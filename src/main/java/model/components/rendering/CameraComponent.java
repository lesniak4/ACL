package model.components.rendering;

import model.GameObject;
import model.components.Component;

public class CameraComponent extends Component {


    public CameraComponent(GameObject obj) {
        super(obj);
    }

    @Override
    public void update() {

        this.getGameObject().getGame().setCameraPosition(this.getGameObject().getPosition());
    }
}
