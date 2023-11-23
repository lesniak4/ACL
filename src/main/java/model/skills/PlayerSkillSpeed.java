package model.skills;

import engine.Cmd;
import model.GameObject;
import model.components.player.PlayerStatsComponent;
import model.components.player.skills.PlayerSpeedModifierComponent;
import model.components.player.skills.PlayerStatsModifierComponent;
import utils.GameConfig;

public class PlayerSkillSpeed extends PlayerSkill{

    public PlayerSkillSpeed(Cmd cmdBinding, String name, int cost, int cooldownInMs) {
        super(cmdBinding, name, cost, cooldownInMs);

        this.cmd = GameConfig.getInstance().getSkill1Key();
    }

    @Override
    public PlayerStatsModifierComponent getNewModifierComponent(GameObject player, PlayerStatsComponent stats) {
        GameConfig gc = GameConfig.getInstance();
        return new PlayerSpeedModifierComponent(player, stats, gc.getSkill1Duration(), gc.getSkill1Modifier());
    }
}
