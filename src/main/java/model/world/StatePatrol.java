package model.world;

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
        aiComponent.move();
    }

    @Override
    public void onExit() {
        aiComponent.switchDest();
    }
}
