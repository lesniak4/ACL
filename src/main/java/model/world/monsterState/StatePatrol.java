package model.world.monsterState;

import model.components.AIComponent;

public class StatePatrol extends State{
    public StatePatrol(AIComponent aiComponent) {
        super(aiComponent);
    }

    @Override
    public void tick() {
    }

    @Override
    public void onEnter() {
        aiComponent.switchDest();
    }

    @Override
    public void onExit() {
    }
}
