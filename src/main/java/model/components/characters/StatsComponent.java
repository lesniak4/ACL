package model.components.characters;

import model.GameObject;
import model.components.Component;
import model.components.rendering.AnimatedSpriteRendererComponent;

public class StatsComponent extends Component {

    private double defaultSpeed;
    private double actualSpeed;

    private int defaultMeleeDamage;
    private int actualMeleeDamage;

    private int defaultRangedDamage;
    private int actualRangedDamage;

    private boolean invisible;

    private double meleeAttackDistance;
    private double rangedAttackSpeed;

    public StatsComponent(GameObject obj, double defaultSpeed, int defaultMeleeDamage, int defaultRangedDamage, double meleeAttackDistance, double rangedAttackSpeed) {
        super(obj);

        this.defaultSpeed = defaultSpeed;
        this.actualSpeed = defaultSpeed;

        this.defaultMeleeDamage = defaultMeleeDamage;
        this.actualMeleeDamage = defaultMeleeDamage;

        this.defaultRangedDamage = defaultRangedDamage;
        this.actualRangedDamage = defaultRangedDamage;

        this.invisible = false;

        this.meleeAttackDistance = meleeAttackDistance;
        this.rangedAttackSpeed = rangedAttackSpeed;
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

    public int getDefaultMeleeDamage() {
        return defaultMeleeDamage;
    }
    public int getActualMeleeDamage() {
        return actualMeleeDamage;
    }

    public int getDefaultRangedDamage() {
        return defaultRangedDamage;
    }
    public int getActualRangedDamage() {
        return actualRangedDamage;
    }

    public void setActualMeleeDamage(int newDamage){
        this.actualMeleeDamage = newDamage;
    }

    public void setActualRangedDamage(int newDamage){
        this.actualRangedDamage = newDamage;
    }

    public void resetActualDamage(){

        this.actualMeleeDamage = defaultMeleeDamage;
        this.actualRangedDamage = defaultRangedDamage;
    }

    public double getMeleeAttackDistance() {
        return meleeAttackDistance;
    }

    public double getRangedAttackSpeed() {
        return rangedAttackSpeed;
    }
}
