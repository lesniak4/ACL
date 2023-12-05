package model.skills;

import engine.Cmd;
import model.GameObject;
import model.components.characters.StatsComponent;
import model.components.characters.player.skills.PlayerSpeedModifierComponent;
import model.components.characters.player.skills.PlayerStatsModifierComponent;
import utils.GameConfig;

public class PlayerSkillSpeed extends PlayerSkill{

    public PlayerSkillSpeed(Cmd cmdBinding, String name, int cost, int cooldownInMs) {
        super(cmdBinding, name, cost, cooldownInMs);

        this.cmd = GameConfig.getInstance().getSkill1Key();
    }

    @Override
    public PlayerStatsModifierComponent getNewModifierComponent(GameObject player, StatsComponent stats) {
        GameConfig gc = GameConfig.getInstance();
        return new PlayerSpeedModifierComponent(player, stats, gc.getSkill1Duration(), gc.getSkill1Modifier());
    }
}
