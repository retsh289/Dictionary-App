package admin.item;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.*;

public class ItemCard extends JPanel {
    private JLabel lbIcon;
    private JLabel lbTitle;
    private JLabel lbValues;
    
    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    private Color color1;
    private Color color2;

    public ItemCard() {
        initComponents();
        setOpaque(false);
        color1 = Color.BLACK;
        color2 = Color.WHITE;
    }

    public void setData(String icon, String title, String value) {
        lbIcon.setIcon(
        	new ImageIcon(
    			new ImageIcon(getClass().getResource("/icon/" + icon))
    			.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        	)
        );
        lbTitle.setText(title);
        lbValues.setText(value);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        lbIcon = new JLabel();
        lbTitle = new JLabel();
        lbValues = new JLabel();

        lbTitle.setFont(new Font("sansserif", 1, 24));
        lbTitle.setForeground(new Color(255, 255, 255));
        lbTitle.setText("Title");

        lbValues.setFont(new Font("sansserif", 1, 18)); 
        lbValues.setForeground(new Color(255, 255, 255));
        lbValues.setText("Values");


        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lbValues)
                    .addComponent(lbTitle)
                    .addComponent(lbIcon))
                .addContainerGap(283, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lbIcon)
                .addGap(18, 18, 18)
                .addComponent(lbTitle)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbValues)
                .addGap(18, 18, 18)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.setColor(new Color(255, 255, 255, 50));
        g2.fillOval(getWidth() - (getHeight() / 2), 10, getHeight(), getHeight());
        g2.fillOval(getWidth() - (getHeight() / 2) - 20, getHeight() / 2 + 20, getHeight(), getHeight());
        super.paintComponent(grphcs);
    }

    
}
