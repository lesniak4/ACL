package model.fsm.states.animations;

import model.components.animation.CharacterAnimationComponent;
import utils.SpriteSheet;

public class AttackingAnimation extends AnimationState{

    public AttackingAnimation(CharacterAnimationComponent controller, SpriteSheet sprite) {
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
    public void onExit() {

    }
}
