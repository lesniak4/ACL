package model.components.world;

import model.GameObject;
import model.components.Component;

public class CoinComponent extends Component {

    private int value;

    public CoinComponent(GameObject obj, int value) {

        super(obj);

        this.value = value;
    }

    @Override
    public void update() {

    }

    public int getValue() {
        return value;
    }
}
