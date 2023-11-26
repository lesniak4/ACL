package model.components.animation;

import model.GameObject;
import model.components.Component;
import model.components.attacks.AttackComponent;
import model.components.physics.MovementComponent;
import model.components.rendering.AnimatedSpriteRendererComponent;
import model.fsm.ICondition;
import model.fsm.StateMachine;
import model.fsm.states.animations.AttackingAnimation;
import model.fsm.states.animations.IdleAnimation;
import model.fsm.states.animations.WalkingAnimation;
import utils.SpriteSheet;

public class CharacterAnimationComponent extends Component {

    private MovementComponent movement;
    private AttackComponent attack;
    private AnimatedSpriteRendererComponent renderer;
    private StateMachine stateMachine;

    public CharacterAnimationComponent(GameObject obj, MovementComponent movement, AttackComponent attack, AnimatedSpriteRendererComponent renderer, SpriteSheet idleSprite, SpriteSheet walkingSprite, SpriteSheet fightingSprite) {
        super(obj);

        this.movement = movement;
        this.attack = attack;
        this.renderer = renderer;
        this.stateMachine = new StateMachine();

        IdleAnimation idle = new IdleAnimation(this, idleSprite);
        WalkingAnimation walking = new WalkingAnimation(this, walkingSprite);
        AttackingAnimation attacking = new AttackingAnimation(this, fightingSprite);

        ICondition isWalking = () -> movement.isMoving();
        ICondition isIdle = () -> !(movement.isMoving());
        ICondition isAttacking = () -> attack.isAttacking();
        ICondition attackFinished = () -> !(attack.isAttacking());

        stateMachine.addTransition(idle, walking, isWalking);
        stateMachine.addTransition(walking, idle, isIdle);

        stateMachine.addAnyTransition(attacking, isAttacking);
        stateMachine.addTransition(attacking, idle, attackFinished);

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
