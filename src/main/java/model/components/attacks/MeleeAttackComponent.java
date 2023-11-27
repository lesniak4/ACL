package model.components.attacks;

import engine.Cmd;
import model.CanadaPhysics;
import model.GameObject;
import model.components.physics.MovementComponent;
import model.components.characters.player.PlayerInputComponent;
import model.components.characters.StatsComponent;
import utils.Vector2;

import java.util.HashSet;
import java.util.Set;

public class MeleeAttackComponent extends AttackComponent{


    public MeleeAttackComponent(GameObject obj, StatsComponent stats, MovementComponent movement, CanadaPhysics physics, double radius, int stunFrameCount, int lifetimeFrameCount) {
        super(obj, stats, movement, physics, radius, stunFrameCount, lifetimeFrameCount);
    }

    @Override
    public void update() {

        super.update();
    }


}
