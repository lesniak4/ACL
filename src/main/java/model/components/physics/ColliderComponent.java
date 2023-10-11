package model.components.physics;

import model.CanadaPhysics;
import model.GameObject;
import model.components.CoinComponent;
import model.components.Component;
import model.components.WorldExitComponent;
import model.components.rendering.CircleRendererComponent;

public class ColliderComponent extends Component {

    private CanadaPhysics physics;
    private double radius;
    private boolean isTrigger;

    public ColliderComponent(GameObject obj, CanadaPhysics physics, double radius, boolean isTrigger) {
        super(obj);
        this.physics = physics;
        this.radius = radius;
        this.isTrigger = isTrigger;

        this.physics.addCollider(this);
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void update(double dt) {

    }

    public boolean isTrigger() {
        return isTrigger;
    }

    public void onCollisionEnter(GameObject colliderObj){

        GameObject obj = getGameObject();

        // Check pour la fin du jeu
        WorldExitComponent exit = colliderObj.getComponent(WorldExitComponent.class);
        PlayerInputComponent player = obj.getComponent(PlayerInputComponent.class);
        if(player != null && exit != null){
            obj.getGame().setPlayerWin(true);
        }

        // Check collision avec une pièce
        CoinComponent coin = colliderObj.getComponent(CoinComponent.class);
        if(player != null && coin != null){
            colliderObj.destroyGameObject();
            obj.getGame().incrScore();
            System.out.println("Vous venez de récolter une pièce.");
        }
    }

    @Override
    public void destroyComponent(){

        physics.removeCollider(this);
    }
}
