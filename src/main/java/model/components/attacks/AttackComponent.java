package model.components.attacks;

import model.CanadaPhysics;
import model.GameObject;
import model.GameObjectFactory;
import model.components.Component;
import model.components.characters.StatsComponent;
import model.components.physics.MovementComponent;
import model.items.ItemData;
import model.items.WeaponData;
import utils.Vector2;

public abstract class AttackComponent extends Component {

    protected CanadaPhysics physics;

    protected double radius;
    protected int stunDuration;
    protected int lifetime;

    protected StatsComponent stats;
    protected MovementComponent movementComponent;
    protected WeaponData requiredWeapon;

    protected DamageAreaComponent instantiatedDamageArea;
    protected boolean attacking;
    protected int frameBeforeEndAttack;

    protected int attackCooldown;
    protected boolean waitingCooldown;
    protected int frameBeforeEndCooldown;

    public AttackComponent(GameObject obj, StatsComponent stats, MovementComponent movement, CanadaPhysics physics, double radius, int stunFrameCount, int lifetimeFrameCount, int cooldownFrameCount, WeaponData weapon) {
        super(obj);

        this.physics = physics;

        this.radius = radius;
        this.stunDuration = stunFrameCount;
        this.lifetime = lifetimeFrameCount;

        this.stats = stats;
        this.movementComponent = movement;
        this.requiredWeapon = weapon;

        this.instantiatedDamageArea = null;
        this.attacking = false;
        this.frameBeforeEndAttack = 0;

        this.waitingCooldown = false;
        this.attackCooldown = cooldownFrameCount;
        this.frameBeforeEndCooldown = 0;
    }

    @Override
    public void update(){

        if(attacking){
            frameBeforeEndAttack--;
            if(frameBeforeEndAttack == 0){
                this.attacking = false;
                this.waitingCooldown = true;
                frameBeforeEndCooldown = attackCooldown;
            }
        }

        if(waitingCooldown){
            frameBeforeEndCooldown--;
            if(frameBeforeEndCooldown == 0){
                this.waitingCooldown = false;
            }
        }

    }

    public void attack() {

        Vector2 currentPos = this.getGameObject().getPosition();

        instantiateDamageArea(new Vector2(
                currentPos.X() + this.movementComponent.getCurrentFacingDirection().X() * stats.getMeleeAttackDistance(),
                currentPos.Y() + this.movementComponent.getCurrentFacingDirection().Y() * stats.getMeleeAttackDistance()));
        this.attacking = true;
    }

    public abstract void instantiateDamageArea(Vector2 pos);

    public boolean isAttacking(){
        return this.attacking;
    }

    public boolean isWaitingCooldown(){
        return waitingCooldown;
    }

    public boolean canAttack(){
        return !attacking && !waitingCooldown;
    }

    public void clearDamageArea(){
        instantiatedDamageArea = null;
    }

    public WeaponData getWeapon(){ return requiredWeapon; }
}
