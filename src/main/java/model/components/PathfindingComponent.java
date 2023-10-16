package model.components;

import model.GameObject;

public class PathfindingComponent extends Component{

    private PathfindingComponent pathfindingComponent;
    private GameObject target;

    public PathfindingComponent(GameObject obj, PathfindingComponent pathfindingComponent, GameObject target) {
        super(obj);
        this.pathfindingComponent = pathfindingComponent;
        this.target = target;
    }

    public PathfindingComponent(GameObject obj, PathfindingComponent pathfindingComponent) {
        super(obj);
        this.pathfindingComponent = pathfindingComponent;
    }

    @Override
    public void update(double dt) {

    }
}
