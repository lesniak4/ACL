package model.fsm.states.animations;

import model.components.animation.CharacterAnimationComponent;
import utils.SpriteSheet;

public class WalkingAnimation extends AnimationState{


    public WalkingAnimation(CharacterAnimationComponent controller, SpriteSheet sprite) {
        super(controller, sprite);
    }

    @Override
    public void tick(float dt) {

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
