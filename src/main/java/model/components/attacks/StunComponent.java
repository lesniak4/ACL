package model.components.attacks;

import model.GameObject;
import model.components.Component;

public class StunComponent extends Component {

    private int stunDuration;

    private boolean isStun;
    private long startStunTime;

    public StunComponent(GameObject obj) {
        super(obj);

        this.isStun = false;
        this.startStunTime = System.currentTimeMillis();
    }

    @Override
    public void update() {

        if(System.currentTimeMillis() - startStunTime > stunDuration){
            isStun = false;
        }
    }

    public boolean isStun() {
        return isStun;
    }

    public void stun(int duration){
        this.isStun = true;
        this.stunDuration = duration;
        this.startStunTime = System.currentTimeMillis();
    }
}
