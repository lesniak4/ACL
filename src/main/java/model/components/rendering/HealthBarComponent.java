package model.components.rendering;

import model.GameObject;
import model.components.Component;

public class HealthBarComponent extends Component {

    private int baseHealth;

    private int currentHealth;

    public HealthBarComponent(GameObject obj, int baseHealth){
        super(obj);
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public void setHealth(int health) {
        this.currentHealth = health;
    }

    public void substractHealth(int damage) {
        this.currentHealth = this.currentHealth - damage;
    }

    public int getHealth() {
        return currentHealth;
    }

    public int getHealthOn100(){
        if(this.baseHealth > 0)
            return (int)((this.currentHealth * 100) / this.baseHealth);
        else
            return 0;
    }

    @Override
    public void update() {

    }
}
