import java.awt.*;
import javax.swing.*;
import java.awt.font.TextAttribute;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

public class BudgetBuddyUI extends JFrame{
    public BudgetBuddyUI(){

        setTitle("Budget Buddy");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Font bFont = new Font("Canva Sans", Font.BOLD, 50);
        Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) bFont.getAttributes();
        attributes.put(TextAttribute.TRACKING, 0.2); 
        Font bigFont = bFont.deriveFont(attributes);

        Font sFont = new Font("Canva Sans", Font.BOLD, 13);
        Map<TextAttribute, Object> attributeSmall = (Map<TextAttribute, Object>) sFont.getAttributes();
        attributeSmall.put(TextAttribute.TRACKING, 0.1); 
        Font smallFont = sFont.deriveFont(attributeSmall);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(395, 720));
        leftPanel.setBackground(new Color(253,248,244,255));

        JPanel leftUpperPanel = new JPanel(null);
        leftUpperPanel.setPreferredSize(new Dimension(395, 240));
        leftUpperPanel.setBackground(new Color(253,248,244,255));
        leftPanel.add(leftUpperPanel, BorderLayout.NORTH);

        try {
            ImageIcon logo = new ImageIcon("C:/Users/TATOY/Desktop/BudgetBuddy/testLogo.png");
            Image image = logo.getImage();
            Image scaledImage = image.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            ImageIcon scaledLogo = new ImageIcon(scaledImage);
        
            JLabel logoLabel = new JLabel(scaledLogo);
            logoLabel.setBounds(30, 35, 100, 100);
            leftUpperPanel.add(logoLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JSeparator line = new JSeparator(SwingConstants.VERTICAL);
        line.setBounds(130, 50, 20, 70);
        line.setBackground(new Color(25,9,51,255));
        leftUpperPanel.add(line);

        JLabel appNameLabel = new JLabel("BUDGET BUDDY");
        appNameLabel.setFont(new Font("Canva Sans", Font.BOLD, 25));
        appNameLabel.setForeground(new Color(25,9,51,255));
        appNameLabel.setBounds(150, 50, 350, 50);
        leftUpperPanel.add(appNameLabel);

        JLabel subLabel = new JLabel("-- your friendly expense tracker");
        subLabel.setFont(new Font("Canva Sans", Font.PLAIN, 12));
        subLabel.setForeground(new Color(25,9,51,255));
        subLabel.setBounds(150, 70, 350, 50);
        leftUpperPanel.add(subLabel);

        JLabel monthLabel = new JLabel("APRIL");
        monthLabel.setFont(bigFont);
        monthLabel.setForeground(new Color(25,9,51,255));
        monthLabel.setBounds(100, 160, 350, 50);
        leftUpperPanel.add(monthLabel);

        JPanel leftCenterPanel = new JPanel(null);
        leftCenterPanel.setPreferredSize(new Dimension(395, 290));
        leftCenterPanel.setBackground(new Color(253,248,244,255));
        leftPanel.add(leftCenterPanel, BorderLayout.CENTER);

        JLabel budgetLabel = new JLabel("BUDGET");
        budgetLabel.setFont(smallFont);
        budgetLabel.setForeground(new Color(25,9,51,255));
        budgetLabel.setBounds(30, 10, 100, 20);
        leftCenterPanel.add(budgetLabel);

        JLabel pesoSign = new JLabel("  ₱");
        pesoSign.setFont(smallFont);
        pesoSign.setForeground(new Color(25,9,51,255));
        pesoSign.setBounds(35, 37, 100, 20);
        leftCenterPanel.add(pesoSign);

        RoundedTextField budgetTF = new RoundedTextField(5, 5, 5);
        budgetTF.setFont(new Font("Canva Sans", Font.BOLD, 12));
        budgetTF.setForeground(new Color(25,9,51,255));
        budgetTF.setBackground(Color.WHITE);
        budgetTF.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        budgetTF.setBounds(30, 30, 320, 35);
        leftCenterPanel.add(budgetTF);


        JLabel billLabel = new JLabel("BILL/EXPENSE NAME");
        billLabel.setFont(smallFont);
        billLabel.setForeground(new Color(25,9,51,255));
        billLabel.setBounds(30, 90, 300, 20);
        leftCenterPanel.add(billLabel);

        RoundedTextField billTF = new RoundedTextField(5, 5, 5);
        billTF.setFont(new Font("Canva Sans", Font.BOLD, 12));
        billTF.setForeground(new Color(25,9,51,255));
        billTF.setBackground(Color.WHITE);
        billTF.setBounds(30, 110, 320, 35);
        leftCenterPanel.add(billTF);

        JLabel costLabel = new JLabel("COST");
        costLabel.setFont(smallFont);
        costLabel.setForeground(new Color(25, 9, 51, 255));
        costLabel.setBounds(30, 170, 100, 20);
        leftCenterPanel.add(costLabel);

        JLabel pesoSign2 = new JLabel("  ₱");
        pesoSign2.setFont(smallFont);
        pesoSign2.setForeground(new Color(25,9,51,255));
        pesoSign2.setBounds(35, 197, 100, 20);
        leftCenterPanel.add(pesoSign2);

        RoundedTextField costTF = new RoundedTextField(5, 5, 5);
        costTF.setFont(new Font("Canva Sans", Font.BOLD, 12));
        costTF.setForeground(new Color(25,9,51,255));
        costTF.setBackground(Color.WHITE);
        costTF.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        costTF.setBounds(30, 190, 152, 35);
        leftCenterPanel.add(costTF);

        JLabel dueDateLabel = new JLabel("DUE DATE");
        dueDateLabel.setFont(smallFont);
        dueDateLabel.setForeground(new Color(25, 9, 51, 255));
        dueDateLabel.setBounds(195, 170, 100, 20);
        leftCenterPanel.add(dueDateLabel);

        JComboBox<String> dateCB = new JComboBox<>();
        dateCB.setUI(new RoundedComboBox(5, 5)); 
        dateCB.setFont(smallFont);
        dateCB.setForeground(new Color(25, 9, 51));
        dateCB.setBackground(Color.WHITE); // Background color
        dateCB.setBounds(195, 190, 152, 35);
        leftCenterPanel.add(dateCB);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("    MM/dd/yyyy", Locale.ENGLISH);
        Calendar calendar = new GregorianCalendar();
        calendar.set(2024, Calendar.JANUARY, 1); 
        int numberOfDays = 730;

        for (int i = 0; i < numberOfDays; i++) {
            java.util.Date date = calendar.getTime(); 
            String formattedDate = dateFormatter.format(date); 
            dateCB.addItem(formattedDate);
            calendar.add(Calendar.DAY_OF_YEAR, 1); 
        }

        JPanel leftLowerPanel = new JPanel(null);
        leftLowerPanel.setPreferredSize(new Dimension(395, 190));
        leftLowerPanel.setBackground(new Color(253,248,244,255));
        leftPanel.add(leftLowerPanel, BorderLayout.SOUTH);

        RoundedButton addButton = new RoundedButton("ADD TO LIST", 40, 40);
        addButton.setFont(new Font("Canva Sans", Font.BOLD, 12));
        addButton.setForeground(new Color(25,9,51,255));
        addButton.setBackground(Color.WHITE);
        addButton.setBounds(130, 15, 115, 37);
        addButton.setBorderColor(new Color(25,9,51,255));
        leftLowerPanel.add(addButton);

        RoundedButton generateButton = new RoundedButton("GENERATE SUMMARY", 40, 40);
        generateButton.setFont(new Font("Canva Sans", Font.BOLD, 12));
        generateButton.setForeground(new Color(25,9,51,255));
        generateButton.setBackground(new Color(255,219,195,255));
        generateButton.setBounds(100, 85, 170, 37);
        leftLowerPanel.add(generateButton);

        JPanel centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(525, 720));

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(360, 720));

        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        
        setVisible(true);
    }
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new BudgetBuddyUI());
    }
}
