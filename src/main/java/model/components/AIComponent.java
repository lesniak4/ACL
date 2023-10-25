package model.components;

import model.GameObject;
import utils.Vector2;
import model.world.*;
import model.world.monsterState.*;

import java.util.Random;

public class AIComponent extends Component{

    private PathfindingComponent pathfindingComponent;
    private StateMachine stateMachine;
    private GameObject player;
    private State stateIdle;
    private State stateMoving;
    private State statePatrol;
    private State stateChase;
    private Vector2 savedTarget;
    private Vector2 initialPos;

    public AIComponent(GameObject obj, PathfindingComponent pathfindingComponent, GameObject player) {
        super(obj);
        this.player = player;
        this.pathfindingComponent = pathfindingComponent;

        Random random = new Random();

        stateMachine = new StateMachine();

        stateIdle = new StateIdle(this);
        stateMoving = new StateMoving(this);
        statePatrol = new StatePatrol(this);
        stateChase = new StateChase(this);

        // Chase
        ICondition conditionToChase = () -> { return Vector2.distance(player.getPosition(), this.getGameObject().getPosition()) < pathfindingComponent.getWorld().getTileSize() * 1.5; };
        ICondition conditionStopChasing = () -> { return Vector2.distance(player.getPosition(), this.getGameObject().getPosition()) > pathfindingComponent.getWorld().getTileSize() * 1.75 ; };
        stateMachine.addAnyTransition(stateChase, conditionToChase);
        stateMachine.addTransition(stateChase, stateMoving, conditionStopChasing);

        // Moving
        ICondition conditionMove = () -> { return Vector2.distance(pathfindingComponent.getTarget(), this.getGameObject().getPosition()) > 1d; };
        ICondition conditionStopMoving = () -> { return Vector2.distance(pathfindingComponent.getTarget(), this.getGameObject().getPosition()) < 0.9d; };
        stateMachine.addTransition(stateMoving, stateIdle, conditionStopMoving);

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
    public void update(double dt) {
        stateMachine.tick();
    }
}
