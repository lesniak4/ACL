package model;

import model.components.RectangleComponent;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class World {

    private CanadaPainter painter;

    public World(CanadaPainter painter){

        this.painter = painter;
    }

    public ArrayList<GameObject> buildWorld(String source){

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
                float tileSize = CanadaPainter.WIDTH / line.trim().replaceAll("\\s+","").length();

                // Lecture du fichier
                do {
                    for (int i = 0; i < line.length(); i++) {
                        char n = line.charAt(i);
                        if (n == '0') {
                            tiles.add(GameObjectFactory.getInstance().createWallTile(col * tileSize, row * tileSize, tileSize, tileSize, Color.white, painter));
                            col++;
                        } else if (n == '1') {
                            tiles.add(GameObjectFactory.getInstance().createWallTile(col * tileSize, row * tileSize, tileSize, tileSize, Color.green, painter));
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
}
