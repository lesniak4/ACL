package model.fsm.states.monsters;

import model.components.ai.AIComponent;
import model.components.attacks.AttackComponent;
import model.components.attacks.MeleeAttackComponent;

import java.util.Random;

public class StateAttack extends AIState{

    private Random random;
    private AttackComponent attackComponent;

    public StateAttack(AIComponent aiComponent, AttackComponent attackComponent) {
        super(aiComponent);

        this.random = new Random();
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
