package model.fsm.states.monsters;

import model.components.ai.AIComponent;
import model.components.attacks.AttackComponent;
import model.components.attacks.MeleeAttackComponent;

public class StateAttack extends AIState{

    private AttackComponent attackComponent;

    public StateAttack(AIComponent aiComponent, AttackComponent attackComponent) {
        super(aiComponent);

        this.attackComponent = attackComponent;
    }

    @Override
    public void tick() {

    }

    @Override
    public void onEnter() {

        aiComponent.stopMoving();
        attackComponent.attack();
    }

    @Override
    public void onExit() {

    }
}
