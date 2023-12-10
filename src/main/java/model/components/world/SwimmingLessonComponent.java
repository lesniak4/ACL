package model.components.world;

import model.GameObject;
import model.components.Component;

public class SwimmingLessonComponent extends Component {
    private boolean currentlyLearning;

    public SwimmingLessonComponent(GameObject obj) {
        super(obj);

        this.currentlyLearning = false;
    }

    public void startLearning() {
        this.currentlyLearning = true;
    }

    public boolean isCurrentlyLearning() {
        return currentlyLearning;
    }

    public boolean finishedLearning() {
        return true;
    }

    @Override
    public void update() {
    }
}