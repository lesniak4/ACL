package model.components;

import model.GameObject;
import model.Vector2;
import model.world.*;

import java.util.Random;

public class AIComponent extends Component{

    private PathfindingComponent pathfindingComponent;
    private StateMachine stateMachine;

    private Vector2 initialPos;

    public AIComponent(GameObject obj, PathfindingComponent pathfindingComponent) {
        super(obj);

        this.pathfindingComponent = pathfindingComponent;
        Random random = new Random();

        switch (random.nextInt(3) ){
            case 0:
                createMovingMonster();
                break;
            case 1:
                createPatrolMonster();
                break;
            case 2:
                createStaticMonster();
                break;
            default:
                createStaticMonster();
                break;
        }
    }

    private void createStaticMonster(){

        stateMachine = new StateMachine();
        initialPos = new Vector2(this.getGameObject().getPosition().X(), this.getGameObject().getPosition().Y());

        /* Définition de l'automate du monstre  */
        State etat1 = new StateIdle(this);

        // Etat initial
        stateMachine.setState(etat1);
    }

    private void createMovingMonster(){

        stateMachine = new StateMachine();
        initialPos = new Vector2(this.getGameObject().getPosition().X(), this.getGameObject().getPosition().Y());

        /* Définition de l'automate du monstre  */
        State etat1 = new StateIdle(this);
        State etat2 = new StateMoving(this);

        // Conditions de transitions
        ICondition conditionEtat1to2 = () -> { return pathfindingComponent.getTarget() != null; };
        ICondition conditionEtat2to1 = () -> { return pathfindingComponent.getTarget() == null || Vector2.distance(pathfindingComponent.getTarget(), this.getGameObject().getPosition()) < 0.5d; };

        // Création des transitions
        stateMachine.addTransition(etat1, etat2, conditionEtat1to2);
        stateMachine.addTransition(etat2, etat1, conditionEtat2to1);

        // Etat initial
        stateMachine.setState(etat1);
    }

    private void createPatrolMonster(){

        stateMachine = new StateMachine();
        initialPos = this.getGameObject().getPosition();

        /* Définition de l'automate du monstre  */
        State etat1 = new StatePatrol(this);
        State etat2 = new StatePatrol(this);


        // Conditions de transitions
        ICondition conditionEtatReset = () -> { return Vector2.distance(pathfindingComponent.getTarget(), this.getGameObject().getPosition()) < 0.5d;};

        // Création des transitions
        stateMachine.addTransition(etat1, etat2, conditionEtatReset);
        stateMachine.addTransition(etat2, etat1, conditionEtatReset);

        // Etat initial
        stateMachine.setState(etat1);
    }

    public void switchDest(){
        pathfindingComponent.setTarget(new Vector2(initialPos.X(), initialPos.Y()));

        Vector2 posGameObject = this.getGameObject().getPosition();
        initialPos = new Vector2(posGameObject.X(), posGameObject.Y());
        this.move();
    }

    public void move(){
        pathfindingComponent.setMoving(true);
    }

    public void stopMoving(){
        pathfindingComponent.setTarget(null);
        pathfindingComponent.setMoving(false);
    }

    @Override
    public void update(double dt) {
        stateMachine.tick();
    }
}
