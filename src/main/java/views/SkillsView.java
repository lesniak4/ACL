package views;

import engine.Cmd;
import model.CanadaGame;
import model.skills.PlayerSkill;
import utils.GameConfig;
import utils.SpriteLoader;

import javax.swing.*;
import java.awt.*;

public class SkillsView extends UIView{

    private JLabel speedLabel;
    private JLabel invisibleLabel;


    public SkillsView(CanadaGame game) {
        super(game);
    }

    @Override
    public void buildView() {

        GameConfig gc = GameConfig.getInstance();

        int width = gc.getWinWidth()/3;
        this.setBounds((gc.getWinWidth() - width)/2, 0, width, (int)(gc.getWinHeight()* 0.07));
        this.setBackground(new Color(40,40,40,190));

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        speedLabel = new JLabel("Speed");
        invisibleLabel = new JLabel("Invisible");

        speedLabel.setForeground(Color.WHITE);
        invisibleLabel.setForeground(Color.WHITE);

        this.add(speedLabel);
        this.add(invisibleLabel);

        layout.putConstraint(SpringLayout.WEST, speedLabel, 15, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, invisibleLabel, 15, SpringLayout.EAST, speedLabel);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, speedLabel, 0, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, invisibleLabel, 0, SpringLayout.VERTICAL_CENTER, this);
    }

    @Override
    public void update() {

        updateSkillLabel(speedLabel, Cmd.SKILL_1);
        updateSkillLabel(invisibleLabel, Cmd.SKILL_2);
    }

    public void updateSkillLabel(JLabel label, Cmd cmd){

        PlayerSkill skill = game.getSkills().getSkill(cmd);
        if(game.getSkills().isSkillAvailable(cmd)){
            label.setText(skill.toString() + " (" + skill.getCost() + ")");
            label.setForeground(Color.green);
        }else{
            label.setText(skill.toString() + " (" + (int)game.getSkills().getCooldown(cmd) + " s)");
            label.setForeground(Color.red);
        }
    }
}
