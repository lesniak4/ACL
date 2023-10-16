package model.components;

import model.GameObject;
import model.world.ICondition;
import model.world.StateIdle;
import model.world.StateMachine;

public class AIComponent extends Component{

    private PathfindingComponent pathfindingComponent;

    private StateMachine stateMachine;

    public AIComponent(GameObject obj, PathfindingComponent pathfindingComponent) {
        super(obj);

        this.pathfindingComponent = pathfindingComponent;


        /* Définition de l'automate du monstre  */

        stateMachine = new StateMachine();

        var etat1 = new StateIdle(this);
        var etat2 = new StateIdle(this);

        // Conditions de transitions
        ICondition conditionEtat1to2 = () -> { return obj.getGame().getScore() > 2; };

        // Création des transitions
        stateMachine.addTransition(etat1, etat2, conditionEtat1to2);

        // Etat initial
        stateMachine.setState(etat1);
    }

    @Override
    public void update(double dt) {
        stateMachine.tick();
    }
}
