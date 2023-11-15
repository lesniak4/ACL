package model.fsm.states.monsters;

import model.components.ai.AIComponent;

public class StatePatrol extends AIState {
    public StatePatrol(AIComponent aiComponent) {
        super(aiComponent);
    }

    @Override
    public void tick(float dt) {
    }

    @Override
    public void onEnter() {

        aiComponent.switchDest();
    }

    @Override
    public void onExit() {

    }
}
