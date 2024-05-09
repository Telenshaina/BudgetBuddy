package BudgetBuddy;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.font.TextAttribute;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class BudgetBuddy extends JFrame{
    private JTextField budgetText, billsText, savedText, expensesText, totalSumField;
    private DefaultTableModel taskTable, taskTable1;
    private double totalSumValue;
    private List<Double> expensesPercentages = new ArrayList<>();
    private int rowCount;
    private String budgetValue;
    private JLabel monthLabel;
    private YearMonth currentMonth;
    private DateTimeFormatter monthFormatter;

    public BudgetBuddy(){
        //to fetch the user's actual month e.g MAY
        currentMonth = YearMonth.now();
        monthFormatter = DateTimeFormatter.ofPattern("MMMM");

        setTitle("Budget Buddy");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        rowCount = 0;
        //GUI
        Font bFont = new Font("Canva Sans", Font.BOLD, 43);
        Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) bFont.getAttributes();
        attributes.put(TextAttribute.TRACKING, 0.2);
        Font bigFont = bFont.deriveFont(attributes);

        Font sFont = new Font("Canva Sans", Font.BOLD, 13);
        Map<TextAttribute, Object> attributeSmall = (Map<TextAttribute, Object>) sFont.getAttributes();
        attributeSmall.put(TextAttribute.TRACKING, 0.1);
        Font smallFont = sFont.deriveFont(attributeSmall);

        Font xsFont = new Font("Canva Sans", Font.BOLD, 11);
        Map<TextAttribute, Object> attributesXSmall = (Map<TextAttribute, Object>) xsFont.getAttributes();
        attributesXSmall.put(TextAttribute.TRACKING, 0.4);
        Font xSmallFont = xsFont.deriveFont(attributesXSmall);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(395, 720));
        leftPanel.setBackground(new Color(253,248,244,255));

        JPanel leftUpperPanel = new JPanel(null);
        leftUpperPanel.setPreferredSize(new Dimension(395, 240));
        leftUpperPanel.setBackground(new Color(253,248,244,255));
        leftPanel.add(leftUpperPanel, BorderLayout.NORTH);

        try {
            ImageIcon logo = new ImageIcon("C:\\Users\\John Keith Mercado\\OneDrive\\Desktop\\2-BSCS-1 (SECOND SEM)\\Design and Analysis of Algorithms\\LOGO.png");//change path to image path
            Image image = logo.getImage();
            Image scaledImage = image.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            ImageIcon scaledLogo = new ImageIcon(scaledImage);

            JLabel logoLabel = new JLabel(scaledLogo);
            logoLabel.setBounds(30, 35, 100, 100);
            leftUpperPanel.add(logoLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //GUI
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

        RoundedButton leftArrow = new RoundedButton("<", 20, 20);
        leftArrow.setBackground(new Color(253,248,244,255));
        leftArrow.setFont(smallFont);
        leftArrow.setBounds(280,220, 20, 20);

        RoundedButton rightArrow = new RoundedButton(">", 20, 20);
        rightArrow.setBackground(new Color(253,248,244,255));
        rightArrow.setFont(smallFont);
        rightArrow.setBounds(310, 220, 20, 20);

        leftUpperPanel.add(leftArrow);
        leftUpperPanel.add(rightArrow);

        leftArrow.addActionListener(e -> changeMonth(-1));
        rightArrow.addActionListener(e -> changeMonth(1));

        monthLabel = new JLabel(currentMonth.format(monthFormatter).toUpperCase(), SwingConstants.CENTER);
        monthLabel.setFont(bigFont);
        monthLabel.setForeground(new Color(25,9,51,255));
        monthLabel.setBounds(0,0, 320, 300);

        JPanel monthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        monthPanel.setBackground(new Color(253,248,244,255));
        monthPanel.setBounds(30, 160, 350, 300);
        monthPanel.add(monthLabel);
        leftUpperPanel.add(monthPanel);

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

        RoundedTextField budgetTF = new RoundedTextField(5, 15,15);
        budgetTF.setFont(new Font("Canva Sans", Font.BOLD, 12));
        budgetTF.setForeground(new Color(25,9,51,255));
        budgetTF.setBackground(Color.WHITE);
        budgetTF.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        budgetTF.setBounds(30, 30, 320, 35);
        leftCenterPanel.add(budgetTF);

        RoundedButton enterButton1 = new RoundedButton("SET", 40, 40);

        enterButton1.setFont(new Font("Canva Sans", Font.BOLD, 10));
        enterButton1.setForeground(new Color(25,9,51,255));
        enterButton1.setBackground(new Color(255,219,195));
        enterButton1.setBounds(355, 37, 40, 25);
        //set button , left panel , budget
        enterButton1.addActionListener(e -> {
            if (e.getSource() == enterButton1) {
                String budgetInput = budgetTF.getText().replace(",", "");

                if (budgetInput.isEmpty() || !isNumeric(budgetInput)) {
                    JOptionPane.showMessageDialog(null, "Buddy, enter a valid budget", "Invalid Input", JOptionPane.ERROR_MESSAGE);

                }
                budgetText.setText("                    ₱ " + budgetInput);
            }
        });
        leftCenterPanel.add(enterButton1);
        //left panel , Bill
        JLabel billLabel = new JLabel("BILL/EXPENSE NAME");
        billLabel.setFont(smallFont);
        billLabel.setForeground(new Color(25,9,51,255));
        billLabel.setBounds(30, 90, 300, 20);
        leftCenterPanel.add(billLabel);

        RoundedTextField billTF = new RoundedTextField(5, 15, 15);
        billTF.setFont(new Font("Canva Sans", Font.BOLD, 12));
        billTF.setForeground(new Color(25,9,51,255));
        billTF.setBackground(Color.WHITE);
        billTF.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 0));
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

        RoundedTextField costTF = new RoundedTextField(5, 15, 15);
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
        //set date
        JComboBox<String> dateCB = new JComboBox<>();
        dateCB.setUI(new RoundedComboBox(15, 15));
        dateCB.setFont(smallFont);
        dateCB.setForeground(new Color(25, 9, 51));
        dateCB.setBackground(Color.WHITE);
        dateCB.setBounds(195, 190, 152, 35);
        leftCenterPanel.add(dateCB);

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("    MM/dd/yyyy");
        int numberOfDays = 1825;

        LocalDate currentDate = today;
        for (int i = 0; i < numberOfDays; i++) {
            dateCB.addItem(currentDate.format(formatter));
            currentDate = currentDate.plusDays(1);
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
        addButton.addActionListener(e -> {
            String billInput = billTF.getText();
            String costInput = costTF.getText().replace(",", "");
            String budgetInput = budgetTF.getText().replace(",", "");

            if (budgetInput.isEmpty() || !isNumeric(budgetInput)) { // if no input
                JOptionPane.showMessageDialog(null, "Buddy, you have to enter your budget.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } else if (billInput.isEmpty() || costInput.isEmpty() || !isNumeric(costInput)) {
                if (billInput.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Buddy, I think you forgot to enter the bill/expense name.", "Missing Input", JOptionPane.ERROR_MESSAGE);
                } else if (costInput.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Buddy, please enter how much it is.", "Missing Input", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Buddy, please enter a valid number.", "Input Mismatch", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                billInput = capitalizeFirstLetter(billInput);
                costInput = "₱ " + costInput;
                String dateInput = (String) dateCB.getSelectedItem();

                // to put input in table
                taskTable.insertRow(0, new Object[]{billInput, dateInput, costInput});
                rowCount++;
                sortTaskTableByDate(); // mergesort method

                billTF.setText("");
                costTF.setText("");
            }
        });
        leftLowerPanel.add(addButton);

        RoundedButton generateButton = new RoundedButton("GENERATE SUMMARY", 40, 40);
        generateButton.setFont(new Font("Canva Sans", Font.BOLD, 12));
        generateButton.setForeground(new Color(25,9,51,255));
        generateButton.setBackground(new Color(255,219,195,255));
        generateButton.setBounds(100, 85, 170, 37);

        // prediction algo
        generateButton.addActionListener(e -> {
            String budgetInput = budgetTF.getText().replace(",", "");
            String totalSumText = totalSumField.getText();

            if (budgetInput.isEmpty() || !isNumeric(budgetInput)) {
                JOptionPane.showMessageDialog(null, "Buddy, enter a valid budget", "Invalid Budget", JOptionPane.ERROR_MESSAGE);
            } else {
                double totalCost = 0.0;
                for (int i = 0; i < taskTable.getRowCount(); i++) {
                    Object costObj = taskTable.getValueAt(i, 2);
                    if (costObj != null) {
                        String cost = costObj.toString().replace("₱ ", "").replace(",", "");
                        totalCost += Double.parseDouble(cost);
                    }
                }

                double totalSum = 0.0;
                for (int i = 0; i < taskTable1.getRowCount(); i++) {
                    Object valueObj = taskTable1.getValueAt(i, 1);
                    if (valueObj != null) {
                        String valueStr = valueObj.toString().replace("₱ ", "").replace(",", "");
                        totalSum += Double.parseDouble(valueStr);
                    }
                }

                double expensesValue = totalSum;
                double budgetValue = Double.parseDouble(budgetInput);
                double savedValue = budgetValue - (totalCost + expensesValue);
                budgetText.setText("                    ₱ " + budgetInput);
                billsText.setText("₱ " + totalCost);
                savedText.setText("₱ " + savedValue);

                if (!totalSumText.isEmpty()) {
                    expensesText.setText("₱ " + expensesValue);

                    double difference2 = budgetValue - savedValue;

                    double expensesPercentage = (difference2 / budgetValue) * 100;

                    expensesPercentages.add(expensesPercentage);
                    //expensesPercentage is an array list that contains all expense percentages
                    // to be compared in the linear regression algo

                    if (savedValue < 0) {
                        JOptionPane.showMessageDialog(null, "Buddy, your expenses exceed your budget! Be mindful of your spending.", "Budget Exceeded", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Percentage of expenses for the month: " + String.format("%.2f", expensesPercentage) + "%", "Expenses Percentage", JOptionPane.INFORMATION_MESSAGE);
                    }

                    double predictedExpensesPercentage = linearRegression(expensesPercentages);

                    JOptionPane.showMessageDialog(null, "Predicted percentage of expenses for the next month: " + String.format("%.2f", predictedExpensesPercentage) + "%", "Predicted Expenses Percentage", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        leftLowerPanel.add(generateButton);

        // CENTER / GUI
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

        taskTable = new DefaultTableModel();
        taskTable.addColumn("BILL");
        taskTable.addColumn("DUE DATE");
        taskTable.addColumn("COST");
        taskTable.setRowCount(12);

        JTable table = new JTable(taskTable);
        table.setBackground(new Color(253,248,244));
        table.setBounds(10, 50, 300, 600);
        table.setEnabled(false);
        table.setGridColor(new Color(25,9,51,255));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Canva Sans", Font.PLAIN, 11));
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
        scrollPane.setBackground(new Color(253,248,244));
        scrollPane.setBounds(20, 80, 490, 446);
        centerPanel.add(scrollPane);

        JLabel budgetLabel2 = new JLabel("BUDGET: ");
        budgetLabel2.setForeground(new Color(25,9,51));
        budgetLabel2.setFont(smallFont);
        budgetLabel2.setBounds(85, 538, 100, 25);
        budgetLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
        centerPanel.add(budgetLabel2);

        budgetText = new  RoundedTextField(5, 15, 15);;
        budgetText.setForeground(new Color(25,9,51));
        budgetText.setBackground(new Color(255,219,195,255));
        budgetText.setFont(smallFont);
        budgetText.setBorder(BorderFactory.createLineBorder(new Color(25,9,51)));
        budgetText.setBounds(100, 538, 300, 25);
        budgetText.setOpaque(false);
        budgetText.setText("                    ₱ ");
        budgetText.setEditable(false);
        centerPanel.add(budgetText);

        JLabel billsLabel = new JLabel("BILLS: ");
        billsLabel.setForeground(new Color(25,9,51));
        billsLabel.setBackground(new Color(253,248,244));
        billsLabel.setFont(smallFont);
        billsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        billsLabel.setBounds(30, 565, 100, 30);
        centerPanel.add(billsLabel);

        billsText = new JTextField(20);
        billsText.setForeground(new Color(25,9,51));
        billsText.setFont(smallFont);
        billsText.setBorder(BorderFactory.createEmptyBorder());
        billsText.setBounds(140, 565, 150, 30);
        billsText.setOpaque(false);
        billsText.setText("₱ ");
        billsText.setEditable(false);
        centerPanel.add(billsText);

        JLabel expensesLabel = new JLabel("EXPENSES: ");
        expensesLabel.setForeground(new Color(25,9,51));
        expensesLabel.setFont(smallFont);
        expensesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        expensesLabel.setBounds(30, 590, 100, 30);
        centerPanel.add(expensesLabel);

        expensesText = new JTextField(20);
        expensesText.setForeground(new Color(25,9,51));
        expensesText.setFont(smallFont);
        expensesText.setBorder(BorderFactory.createEmptyBorder());
        expensesText.setBounds(140, 590, 150, 30);
        expensesText.setOpaque(false);
        expensesText.setText("₱ ");
        expensesText.setEditable(false);
        centerPanel.add(expensesText);

        JLabel savedLabel = new JLabel("SAVED: ");
        savedLabel.setForeground(new Color(25,9,51));
        savedLabel.setFont(smallFont);
        savedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        savedLabel.setBounds(30, 615, 100, 30);
        centerPanel.add(savedLabel);

        savedText = new JTextField(20);
        savedText.setForeground(new Color(25,9,51));
        savedText.setFont(smallFont);
        savedText.setBorder(BorderFactory.createEmptyBorder());
        savedText.setBounds(140, 615, 150, 30);
        savedText.setOpaque(false);
        savedText.setText("₱ ");
        savedText.setEditable(false);
        centerPanel.add(savedText);

        RoundedButton clearButton = new RoundedButton("CLEAR", 40, 40);
        clearButton.setFont(new Font("Canva Sans", Font.BOLD, 12));
        clearButton.setForeground(new Color(25,9,51,255));
        clearButton.setBackground(new Color(255,219,195,255));
        clearButton.setBounds(350, 585, 100, 35);
        clearButton.setEnabled(true);

        clearButton.addActionListener(e -> {
            String budgetInput = budgetTF.getText().replace(",", "");
            taskTable.setRowCount(0);
            taskTable.setRowCount(12);
            taskTable1.setRowCount(0);
            taskTable1.setRowCount(11);
            billsText.setText("₱ ");
            expensesText.setText("₱ ");
            savedText.setText("₱ ");
            totalSumField.setText("");
            budgetText.setText("                    ₱ " + budgetInput);

            totalSumValue = 0.0;
        });
        centerPanel.add(clearButton);

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

        //ITEM
        JLabel itemLabel = new JLabel("ITEM");
        itemLabel.setFont(xSmallFont);
        itemLabel.setForeground(new Color(25, 9, 51, 255));
        itemLabel.setBounds(20, 540, 100, 20);
        rightPanel.add(itemLabel);

        RoundedTextField itemBox = new RoundedTextField(5, 15, 15);
        itemBox.setFont(new Font("Canva Sans", Font.PLAIN, 12));
        itemBox.setForeground(new Color(25,9,51,255));
        itemBox.setBackground(Color.WHITE);
        itemBox.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        itemBox.setBounds(17, 560, 300, 28);
        rightPanel.add(itemBox);

        //COST
        JLabel costL = new JLabel("COST");
        costL.setFont(xSmallFont);
        costL.setForeground(new Color(25, 9, 51, 255));
        costL.setBounds(20, 600, 85, 20);
        rightPanel.add(costL);

        JLabel pesoSignCost = new JLabel(" ₱");
        pesoSignCost.setFont(xSmallFont);
        pesoSignCost.setForeground(new Color(25,9,51,255));
        pesoSignCost.setBounds(20, 619, 85, 30);
        rightPanel.add(pesoSignCost);

        RoundedTextField costBox = new RoundedTextField(5, 15, 15);
        costBox.setFont(new Font("Canva Sans", Font.PLAIN, 12));
        costBox.setForeground(new Color(25,9,51,255));
        costBox.setBackground(Color.WHITE);
        costBox.setBorder(BorderFactory.createEmptyBorder(0, 25, 1, 0));
        costBox.setBounds(17, 620, 130, 30);
        rightPanel.add(costBox);

        //SUBTRACT BUDGET
        RoundedButton subBudget = new RoundedButton("SUBTRACT TO BUDGET", 40, 40);
        subBudget.setFont(new Font("Canva Sans", Font.BOLD, (int)10.7));
        subBudget.setForeground(new Color(25,9,51,255));
        subBudget.setBackground(new Color(255,219,195,255));
        subBudget.setBounds(164, 618, 150, 31);
        subBudget.addActionListener(e -> {
            String budgetInput = budgetTF.getText().replace(",", "");
            String totalSumText = totalSumField.getText().replace(",", ""); // bottom TOTAL right panel instantiated

            if (budgetInput.isEmpty() || !isNumeric(budgetInput)) {
                JOptionPane.showMessageDialog(null, "Buddy, enter a valid budget", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } else if (totalSumText.isEmpty() || !isNumeric(totalSumText)) {
                JOptionPane.showMessageDialog(null, "Total Sum is missing", "Missing Input", JOptionPane.ERROR_MESSAGE);
            } else {
                double budgetValue = Double.parseDouble(budgetInput);
                totalSumValue = Double.parseDouble(totalSumText);

                double totalCost = 0.0;
                for (int i = 0; i < taskTable.getRowCount(); i++) {
                    Object costObj = taskTable.getValueAt(i, 2);
                    if (costObj != null) {
                        String cost = costObj.toString().replace("₱ ", "").replace(",", "");
                        totalCost += Double.parseDouble(cost);
                    }
                }

                double difference = budgetValue - (totalSumValue + totalCost); //  saved
                budgetText.setText(String.format("                    ₱ %.2f", difference));
                JOptionPane.showMessageDialog(null, "Subtracted to budget", "Operation Successful", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        rightPanel.add(subBudget);


        //TOTAL
        JLabel totalLabel = new JLabel("T O T A L :  " );
        totalLabel.setFont(smallFont);
        totalLabel.setForeground(new Color(25, 9, 51, 255));
        totalLabel.setBounds(90, 490, 120, 40);
        rightPanel.add(totalLabel);

        JLabel pesoSign3 = new JLabel("₱");
        pesoSign3.setFont(smallFont);
        pesoSign3.setForeground(new Color(25,9,51,255));
        pesoSign3.setBounds(180, 500, 10,20);
        rightPanel.add(pesoSign3);

        JLabel costSum = new JLabel("" );
        costSum.setFont(smallFont);
        costSum.setForeground(new Color(25, 9, 51, 255));
        costSum.setBounds(10, 50, 20, 20);

        totalSumField = new JTextField(10);
        totalSumField.setFont(new Font("Canva Sans", Font.BOLD, 12));
        totalSumField.setForeground(new Color(25, 9, 51));
        totalSumField.setBackground(new Color(255, 219, 195));
        totalSumField.setBorder(BorderFactory.createEmptyBorder());
        totalSumField.setHorizontalAlignment(SwingConstants.LEFT);
        totalSumField.setBounds(195, 499, 180, 20);
        totalSumField.setEditable(false);
        totalSumField.setOpaque(false);
        rightPanel.add(totalSumField);

        //table
        taskTable1 = new DefaultTableModel();
        taskTable1.addColumn("ITEM");
        taskTable1.addColumn("COST");
        taskTable1.setRowCount(11);

        table = new JTable(taskTable1);
        table.setBackground(new Color(253,248,244));
        table.setBounds(10, 50, 300, 500);
        table.setEnabled(false);
        table.setGridColor(new Color(25,9,51));
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);

        JTableHeader header2 = table.getTableHeader();
        header2.setFont(new Font("Canva Sans", Font.PLAIN, 11));
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
        scrollPane2.setBounds(15, 80, 320, 412);

        rightPanel.add(scrollPane2);

        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        //bottom part
        RoundedButton enterButton = new RoundedButton("IGNORE THIS", 40, 40);

        enterButton.setFont(new Font("Canva Sans", Font.BOLD, 12));
        enterButton.setForeground(getForeground());
        enterButton.setBackground(getBackground());
        enterButton.setBounds(10000, 15, 115, 37);
        enterButton.setBorderColor(new Color(25,9,51,255));
        //bottom part right panel gui input
        enterButton.addActionListener(e -> {
            if (e.getSource() == enterButton) {
                String itemInput = itemBox.getText();
                String cost2Input = costBox.getText().replace(",", "");
                if (itemInput.isEmpty() || cost2Input.isEmpty() || !isNumeric(cost2Input)) {
                    if (itemInput.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Buddy, please enter an item name", "Missing Input", JOptionPane.ERROR_MESSAGE);
                    } else if (cost2Input.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Buddy, please enter the cost", "Missing Input", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Buddy, please enter a valid number for the cost", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                } else {

                    itemInput = capitalizeFirstLetter(itemInput);
                    cost2Input = "₱ " + cost2Input;

                    taskTable1.insertRow(0, new Object[]{itemInput, cost2Input}); // put in the table
                    rowCount++;

                    double totalSum = 0.0;
                    for (int i = 0; i < taskTable1.getRowCount(); i++) {
                        String costValue = (String) taskTable1.getValueAt(i, 1);
                        if (costValue != null) {
                            String numericPart = costValue.substring(2).trim().replace("₱", "").replace(",", "");
                            totalSum += Double.parseDouble(numericPart);
                        }
                    }
                    totalSumField.setText(String.format("%.2f", totalSum));

                    itemBox.setText("");
                    costBox.setText("");
                }
            }
        });
        itemBox.addActionListener(e -> enterButton.doClick());
        costBox.addActionListener(e -> enterButton.doClick());
        rightPanel.add(enterButton);
        setVisible(true);
    }

    private String capitalizeFirstLetter(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    private void changeMonth(int direction) {  // < > button to work
        currentMonth = currentMonth.plusMonths(direction);
        monthLabel.setText(currentMonth.format(monthFormatter).toUpperCase());
        monthLabel.repaint();
        monthLabel.revalidate();
    }

    private boolean isNumeric(String str) {  //check if input  is a number then accept
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private double linearRegression(List<Double> expensesPercentages) {
        int n = expensesPercentages.size(); // y = mx + b / ax+b
        double sumX = 0.0; // slope x variable
        double sumY = 0.0; // slope y
        double sumXY = 0.0; //
        double sumXSquare = 0.0;

        if (n == 1) { // if 1 , the percentage will remain because not enough info /data set, need 2 or more
            double sum = 0.0;
            for (Double percentage : expensesPercentages) {
                sum += percentage;
            }
            return sum / n;
        }

        for (int i = 0; i < n; i++) {
            double x = i + 1;
            double y = expensesPercentages.get(i);
            sumX += x;
            sumY += y;
            sumXY += x * y;
            sumXSquare += x * x;
        }

        double slope = (n * sumXY - sumX * sumY) / (n * sumXSquare - sumX * sumX); // a
        double intercept = (sumY - slope * sumX) / n; // b

        double predictedExpensesPercentage = slope * (n + 1) + intercept;  // add 1 because you have to predict for the next month
        return predictedExpensesPercentage;
    }

    private void sortTaskTableByDate() { // merge sort
        int dateColumnIndex = 1;
        int columnCount = taskTable.getColumnCount();
        int rowCount = taskTable.getRowCount();
        String[] dates = new String[rowCount];
        int validRowCount = 0;
        //getting the values in the date column // input from left panel
        for (int i = 0; i < rowCount; i++) {
            Object dateValue = taskTable.getValueAt(i, dateColumnIndex);
            if (dateValue != null && !dateValue.toString().isEmpty()) {
                dates[validRowCount++] = dateValue.toString();
            }
        }
        // performing the mergesort
        // validRowCount , count the row of dates , minus 1 since index
        mergeSort(dates, 0, validRowCount - 1);
        //to print the sorted values in the table immediately
        for (int i = 0; i < validRowCount; i++) {
            String targetDate = dates[i]; //targetdate is for the gathered dates tha are sorted
            for (int j = 0; j < rowCount; j++) {
                if (targetDate.equals(taskTable.getValueAt(j, dateColumnIndex))) {
                    for (int k = 0; k < columnCount; k++) {
                        Object temp = taskTable.getValueAt(i, k);
                        taskTable.setValueAt(taskTable.getValueAt(j, k), i, k);
                        taskTable.setValueAt(temp, j, k);
                    }
                    break;
                }
            }
        }
    }

    private void mergeSort(String[] dates, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2; // from the module abt merge sorting
            mergeSort(dates, left, mid);
            mergeSort(dates, mid + 1, right);
            merge(dates, left, mid, right);
        }
    }

    private void merge(String[] dates, int left, int mid, int right) { // left = month , day , year
        int n1 = mid - left + 1;
        int n2 = right - mid;

        String[] leftArr = new String[n1];
        String[] rightArr = new String[n2];

        for (int i = 0; i < n1; i++)
            leftArr[i] = dates[left + i];
        for (int j = 0; j < n2; j++)
            rightArr[j] = dates[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (compareDates(leftArr[i], rightArr[j]) <= 0) { // kasi di nya ma peperform ang merge sort kasi naka format ng dte
                // , gumawa tayo ng method to separete the day month and year
                dates[k] = leftArr[i];
                i++;
            } else {
                dates[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            dates[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            dates[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static int compareDates(String date1, String date2) { // year>> month>>day to compare
        date1 = date1.trim();
        date2 = date2.trim();

        String[] parts1 = date1.split("/");
        String[] parts2 = date2.split("/");

        int year1 = Integer.parseInt(parts1[2]);
        int year2 = Integer.parseInt(parts2[2]);
        if (year1 != year2) {
            return Integer.compare(year1, year2);
        }

        int month1 = Integer.parseInt(parts1[0]);
        int month2 = Integer.parseInt(parts2[0]);
        if (month1 != month2) {
            return Integer.compare(month1, month2);
        }

        int day1 = Integer.parseInt(parts1[1]);
        int day2 = Integer.parseInt(parts2[1]);
        return Integer.compare(day1, day2);
    }

}
