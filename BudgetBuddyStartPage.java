import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BudgetBuddyStartPage extends JFrame {

    public BudgetBuddyStartPage(){

        setUndecorated(true);
        setTitle("Budget Buddy");
        setSize(1270, 710);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Font bFont = new Font("Canva Sans", Font.BOLD, 40);
        Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) bFont.getAttributes();
        attributes.put(TextAttribute.TRACKING, 0.2);
        Font bigFont = bFont.deriveFont(attributes);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(253,248,244,255));
        mainPanel.setLayout(null);

        // si buddy Budget, guys B-)
        try {
            ImageIcon logo = new ImageIcon("C:/Users/TATOY/Desktop/BudgetBuddy/budget(2).png"); //change to the path where you put the image
            Image image = logo.getImage();
            
            Image scaledImage = image.getScaledInstance(650, 650, Image.SCALE_SMOOTH);
            ImageIcon scaledLogo = new ImageIcon(scaledImage);

            JLabel logoLabel = new JLabel(scaledLogo);
            logoLabel.setBounds(60, 190, 650, 650);
            mainPanel.add(logoLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel appNameLabel = new JLabel("BUDGET");
        appNameLabel.setFont(bigFont);
        appNameLabel.setForeground(new Color(25,9,51,255));
        appNameLabel.setBounds(60, 50, 500, 50);
        mainPanel.add(appNameLabel);

        JLabel appNameLabel2 = new JLabel("BUDDY");
        appNameLabel2.setFont(bigFont);
        appNameLabel2.setForeground(new Color(25,9,51,255));
        appNameLabel2.setBounds(60, 90, 500, 50);
        mainPanel.add(appNameLabel2);

        String message = "<html>" +
                "<p style='text-align: left; font-family: Canva Sans; font-weight: bold; font-size: 11px; color: #190933;'>" +
                "Hi! I am Budget, your new Buddy in tracking your expenses!" + "</p>" +
                "<p><br></p>" +
                "<p style='text-align: justify; font-family: Canva Sans; font-weight: normal; font-size: 11px; color: #190933; text-indent: 30px;'>" + 
                " I am here to help you organize your bills and expenses so you can stay on top of your finances." +
                " With me, you can organize your expenses by priority, track daily costs like grabbing a snack," +
                " and work with a fixed budget to see how everything adds up." +
                " I'll also generate a summary of all your bills, side expenses, and what's left for savings." +
                " Let's make managing your budget easier and more fun!" +
                "<br><br> Ready to get started?" +
                "</p>" +
                "</html>";

        JLabel messageLabel = new JLabel(message);
        messageLabel.setBounds(620, 180, 420, 310);
        mainPanel.add(messageLabel); 

        RoundedButton goButton = new RoundedButton("Let's go!", 40, 40);
        goButton.setForeground(new Color(25,9,51,255));
        goButton.setBackground(new Color(255,219,195,255));
        goButton.setBounds(780, 475, 150, 35);
        mainPanel.add(goButton);
        
        goButton.addActionListener(e -> {
            new BudgetBuddy();
            this.dispose();
        });

        RoundedButton exitButton = new RoundedButton("No, thanks.", 40, 40);
        exitButton.setForeground(new Color(25,9,51,255));
        exitButton.setBackground(new Color(255,219,195,255));
        exitButton.setBounds(615, 475, 150, 35);
        mainPanel.add(exitButton);

        exitButton.addActionListener(e -> {
            int click = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit? :<", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (click == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        });


        JPanel horizontalLineTop = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;  
                g2d.setColor(new Color(25, 9, 51, 255)); 
                g2d.setStroke(new BasicStroke(3));  
                g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2); 
            }
        };
        horizontalLineTop.setBounds(35, 40, 1200, 3);

        JPanel horizontalLineBottom = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g; 
                g2d.setColor(new Color(25, 9, 51, 255));  
                g2d.setStroke(new BasicStroke(3));

                g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2); 
            }
        };
        horizontalLineBottom.setBounds(35, 670, 1200, 3); 

        JPanel verticalLineLeft = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(25, 9, 51, 255)); 
                g2d.setStroke(new BasicStroke(5)); 

                g2d.drawLine(getWidth() / 2, 0, getWidth() /2, getHeight()); 
            }
        };
        verticalLineLeft.setBounds(35, 41, 3,630); 

        JPanel verticalLineRight = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(25, 9, 51, 255)); 
                g2d.setStroke(new BasicStroke(5));

                g2d.drawLine(getWidth() / 2, 0, getWidth() /2, getHeight()); 
            }
        };
        verticalLineRight.setBounds(1232, 41, 3,630); 

        mainPanel.add(verticalLineLeft);
        mainPanel.add(verticalLineRight);
        mainPanel.add(horizontalLineTop);
        mainPanel.add(horizontalLineBottom);
        
        add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new BudgetBuddyStartPage());
    }
}

