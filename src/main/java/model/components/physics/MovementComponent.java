package model.components.physics;


import model.CanadaPhysics;
import model.GameObject;
import model.components.Component;
import utils.Vector2;

public abstract class MovementComponent extends Component {

    protected CanadaPhysics physics;
    protected double movementSpeed;
    protected double velocityX;
    protected double velocityY;

    public MovementComponent(GameObject obj, double movementSpeed, CanadaPhysics physics) {
        super(obj);
        this.physics = physics;
        this.movementSpeed = movementSpeed;
    }
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    @Override
    public void update(double dt) {
        if(this.gameObject != null){
            Set<Cmd> commands = new HashSet<>(controller.getCommands());

            if(!commands.isEmpty()) {
                for (Cmd command : commands) {
                    if (command == Cmd.UP) {
                        this.velocityX -= 1;
                        this.velocityY -= 1;
                    } else if (command == Cmd.DOWN) {
                        this.velocityX += 1;
                        this.velocityY += 1;
                    } else if (command == Cmd.LEFT) {
                        this.velocityX -= 1;
                        this.velocityY += 1;
                    } else if (command == Cmd.RIGHT) {
                        this.velocityX += 1;
                        this.velocityY -= 1;
                    }
                }
                Vector2 v = Vector2.normalize(new Vector2(velocityX, velocityY));
                this.velocityX = v.X();
                this.velocityY = v.Y();
                physics.addToUpdate(this);
            }
        }
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void resetVelocity(){
        this.velocityX = 0d;
        this.velocityY = 0d;
    }
}
