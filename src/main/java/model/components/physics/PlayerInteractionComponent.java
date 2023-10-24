package model.components.physics;

import model.GameObject;
import model.components.AIComponent;
import model.components.CoinComponent;
import model.components.Component;
import model.components.WorldExitComponent;

public class PlayerInteractionComponent extends Component {
    public PlayerInteractionComponent(GameObject obj) {
        super(obj);
    }

    void interactWith(GameObject colliderObj){

        WorldExitComponent exit = colliderObj.getComponent(WorldExitComponent.class);
        if(exit != null){
            colliderObj.getGame().setPlayerWin(true);
        }

        // Check collision avec une pièce
        CoinComponent coin = colliderObj.getComponent(CoinComponent.class);
        if(coin != null){
            colliderObj.destroyGameObject();
            colliderObj.getGame().incrScore();
            System.out.println("Vous venez de récolter une pièce.");
        }
    }

    @Override
    public void update(double dt) {

    }
}
