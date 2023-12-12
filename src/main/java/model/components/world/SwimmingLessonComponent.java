package model.components.world;

import model.GameObject;
import model.components.Component;
import model.components.physics.PlayerMovementComponent;
import utils.GameConfig;

import java.util.*;

public class SwimmingLessonComponent extends Component {
    private boolean currentlyLearning;
    private boolean finishedLearning;

    public SwimmingLessonComponent(GameObject obj) {
        super(obj);

        this.currentlyLearning = false;
        this.finishedLearning = false;
    }

    public void startLearning() {
        this.currentlyLearning = true;
        learningCheck();
    }

    public void stopLearning() {
        this.currentlyLearning = false;
    }

    public void learningCheck() {
        Timer lesson = new Timer(false);
        lesson.schedule(new TimerTask() {
            @Override
            public void run() {
                if(isCurrentlyLearning()) {
                    setFinishedLearning(true);
                }
            }
        }, GameConfig.getInstance().getTimeNeededToLearn());
    }

    public boolean isCurrentlyLearning() {
        return currentlyLearning;
    }

    public void setFinishedLearning(boolean hasLearnt) {
        this.finishedLearning = hasLearnt;
    }

    public boolean finishedLearning() {
        return this.finishedLearning;
    }

    @Override
    public void update() {
    }
}