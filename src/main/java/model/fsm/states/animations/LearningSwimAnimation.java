package model.fsm.states.animations;

import model.components.characters.CharacterAnimationComponent;
import utils.SpriteSheet;

public class LearningSwimAnimation extends AnimationState{

    public LearningSwimAnimation(CharacterAnimationComponent controller, SpriteSheet sprite) {
        super(controller, sprite);
    }

    @Override
    public void tick() {

        int angle = (int) Math.floor(controller.getDirectionAngle() * 180d / Math.PI);

        if (angle < 0) angle = -angle;
        else if (angle > 0) angle = 360 - angle;

        int index = angle / 45;
        controller.setRowIndex(index);
    }

    @Override
    public void onEnter() {

        super.onEnter();
    }

    @Override
    public void onExit() {

    }
}
