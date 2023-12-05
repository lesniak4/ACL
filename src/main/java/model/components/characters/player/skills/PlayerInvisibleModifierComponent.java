package model.components.characters.player.skills;

import model.GameObject;
import model.components.characters.StatsComponent;

public class PlayerInvisibleModifierComponent extends PlayerStatsModifierComponent {

    public PlayerInvisibleModifierComponent(GameObject obj, StatsComponent stats, int durationInMS) {
        super(obj, stats, durationInMS);

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
