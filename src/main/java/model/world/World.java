package model.world;

import model.*;
import utils.Vector2;
import model.components.physics.ColliderComponent;
import model.components.physics.PlayerInputComponent;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class World {

    private final int EVEN = 1;
    private final int ODD = -1;

    private double tileSize = 91.84d;

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
        this.tileSize = 0;
        graph = new WorldGraph<>();
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

    public double getTileSize() {
        return tileSize;
    }

    public ArrayList<GameObject> buildWorld(String source, HexOrientation orientation){

        ArrayList<GameObject> tiles = new ArrayList<>();

        try {
            InputStream stream = getClass().getResourceAsStream(source);
            if(stream == null)
                throw new IOException("Le fichier n'existe pas.");

            InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            BufferedReader buffer = new BufferedReader(reader);

            int row = 0;
            int col = 0;
            String line = buffer.readLine();

            if(line != null) {
                int worldSize = line.trim().replaceAll("\\s+","").length();

                HexLayout layout = new HexLayout(orientation, new Vector2(tileSize, tileSize), new Vector2(tileSize, tileSize));

                // Lecture du fichier
                do {
                    for (int i = 0; i < line.length(); i++) {
                        char n = line.charAt(i);
                        if (n != ' ') {
                            Hex hex = Hex.gridToHexCoord(EVEN, col, row);

                            if (n == '0') {
                                tiles.add(GameObjectFactory.getInstance().createPathTile(game, hex, layout, painter));
                                hexMap.put(hex, 0);
                            } else if (n == '1') {
                                tiles.add(GameObjectFactory.getInstance().createWallTile(game, hex, layout, painter, physics));
                                hexMap.put(hex, 1);
                            } else if (n == '2') {
                                tiles.add(GameObjectFactory.getInstance().createWorldExitTile(game, hex, layout, painter, physics));
                                hexMap.put(hex, 2);
                            } else if (n == '3') {
                                tiles.add(GameObjectFactory.getInstance().createPathTile(game, hex, layout, painter));
                                tiles.add(GameObjectFactory.getInstance().createCoinTile(game, hex, layout, painter, physics));
                                hexMap.put(hex, 3);
                            }
                            col++;
                        }
                    }
                    col = 0;
                    row++;
                } while ((line = buffer.readLine()) != null);
                buffer.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        buildWorldGraph();

        return tiles;
    }

    public void createRandomMonsters(int number, List<GameObject> gameObjects, GameObject player){
        Random random = new Random();

        ArrayList<GameObject> objs = new ArrayList<>();

        for(int i = 0 ; i < number; i++) {

            GameObject obj = null;
            while (obj == null || obj.getComponent(ColliderComponent.class) != null) {
                obj = gameObjects.get(random.nextInt(gameObjects.size()));
            }

            GameObject targetObj = null;
            while (targetObj == null || targetObj.getComponent(ColliderComponent.class) != null || obj == targetObj || objs.contains(targetObj)) {
                targetObj = gameObjects.get(random.nextInt(gameObjects.size()));
            }

            double randomSlidingX = random.nextInt(1) == 0 ? -(random.nextInt((int)tileSize)  * 1/2) : (random.nextInt((int)tileSize)  * 1/2);
            double randomSlidingY = random.nextInt(1) == 0 ? -(random.nextInt((int)tileSize)  * 1/2) : (random.nextInt((int)tileSize)  * 1/2);

            objs.add(targetObj);
            objs.add(obj);
            gameObjects.add(GameObjectFactory.getInstance().createMonsterObject(game, (obj.getX() + randomSlidingX), (obj.getY()  + randomSlidingY), painter, this, physics, targetObj, player));
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
        return hexMap.get(hex) == 1;
    }

}
