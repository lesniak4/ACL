package model.components.characters;

import model.GameObject;
import model.components.Component;

public class SwimComponent extends Component {

    protected boolean isLearningSwim;
    protected boolean canSwim;
    protected boolean isSwimming;

    public SwimComponent(GameObject obj, boolean canSwim) {
        super(obj);

        this.isLearningSwim = false;
        this.canSwim = canSwim;
    }

    @Override
    public void update() {

    }

    public void startLearning(){
        this.isLearningSwim = true;
    }

    public void stopLearning(){
        this.isLearningSwim = false;
    }

    public boolean isLearningSwim() {
        return isLearningSwim;
    }

    public boolean canSwim() {
        return canSwim;
    }

    public void startSwimming(){
        this.isSwimming = true;
    }

    public void stopSwimming(){
        this.isSwimming = false;
    }

    public boolean isSwimming() {
        return isSwimming;
    }

    public void setCanSwim(boolean canSwim) {
        this.canSwim = canSwim;
    }
}
