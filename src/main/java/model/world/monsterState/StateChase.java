package model.world.monsterState;

import model.components.AIComponent;
import model.fsm.State;

public class StateChase extends AIState {
    public StateChase(AIComponent aiComponent) {
        super(aiComponent);
    }

    @Override
    public void tick() {
        aiComponent.updateChase();
    }

    @Override
    public void onEnter() {
        aiComponent.chase();
    }

    @Override
    public void onExit() {
        aiComponent.stopChasing();
    }
}
