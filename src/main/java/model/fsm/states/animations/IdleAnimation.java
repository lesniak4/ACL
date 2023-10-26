package model.fsm.states.animations;

import model.components.animation.CharacterAnimationComponent;
import utils.AnimatedSprite;

public class IdleAnimation extends AnimationState{

    public IdleAnimation(CharacterAnimationComponent controller, AnimatedSprite sprite) {
        super(controller, sprite);
    }

    @Override
    public void tick() {

    }

    @Override
    public void onEnter() {

        super.onEnter();
    }

    @Override
    public void onExit() {

    }
}
