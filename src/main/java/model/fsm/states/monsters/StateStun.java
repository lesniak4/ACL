package model.fsm.states.monsters;

import model.components.ai.AIComponent;

public class StateStun extends AIState {

    public StateStun(AIComponent aiComponent) {
        super(aiComponent);
    }

    @Override
    public void tick() {

    }

    @Override
    public void onEnter() {

        aiComponent.stopMoving();
    }

    @Override
    public void onExit() {

    }
}
