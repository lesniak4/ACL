package utils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UIStyle {

    public static void setButtonStyle(JButton button){

        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(40,40,40,190));
        Border line = new LineBorder(Color.WHITE);
        Border margin = new EmptyBorder(20, 25, 20, 25);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        //startButton.setBounds(gc.getWinWidth() / 2, gc.getWinHeight() / 2, (int)(gc.getWinWidth() * 0.2), (int)(gc.getWinHeight() * 0.1));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
