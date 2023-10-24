package model.world.monsterState;

public interface IState {

    public void tick();
    public void onEnter();
    public void onExit();
}
