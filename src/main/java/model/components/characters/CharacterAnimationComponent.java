package model.components.characters;

import model.GameObject;
import model.components.Component;
import model.components.attacks.MeleeAttackComponent;
import model.components.attacks.RangedAttackComponent;
import model.components.physics.MovementComponent;
import model.components.rendering.AnimatedSpriteRendererComponent;
import model.fsm.ICondition;
import model.fsm.StateMachine;
import model.fsm.states.animations.*;
import utils.SpriteSheet;

public class CharacterAnimationComponent extends Component {

    private MovementComponent movement;
    private MeleeAttackComponent meleeAttack;
    private RangedAttackComponent rangedAttack;
    private SwimComponent swim;
    private AnimatedSpriteRendererComponent renderer;
    private StateMachine stateMachine;

    public CharacterAnimationComponent(GameObject obj, MovementComponent movement, MeleeAttackComponent meleeAttack, RangedAttackComponent rangedAttack, SwimComponent swim, AnimatedSpriteRendererComponent renderer, SpriteSheet idleSprite, SpriteSheet walkingSprite, SpriteSheet fightingSprite, SpriteSheet slingshotSprite, SpriteSheet learningSwimSprite, SpriteSheet swimmingSprite) {
        super(obj);

        this.movement = movement;
        this.meleeAttack = meleeAttack;
        this.rangedAttack = rangedAttack;
        this.swim = swim;
        this.renderer = renderer;
        this.stateMachine = new StateMachine();

        IdleAnimation idle = new IdleAnimation(this, idleSprite);
        WalkingAnimation walking = new WalkingAnimation(this, walkingSprite);
        MeleeAttackAnimation attackingMelee = new MeleeAttackAnimation(this, fightingSprite);

        ICondition isWalking = () -> movement.isMoving();
        ICondition isIdle = () -> !(movement.isMoving());
        ICondition isAttackingMelee = () -> meleeAttack.isAttacking();
        ICondition meleeAttackFinished = () -> !(meleeAttack.isAttacking());

        stateMachine.addTransition(idle, walking, isWalking);
        stateMachine.addTransition(walking, idle, isIdle);

        stateMachine.addAnyTransition(attackingMelee, isAttackingMelee);
        stateMachine.addTransition(attackingMelee, idle, meleeAttackFinished);

        if(rangedAttack != null) {
            RangedAttackAnimation attackingRanged = new RangedAttackAnimation(this, slingshotSprite);
            ICondition isAttackingRanged = () -> rangedAttack.isAttacking();
            ICondition rangedAttackFinished = () -> !(rangedAttack.isAttacking());
            stateMachine.addTransition(idle, attackingRanged, isAttackingRanged);
            stateMachine.addTransition(walking, attackingRanged, isAttackingRanged);
            stateMachine.addTransition(attackingRanged, idle, rangedAttackFinished);
        }

        if(swim != null) {
            if(learningSwimSprite != null) {
                LearningSwimAnimation learningSwim = new LearningSwimAnimation(this, learningSwimSprite);
                ICondition isLearningSwim = () -> swim.isLearningSwim();
                ICondition learningSwimFinished = () -> !(swim.isLearningSwim());
                stateMachine.addTransition(idle, learningSwim, isLearningSwim);
                stateMachine.addTransition(walking, learningSwim, isLearningSwim);
                stateMachine.addTransition(learningSwim, idle, learningSwimFinished);
            }
            SwimmingAnimation swimming = new SwimmingAnimation(this, swimmingSprite);
            ICondition isSwimming = () -> swim.isSwimming();
            ICondition stopSwimming = () -> !(swim.isSwimming());
            stateMachine.addTransition(idle, swimming, isSwimming);
            stateMachine.addTransition(walking, swimming, isSwimming);
            stateMachine.addTransition(swimming, idle, stopSwimming);
        }

        stateMachine.setState(idle);

    }

    @Override
    public void update() {

        stateMachine.tick();
    }

    public void setCurrentSprite(SpriteSheet sprite){
        renderer.setSprite(sprite);
    }

    public void setRowIndex(int index){
        renderer.setRowIndex(index);
    }

    public double getDirectionAngle(){
        return movement.getDirectionAngle();
    }

}
