package model.components.player.skills;

import model.GameObject;
import model.components.Component;
import model.components.player.PlayerStatsComponent;
import model.components.rendering.GraphicsComponent;

public class PlayerInvisibleModifierComponent extends PlayerStatsModifierComponent {

    public PlayerInvisibleModifierComponent(GameObject obj, PlayerStatsComponent stats, int durationInMS) {
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
