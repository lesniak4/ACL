package model.world;

import model.*;
import model.components.ai.PathNodeComponent;
import model.components.rendering.BitmaskedSpriteRendererComponent;
import model.components.world.TeleportationTileComponent;
import model.components.world.TeleportationTileOrientation;
import model.components.world.WorldSpawnComponent;
import utils.GameConfig;
import utils.Vector2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class World {

    private final int EVEN = 1;
    private final int ODD = -1;

    private double tileSize;
    private WorldGraph graph;

    private CanadaGame game;
    private CanadaPainter painter;
    private CanadaPhysics physics;
    private HashMap<Hex, Integer> hexMap;

    public World(CanadaGame game, CanadaPainter painter, CanadaPhysics physics){

        this.game = game;
        this.painter = painter;
        this.physics = physics;
        this.hexMap = new HashMap<>();
        graph = new WorldGraph<>();

        tileSize = GameConfig.getInstance().getTileSize();
    }

    public WorldGraph getGraph() {
        return graph;
    }

    public void setGraph(WorldGraph graph) {
        this.graph = graph;
    }

    public void setTileSize(double tileSize) {
        this.tileSize = tileSize;
    }

    public Collection<GameObject> buildWorld(String source, HexOrientation orientation){

        HashMap<Hex, GameObject> tiles = new HashMap<>();

        int row = 0;
        int col = 0;

        int maxRow = row;
        int maxCol = col;

        HexLayout layout = new HexLayout(orientation, new Vector2(tileSize, tileSize), new Vector2(tileSize, tileSize));

        try {
            InputStream stream = getClass().getResourceAsStream(source);
            if(stream == null)
                throw new IOException("Le fichier n'existe pas.");

            InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            BufferedReader buffer = new BufferedReader(reader);

            String line = buffer.readLine();

            if(line != null) {
                int worldSize = line.trim().replaceAll("\\s+","").length();
                TeleportationTileComponent tp1 = null;
                TeleportationTileComponent tp2 = null;

                // Lecture du fichier
                do {
                    for (int i = 0; i < line.length(); i++) {
                        char n = line.charAt(i);
                        if (n != ' ') {
                            Hex hex = Hex.gridToHexCoord(EVEN, col, row);

                            if (n == '0') {
                                tiles.put(hex, GameObjectFactory.getInstance().createPathTile(game, hex, layout, painter));
                                hexMap.put(hex, 0);
                            } else if (n == '1') {
                                tiles.put(hex, GameObjectFactory.getInstance().createWallTile(game, hex, layout, painter, physics));
                                hexMap.put(hex, 1);
                            }  else if (n == '2') {
                                tiles.put(hex, GameObjectFactory.getInstance().createPathTile(game, hex, layout, painter));
                                tiles.put(hex, GameObjectFactory.getInstance().createCoinsObject(game, hex, layout, painter, physics));
                                hexMap.put(hex, 2);
                            } else if(n == '3'){
                                tiles.put(hex, GameObjectFactory.getInstance().createPathTile(game, hex, layout, painter));
                                tiles.put(hex, GameObjectFactory.getInstance().createKeyObject(game, hex, layout, painter, physics));
                                hexMap.put(hex, 3);
                            } else if(n == '4'){
                                tiles.put(hex, GameObjectFactory.getInstance().createTeleportationTile(game, hex, layout, painter, physics, TeleportationTileOrientation.LEFT));
                                hexMap.put(hex, 4);
                                if(tp1 == null){
                                    tp1 = tiles.get(hex).getComponent(TeleportationTileComponent.class);
                                }else{
                                    tp2 = tiles.get(hex).getComponent(TeleportationTileComponent.class);
                                }
                            }else if(n == '5') {
                                tiles.put(hex, GameObjectFactory.getInstance().createTeleportationTile(game, hex, layout, painter, physics, TeleportationTileOrientation.RIGHT));
                                hexMap.put(hex, 5);
                                if (tp1 == null) {
                                    tp1 = tiles.get(hex).getComponent(TeleportationTileComponent.class);
                                } else {
                                    tp2 = tiles.get(hex).getComponent(TeleportationTileComponent.class);
                                }
                            }else if (n == '6') {
                                tiles.put(hex, GameObjectFactory.getInstance().createPathTile(game, hex, layout, painter));
                                tiles.put(hex, GameObjectFactory.getInstance().createWeaponObject(game, hex, layout, painter, physics));
                            }else if (n == '8') {
                                tiles.put(hex, GameObjectFactory.getInstance().createWorldSpawnTile(game, hex, layout, painter, physics));
                                hexMap.put(hex, 8);
                            }else if (n == '9') {
                                tiles.put(hex, GameObjectFactory.getInstance().createWorldExitTile(game, hex, layout, painter, physics));
                                hexMap.put(hex, 9);
                            }
                            col++;
                        }
                    }
                    maxCol = col;
                    col = 0;
                    row++;
                } while ((line = buffer.readLine()) != null);
                buffer.close();
                maxRow = row;

                if(tp1 != null && tp2 != null){
                    tp1.setLinkedTile(tp2);
                    tp2.setLinkedTile(tp1);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (Hex hex : tiles.keySet()){
            int bitmask = 0;
            for(int i = 0; i < 4; i++){
                Hex neighbor = Hex.hexOfNeighbor(hex, i);
                if(neighbor != null && hexMap.containsKey(neighbor) && isForest(neighbor)){
                    bitmask += (int)Math.pow(2, i);
                }
                BitmaskedSpriteRendererComponent renderer = tiles.get(hex).getComponent(BitmaskedSpriteRendererComponent.class);
                if(renderer != null){
                    renderer.setBitmask(bitmask);
                }
            }
        }

        buildWorldGraph();

        // Création des bordures du monde
        // Bordure Est
        for(int i = -4; i < 0; i++){
            for(int j = -3; j < maxRow+3; j++){
                Hex hex = Hex.gridToHexCoord(EVEN, i, j);
                tiles.put(hex, GameObjectFactory.getInstance().createBorderTile(game, hex, layout, painter, physics));
            }
        }
        // Bordure Ouest
        for(int i = maxCol; i < maxCol+4; i++){
            for(int j = -4; j < maxRow+4; j++){
                Hex hex = Hex.gridToHexCoord(EVEN, i, j);
                tiles.put(hex, GameObjectFactory.getInstance().createBorderTile(game, hex, layout, painter, physics));
            }
        }
        // Bordure Nord
        for(int i = 1; i < maxCol; i++){
            for(int j = -4; j < 0; j++){
                Hex hex = Hex.gridToHexCoord(EVEN, i, j);
                tiles.put(hex, GameObjectFactory.getInstance().createBorderTile(game, hex, layout, painter, physics));
            }
        }
        // Bordure Sud
        for(int i = 0; i < maxCol-1; i++){
            for(int j = maxRow; j < maxRow+4; j++){
                Hex hex = Hex.gridToHexCoord(EVEN, i, j);
                tiles.put(hex, GameObjectFactory.getInstance().createBorderTile(game, hex, layout, painter, physics));
            }
        }


        return tiles.values();
    }

    public void createRandomMonsters(int number, List<GameObject> gameObjects, GameObject player){
        Random random = new Random();

        ArrayList<GameObject> objs = new ArrayList<>();

        for(int i = 0 ; i < number; i++) {

            GameObject obj = null;
            while (obj == null || obj.getComponent(PathNodeComponent.class) == null || obj.getComponent(WorldSpawnComponent.class) != null) {
                obj = gameObjects.get(random.nextInt(gameObjects.size()));
            }

            GameObject targetObj = null;
            while (targetObj == null || targetObj.getComponent(PathNodeComponent.class) == null || obj.getComponent(WorldSpawnComponent.class) != null || obj == targetObj || objs.contains(targetObj)) {
                targetObj = gameObjects.get(random.nextInt(gameObjects.size()));
            }

            double randomSlidingX = random.nextInt(2) == 0 ? -(random.nextInt((int)tileSize) * 0.5f) : (random.nextInt((int)tileSize)  * 0.5f);
            double randomSlidingY = random.nextInt(2) == 0 ? -(random.nextInt((int)tileSize) * 0.5f) : (random.nextInt((int)tileSize)  * 0.5f);

            objs.add(targetObj);
            objs.add(obj);
            gameObjects.add(GameObjectFactory.getInstance().createMonsterObject(game, (obj.getX() + randomSlidingX), (obj.getY()  + randomSlidingY), painter, getGraph(), physics, targetObj, player));
        }
    }

    private void buildWorldGraph(){
        for (Hex hex : hexMap.keySet()){
            Node node = new Node(hex);
            graph.addVertex(node);
        }

        for (Hex hex : hexMap.keySet()){
            if(hexMap.containsKey(hex) && !isWall(hex)){
                for(int i = 0; i < 6; i++){ // On parcours les six voisins
                    Hex neighbor = Hex.hexOfNeighbor( hex, i);

                    if(neighbor != null && hexMap.containsKey(neighbor) && !isWall(neighbor)){
                        Node edge = (Node)graph.getSingularVertex(new Node(neighbor));
                        Node src = (Node)graph.getSingularVertex(new Node(hex));

                        graph.addEdge(src, edge, true);
                    }
                }
            }
        }

    }

    private boolean isWall(Hex hex){
        return hexMap.get(hex) == 1 || hexMap.get(hex) == 4 || hexMap.get(hex) == 5;
    }
    private boolean isForest(Hex hex) { return hexMap.get(hex) == 1; };

}
