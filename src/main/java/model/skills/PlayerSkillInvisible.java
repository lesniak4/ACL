package model.skills;

import engine.Cmd;
import model.GameObject;
import model.components.characters.StatsComponent;
import model.components.characters.player.skills.PlayerInvisibleModifierComponent;
import model.components.characters.player.skills.PlayerStatsModifierComponent;
import utils.GameConfig;

public class PlayerSkillInvisible extends PlayerSkill{

    public PlayerSkillInvisible(Cmd cmdBinding, String name, int cost, int cooldownInMs) {
        super(cmdBinding, name, cost, cooldownInMs);

        this.cmd = GameConfig.getInstance().getSkill2Key();
    }

    @Override
    public PlayerStatsModifierComponent getNewModifierComponent(GameObject player, StatsComponent stats) {
        GameConfig gc = GameConfig.getInstance();
        return new PlayerInvisibleModifierComponent(player, stats, gc.getSkill2Duration());
    }
}
