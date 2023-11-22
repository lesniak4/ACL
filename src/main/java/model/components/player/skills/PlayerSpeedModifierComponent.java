package model.components.player.skills;

import model.GameObject;
import model.components.Component;
import model.components.player.PlayerStatsComponent;

public class PlayerSpeedModifierComponent extends PlayerStatsModifierComponent {

    private double speedFactor;

    public PlayerSpeedModifierComponent(GameObject obj, PlayerStatsComponent stats, int durationInMS, double speedFactor) {
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
