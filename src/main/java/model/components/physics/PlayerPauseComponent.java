package model.components.physics;

import controllers.InGameController;
import engine.Cmd;
import model.CanadaPhysics;
import model.GameObject;
import model.components.Component;
import utils.Vector2;

import java.util.HashSet;
import java.util.Set;

public class PlayerPauseComponent extends Component {

    private PlayerInputComponent playerInputComponent;
    private InGameController controller;

    public PlayerPauseComponent(GameObject obj, PlayerInputComponent playerInputComponent, InGameController controller) {
        super(obj);
        this.playerInputComponent = playerInputComponent;
        this.controller = controller;
    }

    @Override
    public void update() {
        if(this.gameObject != null){
            Set<Cmd> commands = new HashSet<>(playerInputComponent.getCommands());
            if(!commands.isEmpty()) {
                for (Cmd command : commands) {

                    if (command == Cmd.PAUSE) {
                        controller.notifyKeyEscapePressed();
                    }
                }
            }
        }
    }
}
