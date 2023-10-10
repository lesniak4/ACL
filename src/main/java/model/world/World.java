package model.world;

import model.*;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class World {

    private final int EVEN = 1;
    private final int ODD = -1;

    private CanadaPainter painter;
    private CanadaPhysics physics;

    public World(CanadaPainter painter, CanadaPhysics physics){

        this.painter = painter;
        this.physics = physics;
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
                float tileSize = painter.getWidth() / (float)(Math.sqrt(3f)*worldSize);
                HexLayout layout = new HexLayout(orientation, new Vector2(tileSize, tileSize), new Vector2(tileSize, tileSize));

                // Lecture du fichier
                do {
                    for (int i = 0; i < line.length(); i++) {
                        char n = line.charAt(i);
                        Hex hex = gridToHexCoord(EVEN, col, row);
                        if (n == '0') {
                            tiles.add(GameObjectFactory.getInstance().createPathTile(hex, layout, painter));
                            col++;
                        } else if (n == '1') {
                            tiles.add(GameObjectFactory.getInstance().createWallTile(hex, layout, painter, physics));
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
        return tiles;
    }

    private Hex gridToHexCoord(int offset, int col, int row) {
        int q = col - ((row + offset * (row & 1)) / 2);
        int r = row;
        return new Hex(q, r);
    }
}
