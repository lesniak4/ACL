package model.components.attacks;

import model.GameObject;
import model.components.Component;

public class StunComponent extends Component {

    private int stunDuration;
    private boolean isStun;

    public StunComponent(GameObject obj) {
        super(obj);

        this.isStun = false;
        this.stunDuration = 0;
    }

    @Override
    public void update() {

        if(isStun){
            stunDuration--;
            if(stunDuration == 0){
                this.isStun = false;
            }
        }
    }

    public boolean isStun() {
        return isStun;
    }

    public void stun(int frameCount){
        this.isStun = true;
        this.stunDuration = frameCount;
    }
}
