package model.world.monsterState;

import model.components.AIComponent;
import model.fsm.State;

public class StateIdle extends AIState {
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
