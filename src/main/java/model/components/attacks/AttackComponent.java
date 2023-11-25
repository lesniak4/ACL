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
    protected long lastAttackTime;

    public AttackComponent(GameObject obj, StatsComponent stats, MovementComponent movement, CanadaPhysics physics, double radius, int stunDurationInMS, int lifetimeInMS) {
        super(obj);

        this.physics = physics;

        this.radius = radius;
        this.stunDuration = stunDurationInMS;
        this.lifetime = lifetimeInMS;

        this.stats = stats;
        this.movementComponent = movement;

        this.instantiatedDamageArea = null;
        this.attacking = false;
        this.lastAttackTime = 0;
    }

    @Override
    public void update(){

        if(System.currentTimeMillis() - lastAttackTime > lifetime) {
            this.attacking = false;
        }
    }

    public void attack() {

        Vector2 currentPos = this.getGameObject().getPosition();

        instantiateDamageArea(new Vector2(
                currentPos.X() + this.movementComponent.getCurrentFacingDirection().X() * stats.getMeleeAttackDistance(),
                currentPos.Y() + this.movementComponent.getCurrentFacingDirection().Y() * stats.getMeleeAttackDistance()));
        this.attacking = true;
        this.lastAttackTime = System.currentTimeMillis();
    }

    public void instantiateDamageArea(Vector2 pos){

        GameObject damageArea = GameObjectFactory.getInstance().createDamageArea(gameObject.getGame(), pos, this, physics, radius, stats.getActualDamage(), stunDuration, lifetime);
        getGameObject().getGame().addGameObject(damageArea);

        instantiatedDamageArea = damageArea.getComponent(DamageAreaComponent.class);
    }

    public boolean isAttacking(){
        return this.attacking;
    }

    public void clearDamageArea(){
        instantiatedDamageArea = null;
    }
}
