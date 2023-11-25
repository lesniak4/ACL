package model.skills;

import engine.Cmd;
import model.GameObject;
import model.components.characters.StatsComponent;
import model.components.characters.player.skills.PlayerDamageModifierComponent;
import model.components.characters.player.skills.PlayerStatsModifierComponent;
import utils.GameConfig;

public class PlayerSkillDamage extends PlayerSkill{

    public PlayerSkillDamage(Cmd cmdBinding, String name, int cost, int cooldownInMs) {
        super(cmdBinding, name, cost, cooldownInMs);

        this.cmd = GameConfig.getInstance().getSkill3Key();
    }

    @Override
    public PlayerStatsModifierComponent getNewModifierComponent(GameObject player, StatsComponent stats) {
        GameConfig gc = GameConfig.getInstance();
        return new PlayerDamageModifierComponent(player, stats, gc.getSkill3Duration(), gc.getSkill3Modifier());
    }
}
