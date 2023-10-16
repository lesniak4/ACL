package model.components;

import model.GameObject;

public class AIComponent extends Component{

    private PathfindingComponent pathfindingComponent;

    public AIComponent(GameObject obj, PathfindingComponent pathfindingComponent) {
        super(obj);

        this.pathfindingComponent = pathfindingComponent;
    }

    @Override
    public void update(double dt) {

    }
}
