package model.components.attacks;

import model.GameObject;
import model.components.Component;
import views.HealthBarView;

public class HealthComponent extends Component{

    private int defaultHealth;
    private int currentHealth;

    private HealthBarView view;

    public HealthComponent(GameObject obj, int defaultHealth) {
        super(obj);

        this.defaultHealth = defaultHealth;
        this.currentHealth = defaultHealth;
    }

    @Override
    public void update() {

    }

    public HealthBarView getView(){
        return this.view;
    }

    public void setView(HealthBarView view){
        this.view = view;
    }

    public int getCurrentHealth(){
        return this.currentHealth;
    }

    public void takeDamage(int damage){
        this.currentHealth -= damage;
        if(isDead()) {
            getGameObject().destroyGameObject();
        }
    }

    public boolean isDead(){
        return this.currentHealth <= 0;
    }

    public int getHealthOn100(){
        if(this.defaultHealth > 0)
            return (int)((this.currentHealth * 100) / this.defaultHealth);
        else
            return 0;
    }
}
