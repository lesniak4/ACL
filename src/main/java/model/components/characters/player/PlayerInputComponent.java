package model.components.characters.player;

import engine.Cmd;

import engine.IGameController;
import model.GameObject;
import model.components.Component;
import model.components.attacks.MeleeAttackComponent;
import model.components.attacks.RangedAttackComponent;
import model.components.attacks.StunComponent;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class PlayerInputComponent extends Component {

    protected IGameController controller;

    private MeleeAttackComponent meleeAttackComponent;
    private RangedAttackComponent rangedAttackComponent;
    private StunComponent stunComponent;

    private Set<Cmd> processedCmd;

    public PlayerInputComponent(GameObject obj, IGameController controller){
        super(obj);
        this.controller = controller;

        processedCmd = new HashSet<>();

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
                    if (command == Cmd.MELEE_ATTACK && meleeAttackComponent != null && !processedCmd.contains(Cmd.MELEE_ATTACK)) {
                        if(!meleeAttackComponent.isAttacking()) {
                            this.getGameObject().getGame().setLastKeyPressed(Cmd.MELEE_ATTACK);
                            meleeAttackComponent.attack();
                        }
                    }
                    if (command == Cmd.RANGED_ATTACK && rangedAttackComponent != null && !processedCmd.contains(Cmd.RANGED_ATTACK)) {
                        if(!rangedAttackComponent.isAttacking()) {
                            this.getGameObject().getGame().setLastKeyPressed(Cmd.RANGED_ATTACK);
                            rangedAttackComponent.attack();
                        }
                    }
                }
            }
            processedCmd.clear();
            processedCmd.addAll(commands);
        }
    }

    public void setMeleeAttackComponent(MeleeAttackComponent meleeAttackComponent){
        this.meleeAttackComponent = meleeAttackComponent;
    }

    public void setRangedAttackComponent(RangedAttackComponent rangedAttackComponent){
        this.rangedAttackComponent = rangedAttackComponent;
    }

    public void setStunComponent(StunComponent stunComponent){
        this.stunComponent = stunComponent;
    }
}