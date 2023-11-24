package engine;

import views.UIView;

import javax.swing.*;

public class UIPanel extends JPanel {

    private int width, height;

    public UIPanel(int width, int height) {
            super();

            this.width = width;
            this.height = height;
            this.setSize(this.width, this.height);
            this.setOpaque(false);
            this.setLayout(null);
    }

    public void addView(UIView view, Integer layer){
        this.add(view, layer);
    }

    public void removeView(UIView view){
        this.remove((JPanel)view);
        this.revalidate();
        this.repaint();
    }
}
