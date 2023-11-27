package model.components.ai;

import model.GameObject;
import model.components.Component;
import model.components.attacks.AttackComponent;
import model.components.attacks.StunComponent;
import model.components.characters.StatsComponent;
import model.fsm.ICondition;
import model.fsm.IState;
import model.fsm.StateMachine;
import model.fsm.states.monsters.*;
import utils.GameConfig;
import utils.Vector2;

import java.util.Random;

public class AIComponent extends Component {

    private PathfindingComponent pathfindingComponent;
    private StateMachine stateMachine;
    private GameObject player;
    private StatsComponent playerStats;
    private StunComponent stunComponent;
    private AttackComponent attackComponent;
    private IState stateIdle;
    private IState stateMoving;
    private IState statePatrol;
    private IState stateChase;
    private Vector2 savedTarget;
    private Vector2 initialPos;
    private int attackCooldown;

    public AIComponent(GameObject obj, PathfindingComponent pathfindingComponent, GameObject player, StunComponent stun, AttackComponent attack) {
        super(obj);
        this.player = player;
        this.pathfindingComponent = pathfindingComponent;

        this.playerStats = player.getComponent(StatsComponent.class);
        this.stunComponent = stun;
        this.attackComponent = attack;

        GameConfig gc = GameConfig.getInstance();

        Random random = new Random();

        stateMachine = new StateMachine();

        stateIdle = new StateIdle(this);
        stateMoving = new StateMoving(this);
        statePatrol = new StatePatrol(this);
        stateChase = new StateChase(this);
        IState stateStun = new StateStun(this);
        IState stateAttack = new StateAttack(this, attackComponent);

        // Chase
        ICondition conditionToChase = () -> playerStats != null
                && !playerStats.isInvisible()
                && Vector2.distance(player.getPosition(), this.getGameObject().getPosition()) < gc.getMonsterVision();

        ICondition conditionStopChasing = () ->
                Vector2.distance(player.getPosition(), this.getGameObject().getPosition()) > gc.getMonsterLooseVision()
                || (playerStats != null && playerStats.isInvisible());

        stateMachine.addTransition(stateMoving, stateChase, conditionToChase);
        stateMachine.addTransition(stateIdle, stateChase, conditionToChase);
        stateMachine.addTransition(stateChase, stateMoving, conditionStopChasing);

        // Moving
        ICondition conditionMove = () -> { return Vector2.distance(pathfindingComponent.getTarget(), this.getGameObject().getPosition()) >= 1d; };
        ICondition conditionStopMoving = () -> { return Vector2.distance(pathfindingComponent.getTarget(), this.getGameObject().getPosition()) < 1d; };
        stateMachine.addTransition(stateMoving, stateIdle, conditionStopMoving);

        // Stun
        ICondition isStun = () -> stunComponent.isStun();
        ICondition recovered = () -> !(stunComponent.isStun());

        stateMachine.addTransition(stateMoving, stateStun, isStun);
        stateMachine.addTransition(stateChase, stateStun, isStun);
        stateMachine.addTransition(stateAttack, stateStun, isStun);
        stateMachine.addTransition(stateStun, stateMoving, recovered);

        // Attack
        ICondition canAttack = () -> attackCooldown <= 0 && Vector2.distance(player.getPosition(), this.getGameObject().getPosition()) < gc.getMonsterMeleeAttackDistance();
        ICondition attackFinished = () -> !attackComponent.isAttacking();

        stateMachine.addTransition(stateMoving, stateAttack, canAttack);
        stateMachine.addTransition(stateChase, stateAttack, canAttack);
        stateMachine.addTransition(stateAttack, stateMoving, attackFinished);


        switch (random.nextInt(3)){
            case 0:
                createMovingMonster(conditionMove);
                break;
            case 1:
                createPatrolMonster();
                break;
            case 2:
                createStaticMonster(conditionMove);
                break;
            default:
                createStaticMonster(conditionMove);
                break;
        }

        savedTarget = pathfindingComponent.getTarget();
        initialPos = getGameObject().getPosition();

        stateMachine.setState(stateIdle);

    }

    private void createStaticMonster(ICondition condition){
        pathfindingComponent.setTarget(gameObject.getPosition());
        stateMachine.addTransition(stateIdle, stateMoving, condition);
    }

    private void createMovingMonster(ICondition condition){
        stateMachine.addTransition(stateIdle, stateMoving, condition);
    }

    private void createPatrolMonster(){
        ICondition conditionNext = () -> { return true;};
        stateMachine.addTransition(stateIdle, statePatrol, conditionNext);
        ICondition conditionMove = () -> { return pathfindingComponent.getTarget() != null; };
        stateMachine.addTransition(statePatrol, stateMoving, conditionMove);
    }

    public void updateChase(){
        if(pathfindingComponent.isMoving())
            this.pathfindingComponent.setTarget(player.getPosition());
    }

    public void chase(){
        this.savedTarget = pathfindingComponent.getTarget();
        this.pathfindingComponent.setTarget(player.getPosition());
        move();
    }

    public void stopChasing(){
        pathfindingComponent.setTarget(savedTarget);
    }

    public void switchDest(){
        if(Vector2.distance(pathfindingComponent.getTarget(), this.getGameObject().getPosition()) < 1d) {
            pathfindingComponent.setTarget(initialPos);
            Vector2 posGameObject = this.getGameObject().getPosition();
            initialPos = new Vector2(posGameObject.X(), posGameObject.Y());
        }
    }

    public void move(){
        pathfindingComponent.setMoving(true);
    }

    public void stopMoving(){
        pathfindingComponent.setMoving(false);
    }

    @Override
    public void update() {

        stateMachine.tick();

        if(attackCooldown > 0 && !attackComponent.isAttacking()) {
            attackCooldown--;
        }
    }

    public void setAttackCooldown(int frameCount){
        this.attackCooldown = frameCount;
    }

}
