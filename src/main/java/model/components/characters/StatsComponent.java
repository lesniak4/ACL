package model.components.characters;

import model.GameObject;
import model.components.Component;
import model.components.rendering.AnimatedSpriteRendererComponent;

public class StatsComponent extends Component {

    private double defaultSpeed;
    private double actualSpeed;

    private int defaultDamage;
    private int actualDamage;

    private boolean invisible;

    private double meleeAttackDistance;

    public StatsComponent(GameObject obj, double defaultSpeed, int defaultDamage, double meleeAttackDistance) {
        super(obj);

        this.defaultSpeed = defaultSpeed;
        this.actualSpeed = defaultSpeed;

        this.defaultDamage = defaultDamage;
        this.actualDamage = defaultDamage;

        this.invisible = false;

        this.meleeAttackDistance = meleeAttackDistance;
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

    public int getDefaultDamage() {
        return defaultDamage;
    }
    public int getActualDamage() {
        return actualDamage;
    }

    public void setActualDamage(int newDamage){
        this.actualDamage = newDamage;
    }

    public void resetActualDamage(){
        this.actualDamage = defaultDamage;
    }

    public double getMeleeAttackDistance() {
        return meleeAttackDistance;
    }
}
