package model.fsm.states.animations;

import model.components.animation.CharacterAnimationComponent;
import model.fsm.State;
import utils.AnimatedSprite;

public abstract class AnimationState extends State {

    protected CharacterAnimationComponent controller;
    protected AnimatedSprite stateSprite;

    public AnimationState(CharacterAnimationComponent controller, AnimatedSprite sprite){

        this.controller = controller;
        this.stateSprite = sprite;
    }

    @Override
    public void onEnter() {

        controller.setCurrentSprite(stateSprite);
    }
}
