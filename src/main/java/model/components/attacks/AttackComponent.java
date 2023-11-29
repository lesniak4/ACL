package model.components.attacks;

import model.CanadaPhysics;
import model.GameObject;
import model.GameObjectFactory;
import model.components.Component;
import model.components.characters.StatsComponent;
import model.components.physics.MovementComponent;
import utils.Vector2;

public abstract class AttackComponent extends Component {

    protected CanadaPhysics physics;

    protected double radius;
    protected int stunDuration;
    protected int lifetime;

    protected StatsComponent stats;
    protected MovementComponent movementComponent;

    protected DamageAreaComponent instantiatedDamageArea;
    protected boolean attacking;
    protected int frameBeforeEndAttack;

    public AttackComponent(GameObject obj, StatsComponent stats, MovementComponent movement, CanadaPhysics physics, double radius, int stunFrameCount, int lifetimeFrameCount) {
        super(obj);

        this.physics = physics;

        this.radius = radius;
        this.stunDuration = stunFrameCount;
        this.lifetime = lifetimeFrameCount;

        this.stats = stats;
        this.movementComponent = movement;

        this.instantiatedDamageArea = null;
        this.attacking = false;

        this.frameBeforeEndAttack = 0;
    }

    @Override
    public void update(){

        if(attacking){
            frameBeforeEndAttack--;
            if(frameBeforeEndAttack == 0){
                this.attacking = false;
            }
        }
    }

    public void attack() {

        Vector2 currentPos = this.getGameObject().getPosition();

        instantiateDamageArea(new Vector2(
                currentPos.X() + this.movementComponent.getCurrentFacingDirection().X() * stats.getMeleeAttackDistance(),
                currentPos.Y() + this.movementComponent.getCurrentFacingDirection().Y() * stats.getMeleeAttackDistance()));
        this.attacking = true;
        this.frameBeforeEndAttack = lifetime;
    }

    public abstract void instantiateDamageArea(Vector2 pos);

    public boolean isAttacking(){
        return this.attacking;
    }

    public void clearDamageArea(){
        instantiatedDamageArea = null;
    }
}
