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

    public int getCurrentHealth(){
        return this.currentHealth;
    }

    public void takeDamage(int damage){
        this.currentHealth -= damage;
        if(this.currentHealth <= 0) {
            getGameObject().destroyGameObject();
        }
    }

    public int getHealthOn100(){
        if(this.defaultHealth > 0)
            return (int)((this.currentHealth * 100) / this.defaultHealth);
        else
            return 0;
    }
}
