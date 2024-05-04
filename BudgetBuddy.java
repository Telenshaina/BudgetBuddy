import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.font.TextAttribute;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

public class BudgetBuddy extends JFrame{
    public BudgetBuddy(){

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

        // CENTER
        JPanel centerPanel = new JPanel(null);
        centerPanel.setBackground(new Color(253,248,244));
        centerPanel.setPreferredSize(new Dimension(525, 720));

        JPanel sumPanel = new RoundedPanel(30, 30);
        sumPanel.setBounds(16, 45, 495, 30);
        sumPanel.setBackground(new Color(255,219,195));

        JLabel sumLabel = new JLabel("S U M M A R Y  O F  B I L L S");
        sumLabel.setForeground(new Color(25,9,51));
        sumLabel.setFont(new Font("Canva Sans", Font.BOLD, 12));
        sumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sumPanel.add(sumLabel);
        centerPanel.add(sumPanel);

        DefaultTableModel taskTable = new DefaultTableModel();
        taskTable.addColumn("BILL");
        taskTable.addColumn("DUE DATE");
        taskTable.addColumn("COST");
        taskTable.setRowCount(12);

        JTable table = new JTable(taskTable);
        table.setBackground(new Color(253,248,244));
        table.setBounds(10, 50, 300, 600);

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(255,219,195));
        header.setForeground(new Color(25,9,51));

        table.getTableHeader().setResizingAllowed(false);
        
        TableColumnModel columnModel = table.getColumnModel();
        TableColumn column1 = columnModel.getColumn(0);
        TableColumn column2 = columnModel.getColumn(1);
        TableColumn column3 = columnModel.getColumn(2);

        column1.setPreferredWidth(150);
        column2.setPreferredWidth(50);
        column3.setPreferredWidth(40);
        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 35));
        table.setRowHeight(34);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 80, 490, 446);
        centerPanel.add(scrollPane);
    
        JLabel budgetLabel2 = new JLabel("BUDGET: ");
        budgetLabel2.setForeground(new Color(25,9,51));
        budgetLabel2.setFont(smallFont);
        budgetLabel2.setBounds(30, 540, 100, 30);
        budgetLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
        centerPanel.add(budgetLabel2);

        JTextField budgetText = new JTextField(20);
        budgetText.setForeground(new Color(25,9,51));
        budgetText.setFont(smallFont);
        budgetText.setBorder(BorderFactory.createEmptyBorder());
        budgetText.setBounds(140, 540, 300, 30);
        budgetText.setOpaque(false);
        budgetText.setText("₱ ");
        budgetText.setEditable(false);
        centerPanel.add(budgetText);

        JLabel billsLabel = new JLabel("BILLS: ");
        billsLabel.setForeground(new Color(25,9,51));
        billsLabel.setBackground(new Color(253,248,244));
        billsLabel.setFont(smallFont);
        billsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        billsLabel.setBounds(30, 560, 100, 30);
        centerPanel.add(billsLabel);

        JTextField billsText = new JTextField(20);
        billsText.setForeground(new Color(25,9,51));
        billsText.setFont(smallFont);
        billsText.setBorder(BorderFactory.createEmptyBorder());
        billsText.setBounds(140, 560, 300, 30);
        billsText.setOpaque(false);
        billsText.setText("₱ ");
        billsText.setEditable(false);
        centerPanel.add(billsText);

        JLabel expensesLabel = new JLabel("EXPENSES: ");
        expensesLabel.setForeground(new Color(25,9,51));
        expensesLabel.setFont(smallFont);
        expensesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        expensesLabel.setBounds(30, 580, 100, 30);
        centerPanel.add(expensesLabel);

        JTextField expensesText = new JTextField(20);
        expensesText.setForeground(new Color(25,9,51));
        expensesText.setFont(smallFont);
        expensesText.setBorder(BorderFactory.createEmptyBorder());
        expensesText.setBounds(140, 580, 300, 30);
        expensesText.setOpaque(false);
        expensesText.setText("₱ ");
        expensesText.setEditable(false);
        centerPanel.add(expensesText);

        JLabel savedLabel = new JLabel("SAVED: ");
        savedLabel.setForeground(new Color(25,9,51));
        savedLabel.setFont(smallFont);
        savedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        savedLabel.setBounds(30, 600, 100, 30);
        centerPanel.add(savedLabel);

        JTextField savedText = new JTextField(20);
        savedText.setForeground(new Color(25,9,51));
        savedText.setFont(smallFont);
        savedText.setBorder(BorderFactory.createEmptyBorder());
        savedText.setBounds(140, 600, 300, 30);
        savedText.setOpaque(false);
        savedText.setText("₱ ");
        savedText.setEditable(false);
        centerPanel.add(savedText);

        // RIGHT
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(253,248,244));
        rightPanel.setPreferredSize(new Dimension(360, 720));
        rightPanel.setLayout(null);

        JPanel expPanel = new RoundedPanel(30, 30);
        expPanel.setBounds(16, 45, 320, 30);
        expPanel.setBackground(new Color(255,219,195));
        expPanel.setBorder(BorderFactory.createEmptyBorder());

        JLabel expLabel = new JLabel("O T H E R  E X P E N S E S");
        expLabel.setForeground(new Color(25,9,51));
        expLabel.setFont(new Font("Canva Sans", Font.BOLD, 12));
        expLabel.setHorizontalAlignment(SwingConstants.CENTER);
        expPanel.add(expLabel);
        rightPanel.add(expPanel);

        taskTable = new DefaultTableModel();
        taskTable.addColumn("ITEM");
        taskTable.addColumn("COST");
        taskTable.setRowCount(12);

        table = new JTable(taskTable);
        table.setBackground(new Color(253,248,244));
        table.setBounds(10, 50, 300, 600);

        JTableHeader header2 = table.getTableHeader();
        header2.setBackground(new Color(255, 219, 195));
        header2.setForeground(new Color(25, 9, 51));

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        TableColumnModel columnModel2 = table.getColumnModel();
        TableColumn column4 = columnModel.getColumn(0);
        TableColumn column5 = columnModel.getColumn(1);
        column4.setPreferredWidth(175);
        column5.setPreferredWidth(25);

        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 35));
        table.setRowHeight((int) 34);

        column1.setCellEditor(new DefaultCellEditor(new JTextField()));
        column2.setCellEditor(new DefaultCellEditor(new JTextField()));

        JScrollPane scrollPane2 = new JScrollPane(table);
        scrollPane2.setBounds(15, 80, 320, 446);

        rightPanel.add(scrollPane2);

        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        
        setVisible(true);
    }


    
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new BudgetBuddy());
    }
}
