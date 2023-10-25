package model.world.monsterState;

import model.components.AIComponent;

public class StateChase extends State{
    public StateChase(AIComponent aiComponent) {
        super(aiComponent);
    }

    @Override
    public void tick() {
        aiComponent.updateChase();
    }

    @Override
    public void onEnter() {
        aiComponent.chase();
    }

    @Override
    public void onExit() {
        aiComponent.stopChasing();
    }
}
