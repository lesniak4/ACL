package model.components.player.skills;

import model.GameObject;
import model.components.Component;
import model.components.player.PlayerStatsComponent;

public abstract class PlayerStatsModifierComponent extends Component {

    protected PlayerStatsComponent stats;
    protected long startTime;
    protected int duration;

    public PlayerStatsModifierComponent(GameObject obj, PlayerStatsComponent stats, int duration) {
        super(obj);

        this.stats = stats;
        this.duration = duration;

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
