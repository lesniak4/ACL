package model.components.physics;

import engine.Cmd;

import engine.IGameController;
import model.GameObject;
import model.components.Component;

import java.util.Set;

public class PlayerInputComponent extends Component {

    protected IGameController controller;

    public PlayerInputComponent(GameObject obj, IGameController controller){
        super(obj);
        this.controller = controller;
    }

    public Set<Cmd> getCommands() {
        return controller.getCommands();
    }

    @Override
    public void update() {
    }
}