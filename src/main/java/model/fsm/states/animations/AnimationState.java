package model.fsm.states.animations;

import model.components.animation.CharacterAnimationComponent;
import model.fsm.IState;
import utils.SpriteSheet;

public abstract class AnimationState implements IState {

    protected CharacterAnimationComponent controller;
    protected SpriteSheet stateSprite;

    public AnimationState(CharacterAnimationComponent controller, SpriteSheet sprite){

        this.controller = controller;
        this.stateSprite = sprite;
    }

    @Override
    public void onEnter() {

        controller.setCurrentSprite(stateSprite);
    }
}
