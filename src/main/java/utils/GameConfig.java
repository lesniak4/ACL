package utils;

public class GameConfig {

    private static GameConfig instance = new GameConfig();

    public static GameConfig getInstance() {
        return instance;
    }

    private GameConfig(){
    }

    public int getWinWidth(){ return 1024; } //700;

    public int getWinHeight(){ return 576; } //(int)(Math.sqrt(3f) * getWinWidth * 0.5f );

    public double getTileSize(){ return 92.5d; } //91.84d;

    // Facteur du nombre d'hexagone où le monstre repère le joueur
    public double getMonsterVision(){ return getTileSize() * 1.5d; }

    // Facteur du nombre d'hexagone où le monstre perd de vue le joueur
    public double getMonsterLooseVision(){ return getTileSize() * 1.75d; }

    public int getMonsterNb(){ return 5; }

    public double getMonsterBaseMS(){ return 1.55d; }

    public double getPlayerBaseMS(){ return 1.9d; }

    public double getMaxTime(){ return 90d; }

    public double getAddedTime(){ return 30f; }

    public int getCoinValue(){ return 5; }


}
