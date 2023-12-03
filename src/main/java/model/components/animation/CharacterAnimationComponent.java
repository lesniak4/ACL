package model.components.animation;

import model.GameObject;
import model.components.Component;
import model.components.attacks.MeleeAttackComponent;
import model.components.attacks.RangedAttackComponent;
import model.components.attacks.StunComponent;
import model.components.physics.MovementComponent;
import model.components.rendering.AnimatedSpriteRendererComponent;
import model.fsm.ICondition;
import model.fsm.StateMachine;
import model.fsm.states.animations.*;
import utils.SpriteSheet;

public class CharacterAnimationComponent extends Component {

    private MovementComponent movement;
    private MeleeAttackComponent meleeAttack;
    private RangedAttackComponent rangedAttack;
    private AnimatedSpriteRendererComponent renderer;
    private StateMachine stateMachine;

    public CharacterAnimationComponent(GameObject obj, MovementComponent movement, MeleeAttackComponent meleeAttack, RangedAttackComponent rangedAttack, AnimatedSpriteRendererComponent renderer, SpriteSheet idleSprite, SpriteSheet walkingSprite, SpriteSheet fightingSprite, SpriteSheet slingshotSprite) {
        super(obj);

        this.movement = movement;
        this.meleeAttack = meleeAttack;
        this.rangedAttack = rangedAttack;
        this.renderer = renderer;
        this.stateMachine = new StateMachine();

        IdleAnimation idle = new IdleAnimation(this, idleSprite);
        WalkingAnimation walking = new WalkingAnimation(this, walkingSprite);
        MeleeAttackAnimation attackingMelee = new MeleeAttackAnimation(this, fightingSprite);

        ICondition isWalking = () -> movement.isMoving();
        ICondition isIdle = () -> !(movement.isMoving());
        ICondition isAttackingMelee = () -> meleeAttack.isAttacking();
        ICondition meleeAttackFinished = () -> !(meleeAttack.isAttacking());

        stateMachine.addTransition(idle, walking, isWalking);
        stateMachine.addTransition(walking, idle, isIdle);

        stateMachine.addAnyTransition(attackingMelee, isAttackingMelee);
        stateMachine.addTransition(attackingMelee, idle, meleeAttackFinished);

        if(rangedAttack != null) {
            RangedAttackAnimation attackingRanged = new RangedAttackAnimation(this, slingshotSprite);
            ICondition isAttackingRanged = () -> rangedAttack.isAttacking();
            ICondition rangedAttackFinished = () -> !(rangedAttack.isAttacking());
            stateMachine.addTransition(idle, attackingRanged, isAttackingRanged);
            stateMachine.addTransition(walking, attackingRanged, isAttackingRanged);
            stateMachine.addTransition(attackingRanged, idle, rangedAttackFinished);
        }

        stateMachine.setState(idle);

    }

    @Override
    public void update() {

        stateMachine.tick();
    }

    public void setCurrentSprite(SpriteSheet sprite){
        renderer.setSprite(sprite);
    }

    public void setRowIndex(int index){
        renderer.setRowIndex(index);
    }

    public double getDirectionAngle(){
        return movement.getDirectionAngle();
    }

}
