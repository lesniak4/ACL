package model.components.animation;

import model.GameObject;
import model.components.Component;
import model.components.physics.MovementComponent;
import model.components.rendering.AnimatedSpriteRendererComponent;
import model.fsm.StateMachine;

public class CharacterAnimationComponent extends Component {

    private MovementComponent movement;
    private AnimatedSpriteRendererComponent renderer;
    private StateMachine stateMachine;

    public CharacterAnimationComponent(GameObject obj, MovementComponent movement, AnimatedSpriteRendererComponent renderer) {
        super(obj);

        this.movement = movement;
        this.renderer = renderer;
        this.stateMachine = new StateMachine();
    }

    @Override
    public void update(double dt) {

        int angle = (int)Math.floor(movement.getDirectionAngle() * 180d / Math.PI);
        if(angle < 0) angle = -angle;
        else if(angle > 0) angle = 360 - angle;
        //System.out.println(angle / 45);
        renderer.setRowIndex(angle / 45);
    }
}
