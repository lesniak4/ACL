package model.components.attacks;

import model.GameObject;
import model.components.Component;

public class HealthComponent extends Component{

    private int defaultHealth;
    private int currentHealth;

    public HealthComponent(GameObject obj, int defaultHealth) {
        super(obj);

        this.defaultHealth = defaultHealth;
        this.currentHealth = defaultHealth;
    }

    @Override
    public void update() {

    }

    public void takeDamage(int damage){
        this.currentHealth -= damage;
        if(this.currentHealth <= 0) {
            getGameObject().destroyGameObject();
        }
    }
}
