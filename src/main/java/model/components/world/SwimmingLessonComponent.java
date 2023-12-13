package model.components.world;

import model.GameObject;
import model.components.Component;
import model.components.characters.SwimComponent;
import model.components.physics.ColliderComponent;
import model.components.physics.ICollidable;
import model.components.physics.PlayerMovementComponent;

public class SwimmingLessonComponent extends Component implements ICollidable {
    private boolean currentlyLearning;

    private int framesBeforeFinishedLearning;
    private SwimComponent learner;

    public SwimmingLessonComponent(GameObject obj, int learnDurationFrameCount) {
        super(obj);

        this.currentlyLearning = false;
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
                this.stopLearning();
                this.learner.setCanSwim(true);
            }
        }
    }

    @Override
    public void subscribeToCollider(ColliderComponent collider) {

        collider.addCollidableComponent(this);
    }

    @Override
    public void onCollisionEnter(GameObject colliderObj) {

        SwimComponent s = colliderObj.getComponent(SwimComponent.class);
        if(s != null){
            if(!s.canSwim()) {
                if (!isCurrentlyLearning()) {
                    startLearning(s);
                }
            }else{
                s.startSwimming();
            }
        }
    }

    @Override
    public void onCollisionExit(GameObject colliderObj) {

        SwimComponent s = colliderObj.getComponent(SwimComponent.class);
        if (s != null) {
            if(!s.canSwim()) {
                stopLearning();
            }else{
                s.stopSwimming();
            }
        }
    }
}