package model.fsm.states.animations;

import model.components.animation.CharacterAnimationComponent;
import model.fsm.State;
import utils.SpriteSheet;

public abstract class AnimationState extends State {

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
