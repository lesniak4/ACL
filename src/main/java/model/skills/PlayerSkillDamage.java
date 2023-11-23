package model.skills;

import engine.Cmd;
import model.GameObject;
import model.components.player.PlayerStatsComponent;
import model.components.player.skills.PlayerDamageModifierComponent;
import model.components.player.skills.PlayerStatsModifierComponent;
import utils.GameConfig;

public class PlayerSkillDamage extends PlayerSkill{

    public PlayerSkillDamage(Cmd cmdBinding, String name, int cost, int cooldownInMs) {
        super(cmdBinding, name, cost, cooldownInMs);

        this.cmd = GameConfig.getInstance().getSkill3Key();
    }

    @Override
    public PlayerStatsModifierComponent getNewModifierComponent(GameObject player, PlayerStatsComponent stats) {
        GameConfig gc = GameConfig.getInstance();
        return new PlayerDamageModifierComponent(player, stats, gc.getSkill3Duration(), gc.getSkill3Modifier());
    }
}
