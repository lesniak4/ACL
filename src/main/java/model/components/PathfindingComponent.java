package model.components;

import model.GameObject;

public class PathfindingComponent extends Component{

    private GameObject target;


    public PathfindingComponent(GameObject obj) {
        super(obj);
    }

    public void setTarget(GameObject target) {
        this.target = target;
    }

    public void findPath(){
        if(this.gameObject != null){

        }
    }

    @Override
    public void update(double dt) {

    }
}
