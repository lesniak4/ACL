package model.fsm;

public interface IState {

    public void tick(float dt);
    public void onEnter();
    public void onExit();
}
