package model.components.physics;

import engine.Cmd;
import engine.IGameController;
import model.CanadaPhysics;
import model.GameObject;
import model.components.Component;

import java.util.LinkedList;
import java.util.Set;

public class PlayerInputComponent extends Component implements IInputController {

    protected IGameController controller;

    public PlayerInputComponent(GameObject obj, IGameController controller){
        super(obj);
        this.controller = controller;
    }

    @Override
    public Set<Cmd> getCommands() {
        return controller.getCommands();
    }

    @Override
    public void update(double dt) {
    }
}