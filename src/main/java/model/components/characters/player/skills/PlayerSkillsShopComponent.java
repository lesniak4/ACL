package model.components.characters.player.skills;

import engine.Cmd;
import model.GameObject;
import model.components.Component;
import model.components.characters.player.PlayerInputComponent;
import model.components.characters.StatsComponent;
import model.skills.PlayerSkill;
import model.skills.PlayerSkillDamage;
import model.skills.PlayerSkillInvisible;
import model.skills.PlayerSkillSpeed;
import utils.GameConfig;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PlayerSkillsShopComponent extends Component {

    private PlayerInputComponent playerInputComponent;
    private StatsComponent stats;

    private HashMap<Cmd, PlayerSkill> skills;
    private HashMap<Cmd, Long> lastTimesUsed;

    public PlayerSkillsShopComponent(GameObject obj, PlayerInputComponent playerInputComponent, StatsComponent stats) {
        super(obj);

        GameConfig gc = GameConfig.getInstance();

        this.playerInputComponent = playerInputComponent;
        this.stats = stats;

        this.skills = new HashMap<>(3);
        skills.put(Cmd.SKILL_1, new PlayerSkillSpeed(Cmd.SKILL_1, "Vitesse", gc.getSkill1Cost(), gc.getSkill1Cooldown()));
        skills.put(Cmd.SKILL_2, new PlayerSkillInvisible(Cmd.SKILL_2, "Invisibilité", gc.getSkill2Cost(), gc.getSkill2Cooldown()));
        skills.put(Cmd.SKILL_3, new PlayerSkillDamage(Cmd.SKILL_3, "Dégats", gc.getSkill3Cost(), gc.getSkill3Cooldown()));

        this.lastTimesUsed = new HashMap<>(3);
        for(Cmd cmd : skills.keySet()){
            lastTimesUsed.put(cmd, 0L);
        }
    }

    @Override
    public void update() {

        if(this.gameObject != null){
            Set<Cmd> commands = new HashSet<>(playerInputComponent.getCommands());

            if(!commands.isEmpty()) {
                for (Cmd command : commands) {
                    if(skills.containsKey(command)){

                        PlayerSkill skill = skills.get(command);
                        if(isSkillAvailable(command) && this.getGameObject().getGame().getScore() >= skill.getCost()) {
                            this.getGameObject().getGame().decrScore(skill.getCost());
                            lastTimesUsed.put(command, System.currentTimeMillis());
                            this.getGameObject().addComponent(skill.getNewModifierComponent(this.getGameObject(), stats));
                        }
                    }
                }
            }
        }
    }

    public boolean isSkillAvailable(Cmd cmd){
        PlayerSkill skill = skills.get(cmd);

        long currentTime = System.currentTimeMillis();
        return currentTime - lastTimesUsed.get(cmd) >= skill.getCooldown();
    }

    public PlayerSkill getSkill(Cmd cmd){
        return this.skills.get(cmd);
    }

    public float getCooldown(Cmd cmd){
        return (skills.get(cmd).getCooldown() - (System.currentTimeMillis() - lastTimesUsed.get(cmd))) / 1000f;
    }
}
