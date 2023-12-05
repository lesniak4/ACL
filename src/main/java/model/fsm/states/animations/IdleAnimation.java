package model.fsm.states.animations;

import model.components.characters.CharacterAnimationComponent;
import utils.SpriteSheet;

public class IdleAnimation extends AnimationState{

    public IdleAnimation(CharacterAnimationComponent controller, SpriteSheet sprite) {
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
