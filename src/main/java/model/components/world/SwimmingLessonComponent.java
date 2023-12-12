package model.components.world;

import model.GameObject;
import model.components.Component;
import model.components.physics.MovementComponent;

public class SwimmingLessonComponent extends Component {
    private boolean currentlyLearning;
    private boolean finishedLearning;

    private int framesBeforeFinishedLearning;
    private MovementComponent learner;

    public SwimmingLessonComponent(GameObject obj, int learnDurationFrameCount) {
        super(obj);

        this.currentlyLearning = false;
        this.finishedLearning = false;
        this.framesBeforeFinishedLearning = learnDurationFrameCount;
    }

    public void startLearning(MovementComponent learner) {
        this.currentlyLearning = true;
        this.learner = learner;
    }

    public void stopLearning() {
        this.currentlyLearning = false;
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
                System.out.println("Finished learning");
            }
        }
    }
}