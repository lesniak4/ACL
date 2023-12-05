package model.components.attacks;

import model.GameObject;
import model.components.Component;
import model.components.rendering.GraphicsComponent;
import utils.GameConfig;

public class StunComponent extends Component {

    private GraphicsComponent renderer;
    private int stunDuration;
    private boolean isStun;
    private int flashCounter;

    public StunComponent(GameObject obj, GraphicsComponent renderer) {
        super(obj);

        this.renderer = renderer;
        this.isStun = false;
        this.stunDuration = 0;
        this.flashCounter = GameConfig.getInstance().getStunFlashFrameCount();
    }

    @Override
    public void update() {

        if(isStun){
            flashCounter--;
            if(flashCounter <= 0) {
                renderer.setVisible(!renderer.isVisible());
                flashCounter = GameConfig.getInstance().getStunFlashFrameCount();
            }

            stunDuration--;
            if(stunDuration == 0){
                this.isStun = false;
                renderer.setVisible(true);
            }
        }
    }

    public boolean isStun() {
        return isStun;
    }

    public void stun(int frameCount){
        this.isStun = true;
        this.stunDuration = frameCount;
    }
}
