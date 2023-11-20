package model.components.player.skills;

import model.GameObject;
import model.components.Component;
import model.components.player.PlayerStatsComponent;

public abstract class PlayerStatsModifierComponent extends Component {

    protected PlayerStatsComponent stats;
    protected long startTime;
    protected int duration;

    public PlayerStatsModifierComponent(GameObject obj, PlayerStatsComponent stats, int durationInMS) {
        super(obj);

        this.stats = stats;
        this.duration = durationInMS;

        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void update() {

        applyModifier();
        if(System.currentTimeMillis() - startTime > duration){
            resetModifier();
            System.out.println("Fin du modifier");
            this.destroyComponent();
        }
    }

    public abstract void applyModifier();
    public abstract void resetModifier();
}
