package model.components.player;

import engine.Cmd;
import model.GameObject;
import model.components.Component;

import java.util.HashSet;
import java.util.Set;

public class PlayerPauseComponent extends Component {

    private PlayerInputComponent playerInputComponent;

    public PlayerPauseComponent(GameObject obj, PlayerInputComponent playerInputComponent) {
        super(obj);
        this.playerInputComponent = playerInputComponent;
    }

    @Override
    public void update() {
        if(this.gameObject != null){
            Set<Cmd> commands = new HashSet<>(playerInputComponent.getCommands());
            if(!commands.isEmpty()) {
                for (Cmd command : commands) {
                    if (command == Cmd.PAUSE) {
                        this.getGameObject().getGame().setLastKeyPressed(Cmd.PAUSE);
                    }
                }
            }
        }
    }
}
