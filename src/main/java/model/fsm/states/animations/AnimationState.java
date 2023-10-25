package model.fsm.states.animations;

import model.components.animation.CharacterAnimationComponent;
import model.fsm.State;

public abstract class AnimationState extends State {

    protected CharacterAnimationComponent controller;

    public AnimationState(CharacterAnimationComponent controller){
        this.controller = controller;
    }
}
