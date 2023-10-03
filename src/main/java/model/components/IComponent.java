package model.components;

import model.GameObject;

public interface IComponent {

    public void update(GameObject obj, double dt);
}
