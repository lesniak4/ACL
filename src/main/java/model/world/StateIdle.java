package model.world;

import model.components.AIComponent;

public class StateIdle extends State{
    public StateIdle(AIComponent aiComponent) {
        super(aiComponent);
    }

    @Override
    public void tick() {
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onExit() {
    }
}
