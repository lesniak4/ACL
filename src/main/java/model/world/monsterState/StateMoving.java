package model.world.monsterState;

import model.components.AIComponent;
import model.fsm.State;

public class StateMoving extends AIState {
    public StateMoving(AIComponent aiComponent) {
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
        aiComponent.stopMoving();
    }
}
