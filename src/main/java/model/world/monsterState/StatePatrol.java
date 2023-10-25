package model.world.monsterState;

import model.components.AIComponent;
import model.fsm.State;

public class StatePatrol extends AIState {
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
