package model.components.characters.player.skills;

import model.GameObject;
import model.components.Component;
import model.components.characters.StatsComponent;

public abstract class PlayerStatsModifierComponent extends Component {

    protected StatsComponent stats;
    protected long startTime;
    protected int duration;

    public PlayerStatsModifierComponent(GameObject obj, StatsComponent stats, int durationInMS) {
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
            this.destroyComponent();
        }
    }

    public abstract void applyModifier();
    public abstract void resetModifier();
}
