package model.components.world;

import model.GameObject;
import model.components.Component;
import model.components.characters.SwimComponent;

public class SwimmingLessonComponent extends Component {
    private boolean currentlyLearning;
    private boolean finishedLearning;

    private int framesBeforeFinishedLearning;
    private SwimComponent learner;

    public SwimmingLessonComponent(GameObject obj, int learnDurationFrameCount) {
        super(obj);

        this.currentlyLearning = false;
        this.finishedLearning = false;
        this.framesBeforeFinishedLearning = learnDurationFrameCount;
    }

    public void startLearning(SwimComponent learner) {
        this.currentlyLearning = true;
        this.learner = learner;
        this.learner.startLearning();
    }

    public void stopLearning() {

        this.currentlyLearning = false;
        if(learner != null) {
            this.learner.stopLearning();
        }
    }

    public boolean isCurrentlyLearning() {
        return currentlyLearning;
    }

    @Override
    public void update() {

        if(this.currentlyLearning){
            framesBeforeFinishedLearning--;
            if(framesBeforeFinishedLearning <= 0){
                this.finishedLearning = true;
                this.stopLearning();
                this.learner.setCanSwim(true);
            }
        }
    }
}