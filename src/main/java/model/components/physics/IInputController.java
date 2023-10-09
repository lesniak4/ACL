package model.components.physics;

import engine.Cmd;

import java.util.LinkedList;
import java.util.Set;

public interface IInputController {
    /**
     * quand on demande les commandes, le controleur retourne la commande en
     * cours
     *
     * @return commande faite par le joueur
     */
    public Set<Cmd> getCommands();
}
