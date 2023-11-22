package model.skills;

import engine.Cmd;
import model.GameObject;
import model.components.player.PlayerStatsComponent;
import model.components.player.skills.PlayerSpeedModifierComponent;
import model.components.player.skills.PlayerStatsModifierComponent;

public class PlayerSkillSpeed extends PlayerSkill{

    public PlayerSkillSpeed(Cmd cmdBinding, String name, int cost, int cooldownInMs) {
        super(cmdBinding, name, cost, cooldownInMs);

        this.cmd = "W";
    }

    @Override
    public PlayerStatsModifierComponent getNewModifierComponent(GameObject player, PlayerStatsComponent stats) {
        return new PlayerSpeedModifierComponent(player, stats, 10000, 1.5d);
    }
}
