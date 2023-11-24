package model.skills;

import engine.Cmd;
import model.GameObject;
import model.components.player.PlayerStatsComponent;
import model.components.player.skills.PlayerInvisibleModifierComponent;
import model.components.player.skills.PlayerStatsModifierComponent;
import utils.GameConfig;

public class PlayerSkillInvisible extends PlayerSkill{

    public PlayerSkillInvisible(Cmd cmdBinding, String name, int cost, int cooldownInMs) {
        super(cmdBinding, name, cost, cooldownInMs);

        this.cmd = GameConfig.getInstance().getSkill2Key();
    }

    @Override
    public PlayerStatsModifierComponent getNewModifierComponent(GameObject player, PlayerStatsComponent stats) {
        GameConfig gc = GameConfig.getInstance();
        return new PlayerInvisibleModifierComponent(player, stats, gc.getSkill2Duration());
    }
}
