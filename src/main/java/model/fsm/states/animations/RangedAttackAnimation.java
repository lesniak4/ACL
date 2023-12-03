package model.fsm.states.animations;

import model.components.animation.CharacterAnimationComponent;
import utils.SpriteSheet;

public class RangedAttackAnimation extends AnimationState{

    public RangedAttackAnimation(CharacterAnimationComponent controller, SpriteSheet sprite) {
        super(controller, sprite);
    }

    @Override
    public void onEnter() {

        super.onEnter();
    }

    @Override
    public void tick() {

    }

    @Override
    public void onExit() {

    }
}
