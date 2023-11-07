package model.fsm.states.monsters;

import model.components.ai.AIComponent;

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
