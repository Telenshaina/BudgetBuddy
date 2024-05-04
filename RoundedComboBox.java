import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class RoundedComboBox extends BasicComboBoxUI {
    private final int arcWidth;
    private final int arcHeight;

    public RoundedComboBox(int arcWidth, int arcHeight) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    @Override
    protected JButton createArrowButton() {
    JButton button = new JButton("v");
    button.setFocusPainted(false);
    button.setFont(new Font("Arial", Font.PLAIN, 14)); 
    button.setBackground(Color.WHITE);
    return button;
    }

    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color backgroundColor = hasFocus ? Color.LIGHT_GRAY : Color.WHITE; 
        g2d.setColor(backgroundColor);
        g2d.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, arcWidth, arcHeight);
        g2d.dispose();
    }

    @Override
    public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
        super.paintCurrentValue(g, bounds, hasFocus); 
        
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(25,9,51,255));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(bounds.x, bounds.y, bounds.width - 1, bounds.height - 1, arcWidth, arcHeight);

        g2d.dispose(); 
    }
}
