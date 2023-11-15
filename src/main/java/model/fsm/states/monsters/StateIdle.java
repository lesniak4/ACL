package model.fsm.states.monsters;

import model.components.ai.AIComponent;

public class StateIdle extends AIState {
    public StateIdle(AIComponent aiComponent) {
        super(aiComponent);
    }

    @Override
    public void tick(float dt) {
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onExit() {
    }
}
