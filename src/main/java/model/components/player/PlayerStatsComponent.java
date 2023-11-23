package model.components.player;

import model.GameObject;
import model.components.Component;
import model.components.rendering.AnimatedSpriteRendererComponent;
import model.components.rendering.GraphicsComponent;

public class PlayerStatsComponent extends Component {

    private double defaultSpeed;

    private double actualSpeed;

    private double defaultDamage;
    private double actualDamage;

    private boolean invisible;

    public PlayerStatsComponent(GameObject obj, double defaultSpeed, double defaultDamage) {
        super(obj);

        this.defaultSpeed = defaultSpeed;
        this.actualSpeed = defaultSpeed;

        this.defaultDamage = defaultDamage;
        this.actualDamage = defaultDamage;

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
        if(invisible){
            getGameObject().getComponent(AnimatedSpriteRendererComponent.class).setInvisible();
        }else{
            getGameObject().getComponent(AnimatedSpriteRendererComponent.class).resetInvisibility();
        }
    }

    public double getDefaultDamage() {
        return defaultDamage;
    }

    public void setActualDamage(double newDamage){
        this.actualDamage = newDamage;
    }

    public void resetActualDamage(){
        this.actualDamage = defaultSpeed;
    }

}
