package model.skills;

import engine.Cmd;
import model.GameObject;
import model.components.characters.StatsComponent;
import model.components.characters.player.skills.PlayerStatsModifierComponent;

public abstract class PlayerSkill {

    protected Cmd cmdBinding;
    protected String name;
    protected String cmd;
    protected int cost;
    protected int cooldownInMs;

    public PlayerSkill(Cmd cmdBinding, String name, int cost, int cooldownInMs){

        this.cmdBinding = cmdBinding;
        this.name = name;
        this.cost = cost;
        this.cooldownInMs = cooldownInMs;
    }

    public abstract PlayerStatsModifierComponent getNewModifierComponent(GameObject player, StatsComponent stats);

    public int getCost(){
        return this.cost;
    }

    public int getCooldown(){
        return this.cooldownInMs;
    }

    public String getCmdString(){
        return this.cmd;
    }

}
