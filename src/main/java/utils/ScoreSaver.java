package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ScoreSaver {
    private String filename;
    private int maxScore;

    public ScoreSaver(){
        this.filename = GameConfig.getInstance().getSaveFileName();
        this.maxScore = readScoreFile();
    }

    public int getMaxScore(){
        return maxScore;
    }

    public void setMaxScore(int score){
        this.maxScore = score;
    }

    public void writeScoreFile() throws RuntimeException {

        try {
            File f = new File(filename);
            if(f.exists()){
                f.delete();
            }
            f.createNewFile();

            FileWriter writer = new FileWriter(f);
            writer.write(""+maxScore);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int readScoreFile() throws RuntimeException {
        try {
            File f = new File(filename);
            if (f.exists()) {
                return Integer.parseInt(Files.readString(f.toPath(), StandardCharsets.UTF_8));
            } else {
                return 0;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
