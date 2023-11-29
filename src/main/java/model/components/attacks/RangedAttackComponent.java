package model.components.attacks;

import model.CanadaPhysics;
import model.GameObject;
import model.GameObjectFactory;
import model.components.characters.StatsComponent;
import model.components.physics.MovementComponent;
import model.components.rendering.SpriteRendererComponent;
import utils.GameConfig;
import utils.SpriteLoader;
import utils.Vector2;

import java.awt.*;

public class RangedAttackComponent extends AttackComponent{

    public RangedAttackComponent(GameObject obj, StatsComponent stats, MovementComponent movement, CanadaPhysics physics, double radius, int stunFrameCount, int lifetimeFrameCount) {
        super(obj, stats, movement, physics, radius, stunFrameCount, lifetimeFrameCount);
    }

    @Override
    public void update() {

        super.update();
    }

    @Override
    public void instantiateDamageArea(Vector2 pos){

        GameObject damageArea = GameObjectFactory.getInstance().createDamageArea(gameObject.getGame(), pos, this, physics, radius, stats.getActualRangedDamage(), stunDuration, lifetime, stats.getRangedAttackSpeed(), this.movementComponent.getCurrentFacingDirection(), true);
        //damageArea.addComponent();

        getGameObject().getGame().addGameObject(damageArea);

        instantiatedDamageArea = damageArea.getComponent(DamageAreaComponent.class);


    }
}
