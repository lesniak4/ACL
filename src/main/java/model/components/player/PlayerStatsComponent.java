package model.components.player;

import model.GameObject;
import model.components.Component;

public class PlayerStatsComponent extends Component {

    private double defaultSpeed;

    private double actualSpeed;

    private boolean invisible;

    public PlayerStatsComponent(GameObject obj, double defaultSpeed) {
        super(obj);

        this.defaultSpeed = defaultSpeed;
        this.actualSpeed = defaultSpeed;

        this.invisible = false;
    }

    @Override
    public void update() {

    }

    public double getActualSpeed() {
        return actualSpeed;
    }

    public double getDefaultSpeed() {
        return defaultSpeed;
    }

    public void setActualSpeed(double newSpeed){
        this.actualSpeed = newSpeed;
    }

    public void resetActualSpeed(){
        this.actualSpeed = defaultSpeed;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }
}
