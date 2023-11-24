package model.fsm.states.monsters;

import model.components.ai.AIComponent;
import model.fsm.IState;

public abstract class AIState implements IState {

    protected AIComponent aiComponent;

    public AIState(AIComponent aiComponent) {
        super();

        this.aiComponent = aiComponent;
    }
}
