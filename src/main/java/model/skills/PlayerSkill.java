package model.skills;

import engine.Cmd;
import model.GameObject;
import model.components.player.PlayerStatsComponent;
import model.components.player.skills.PlayerStatsModifierComponent;

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

    public abstract PlayerStatsModifierComponent getNewModifierComponent(GameObject player, PlayerStatsComponent stats);

    public int getCost(){
        return this.cost;
    }

    public int getCooldown(){
        return this.cooldownInMs;
    }

    @Override
    public String toString(){
        return this.cmd + " " + this.name;
    }

}
