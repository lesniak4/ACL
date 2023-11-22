package model.components.animation;

import model.GameObject;
import model.components.Component;
import model.components.physics.MovementComponent;
import model.components.rendering.AnimatedSpriteRendererComponent;
import model.fsm.ICondition;
import model.fsm.StateMachine;
import model.fsm.states.animations.IdleAnimation;
import model.fsm.states.animations.WalkingAnimation;
import utils.SpriteSheet;

public class CharacterAnimationComponent extends Component {

    private MovementComponent movement;
    private AnimatedSpriteRendererComponent renderer;
    private StateMachine stateMachine;

    public CharacterAnimationComponent(GameObject obj, MovementComponent movement, AnimatedSpriteRendererComponent renderer, SpriteSheet idleSprite, SpriteSheet walkingSprite) {
        super(obj);

        this.movement = movement;
        this.renderer = renderer;
        this.stateMachine = new StateMachine();

        IdleAnimation idle = new IdleAnimation(this, idleSprite);
        WalkingAnimation walking = new WalkingAnimation(this, walkingSprite);

        ICondition isWalking = () -> { return movement.isMoving(); };
        ICondition isIdle = () -> { return !(movement.isMoving()); };

        stateMachine.addTransition(idle, walking, isWalking);
        stateMachine.addTransition(walking, idle, isIdle);

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
