package model.components.characters.player.skills;

import model.GameObject;
import model.components.characters.StatsComponent;

public class PlayerDamageModifierComponent extends PlayerStatsModifierComponent {

    private int damageAdded;

    public PlayerDamageModifierComponent(GameObject obj, StatsComponent stats, int durationInMS, int damageAdded) {
        super(obj, stats, durationInMS);
        this.damageAdded = damageAdded;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void applyModifier() {
        stats.setActualDamage(stats.getDefaultDamage() + damageAdded);
    }

    @Override
    public void resetModifier() {
        stats.resetActualDamage();
    }
}
