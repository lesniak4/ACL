package model.skills;

import engine.Cmd;
import model.GameObject;
import model.components.player.PlayerStatsComponent;
import model.components.player.skills.PlayerInvisibleModifierComponent;
import model.components.player.skills.PlayerStatsModifierComponent;

public class PlayerSkillInvisible extends PlayerSkill{

    public PlayerSkillInvisible(Cmd cmdBinding, int cost, int cooldownInMs) {
        super(cmdBinding, cost, cooldownInMs);
    }

    @Override
    public PlayerStatsModifierComponent getNewModifierComponent(GameObject player, PlayerStatsComponent stats) {
        return new PlayerInvisibleModifierComponent(player, stats, 7500);
    }
}
