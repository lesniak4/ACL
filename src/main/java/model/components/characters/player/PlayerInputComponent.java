package model.components.characters.player;

import engine.Cmd;

import engine.IGameController;
import model.GameObject;
import model.components.Component;
import model.components.attacks.MeleeAttackComponent;
import model.components.attacks.StunComponent;

import java.util.HashSet;
import java.util.Set;

public class PlayerInputComponent extends Component {

    protected IGameController controller;

    private MeleeAttackComponent meleeAttackComponent;
    private StunComponent stunComponent;

    public PlayerInputComponent(GameObject obj, IGameController controller){
        super(obj);
        this.controller = controller;

    }

    public Set<Cmd> getCommands() {

        if(stunComponent == null || !stunComponent.isStun()) {
            return controller.getCommands();
        }else{
            return new HashSet<>();
        }
    }

    @Override
    public void update() {

        if(this.gameObject != null){
            Set<Cmd> commands = new HashSet<>(getCommands());
            if(!commands.isEmpty()) {
                for (Cmd command : commands) {
                    if (command == Cmd.MELEE_ATTACK && meleeAttackComponent != null) {
                        if(!meleeAttackComponent.isAttacking()) {
                            this.getGameObject().getGame().setLastKeyPressed(Cmd.MELEE_ATTACK);
                            meleeAttackComponent.attack();
                        }
                    }
                }
            }
        }
    }

    public void setMeleeAttackComponent(MeleeAttackComponent meleeAttackComponent){
        this.meleeAttackComponent = meleeAttackComponent;
    }

    public void setStunComponent(StunComponent stunComponent){
        this.stunComponent = stunComponent;
    }
}