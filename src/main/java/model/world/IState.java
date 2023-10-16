package model.world;

public interface IState {

    public void tick();
    public void onEnter();
    public void onExit();
}
