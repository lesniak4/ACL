package model.fsm.states.monsters;

import model.components.ai.AIComponent;

public class StateMoving extends AIState {
    public StateMoving(AIComponent aiComponent) {
        super(aiComponent);
    }

    @Override
    public void tick(float dt) {
    }

    @Override
    public void onEnter() {
        aiComponent.move();
    }

    @Override
    public void onExit() {
        aiComponent.stopMoving();
    }
}
