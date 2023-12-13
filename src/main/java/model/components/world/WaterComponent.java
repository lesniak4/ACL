package model.components.world;

import model.GameObject;
import model.components.Component;
import model.components.characters.SwimComponent;
import model.components.physics.ColliderComponent;
import model.components.physics.ICollidable;

public class WaterComponent extends Component implements ICollidable {

    public WaterComponent(GameObject obj) {
        super(obj);
    }

    @Override
    public void update() {

    }

    @Override
    public void subscribeToCollider(ColliderComponent collider) {

        collider.addCollidableComponent(this);
    }

    @Override
    public void onCollisionEnter(GameObject colliderObj) {

        SwimComponent s = colliderObj.getComponent(SwimComponent.class);
        if(s != null && s.canSwim()){
            s.startSwimming();
        }
    }

    @Override
    public void onCollisionExit(GameObject colliderObj) {

        SwimComponent s = colliderObj.getComponent(SwimComponent.class);
        if(s != null){
            s.stopSwimming();
        }
    }
}
