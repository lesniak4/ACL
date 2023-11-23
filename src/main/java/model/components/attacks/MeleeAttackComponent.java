package model.components.attacks;

import engine.Cmd;
import model.CanadaPhysics;
import model.GameObject;
import model.GameObjectFactory;
import model.components.physics.MovementComponent;
import model.components.player.PlayerInputComponent;
import model.components.player.PlayerStatsComponent;
import utils.Vector2;

import java.util.HashSet;
import java.util.Set;

public class MeleeAttackComponent extends AttackComponent{

    private PlayerInputComponent playerInputComponent;
    private PlayerStatsComponent stats;
    private MovementComponent movementComponent;

    private boolean attacked;
    private long lastAttackTime;

    public MeleeAttackComponent(GameObject obj, PlayerInputComponent input, PlayerStatsComponent stats, MovementComponent movement, CanadaPhysics physics,  double radius, int damage, int lifetimeInMS) {
        super(obj, physics, radius, damage, lifetimeInMS);

        this.playerInputComponent = input;
        this.stats = stats;
        this.movementComponent = movement;
        this.attacked = false;
        this.lastAttackTime = 0;
    }

    @Override
    public void update() {

        if(this.gameObject != null){
            Set<Cmd> commands = new HashSet<>(playerInputComponent.getCommands());
            if(!commands.isEmpty()) {
                for (Cmd command : commands) {
                    if (command == Cmd.MELEE_ATTACK) {
                        if(!attacked && System.currentTimeMillis() - lastAttackTime > lifetimeInMS) {
                            this.getGameObject().getGame().setLastKeyPressed(Cmd.MELEE_ATTACK);
                            Vector2 currentPos = this.getGameObject().getPosition();
                            instantiateDamageArea(new Vector2(
                                    currentPos.X() + this.movementComponent.getCurrentFacingDirection().X() * stats.getMeleeAttackDistance(),
                                    currentPos.Y() + this.movementComponent.getCurrentFacingDirection().Y() * stats.getMeleeAttackDistance()));
                            this.attacked = true;
                            this.lastAttackTime = System.currentTimeMillis();
                        }
                        return;
                    }
                }
                this.attacked = false;
            }
        }
    }

    public void instantiateDamageArea(Vector2 pos){

        GameObject damageArea = GameObjectFactory.getInstance().createDamageArea(gameObject.getGame(), pos, this, physics, radius, damage, lifetimeInMS);
        getGameObject().getGame().addGameObject(damageArea);

        instantiatedDamageArea = damageArea.getComponent(DamageAreaComponent.class);
    }
}
