package model.components.player.skills;

import model.GameObject;
import model.components.Component;
import model.components.player.PlayerStatsComponent;

public class PlayerInvisibleModifierComponent extends PlayerStatsModifierComponent {

    public PlayerInvisibleModifierComponent(GameObject obj, PlayerStatsComponent stats, int duration) {
        super(obj, stats, duration);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void applyModifier() {
        stats.setInvisible(true);
    }

    @Override
    public void resetModifier() {
        stats.setInvisible(false);
    }
}
