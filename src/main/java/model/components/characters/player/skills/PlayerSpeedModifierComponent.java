package model.components.characters.player.skills;

import model.GameObject;
import model.components.characters.StatsComponent;

public class PlayerSpeedModifierComponent extends PlayerStatsModifierComponent {

    private double speedFactor;

    public PlayerSpeedModifierComponent(GameObject obj, StatsComponent stats, int durationInMS, double speedFactor) {
        super(obj, stats, durationInMS);

        this.speedFactor = speedFactor;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void applyModifier() {
        stats.setActualSpeed(stats.getDefaultSpeed() * speedFactor);
    }

    @Override
    public void resetModifier() {
        stats.resetActualSpeed();
    }
}
