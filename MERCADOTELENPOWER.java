package BudgetBuddy;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class MainPage extends JFrame implements ActionListener {
    private JLabel sumLabel,labelExp;
    private JTable table;
    private DefaultTableModel taskTable;

    public JPanel mainPanel(){
        JPanel panel = new JPanel(null);
        panel.setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setVisible(true);

        panel.add(headerPanel());
        panel.add(panel1());
        panel.add(panel2());
        panel.add(panel3());

        return panel;
    }

    public JPanel headerPanel(){
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(25,9,51));
        topPanel.setBounds(0, 0, 1280, 35);
        add(topPanel);

        return topPanel;
    }

    public JPanel panel1() {
        JPanel panel1 = new JPanel();
        panel1.setBackground(getContentPane().getBackground());
        panel1.setBackground(Color.DARK_GRAY);;
        panel1.setBounds(0, 0, 395, 720);
        add(panel1);
        return panel1;
    }

    public JPanel panel2(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        //panel.setBackground(getContentPane().getBackground());
        panel.setBackground(new Color(253,248,244)); // 253-248 - 244
        panel.setBounds(395, 0, 525, 720);


        DefaultTableModel taskTable = new DefaultTableModel();
        taskTable.addColumn("BILL");
        taskTable.addColumn("DUE DATE");
        taskTable.addColumn("COST");
        taskTable.setRowCount(10);

        // Create the table
        JTable table = new JTable(taskTable);
        table.setBackground(new Color(253,248,244));

        // Adjusting the size and position of the table
        table.setBounds(10, 50, 300, 600);

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(255,219,195));
        header.setForeground(new Color(25,9,51));

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        TableColumnModel columnModel = table.getColumnModel();
        TableColumn column1 = columnModel.getColumn(0);
        TableColumn column2 = columnModel.getColumn(1);
        TableColumn column3 = columnModel.getColumn(2);


        column1.setPreferredWidth(150);
        column2.setPreferredWidth(50);
        column3.setPreferredWidth(40);

        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 30));
        table.setRowHeight(35);

        // Create the scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 120, 490, 383);
        //scrollPane.setBackground(new Color(253,248,244));

        // Add the scroll pane containing the table to the rounded panel
        panel.add(scrollPane);
        add(panel);



        JPanel budgetPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        budgetPanel.setBackground(new Color(253,248,244));
        //sumPanel.setBackground(Color.MAGENTA);
        budgetPanel.setBounds(435, 540, 300, 30);

        JLabel budgetLabel = new JLabel("BUDGET:    ");
        budgetLabel.setForeground(new Color(25,9,51));
        budgetLabel.setFont(new Font("Canva Sans", Font.BOLD, 13));
        budgetLabel.setHorizontalAlignment(SwingConstants.LEFT);
        budgetPanel.add(budgetLabel);

        JTextField budgetText = new JTextField(20);
        budgetText.setForeground(new Color(25,9,51));
        budgetText.setFont(new Font("Canva Sans", Font.PLAIN, 13));
        budgetText.setBorder(BorderFactory.createEmptyBorder());
        budgetText.setHorizontalAlignment(SwingConstants.LEFT);
        budgetText.setOpaque(false);
        budgetText.setText("₱");
        budgetText.setEditable(false);
        budgetPanel.add(budgetText);


        add(budgetPanel);

        JPanel billsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        billsPanel.setBackground(new Color(253,248,244));
        //billsPanel.setBackground(Color.MAGENTA);
        billsPanel.setBounds(453, budgetPanel.getY()+25, 300, 30);

        JLabel billsLabel = new JLabel("BILLS:    ");
        billsLabel.setForeground(new Color(25,9,51));
        billsLabel.setBackground(new Color(253,248,244));
        billsLabel.setFont(new Font("Canva Sans", Font.BOLD, 13));
        billsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        billsPanel.add(billsLabel);

        JTextField billsText = new JTextField(20);
        billsText.setForeground(new Color(25,9,51));
        billsText.setFont(new Font("Canva Sans", Font.PLAIN, 13));
        billsText.setBorder(BorderFactory.createEmptyBorder());
        billsText.setHorizontalAlignment(SwingConstants.LEFT);
        billsText.setOpaque(false);
        billsText.setText("₱");
        billsText.setEditable(false);
        billsPanel.add(billsText);


        add(billsPanel);


        JPanel expensesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        expensesPanel.setBackground(new Color(253,248,244));
        //billsPanel.setBackground(Color.MAGENTA);
        expensesPanel.setBounds(419, billsPanel.getY()+25, 300, 30);

        JLabel expensesLabel = new JLabel("EXPENSES:    ");
        expensesLabel.setForeground(new Color(25,9,51));
        expensesLabel.setFont(new Font("Canva Sans", Font.BOLD, 13));
        expensesLabel.setHorizontalAlignment(SwingConstants.LEFT);
        expensesPanel.add(expensesLabel);

        JTextField expensesText = new JTextField(20);
        expensesText.setForeground(new Color(25,9,51));
        expensesText.setFont(new Font("Canva Sans", Font.PLAIN, 13));
        expensesText.setBorder(BorderFactory.createEmptyBorder());
        expensesText.setHorizontalAlignment(SwingConstants.LEFT);
        expensesText.setOpaque(false);
        expensesText.setText("₱");
        expensesText.setEditable(false);
        expensesPanel.add(expensesText);

        add(expensesPanel);

        JPanel savedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        savedPanel.setBackground(new Color(253,248,244));
        //savedPanel.setBackground(Color.MAGENTA);
        savedPanel.setBounds(446, expensesPanel.getY()+25, 300, 30);

        JLabel savedLabel = new JLabel("SAVED:    ");
        savedLabel.setForeground(new Color(25,9,51));
        savedLabel.setFont(new Font("Canva Sans", Font.BOLD, 13));
        savedLabel.setHorizontalAlignment(SwingConstants.LEFT);
        savedPanel.add(savedLabel);

        JTextField savedText = new JTextField(20);
        savedText.setForeground(new Color(25,9,51));
        savedText.setFont(new Font("Canva Sans", Font.PLAIN, 13));
        savedText.setBorder(BorderFactory.createEmptyBorder());
        savedText.setHorizontalAlignment(SwingConstants.LEFT);
        savedText.setOpaque(false);
        savedText.setText("₱");
        savedText.setEditable(false);
        savedPanel.add(savedText);

        add(savedPanel);
        add(sumPanel());



        return panel;
    }

    public JPanel panel3(){
        JPanel panel = new RoundedPanel(20, 20);
        panel.setLayout(null);
        panel.setBackground(new Color(253, 248, 244));
        panel.setBounds(395 + 525, 0, 360, 720);

        taskTable = new DefaultTableModel();
        taskTable.addColumn("ITEM");
        taskTable.addColumn("COST");
        taskTable.setRowCount(10);

        table = new JTable(taskTable);
        table.setBackground(new Color(253,248,244));
        table.setBounds(10, 50, 300, 600);

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(255, 219, 195));
        header.setForeground(new Color(25, 9, 51));

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        TableColumnModel columnModel = table.getColumnModel();
        TableColumn column1 = columnModel.getColumn(0);
        TableColumn column2 = columnModel.getColumn(1);

        column1.setPreferredWidth(150);
        column2.setPreferredWidth(50);

        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 30));
        table.setRowHeight((int) 35);

        // Make the cells editable
        column1.setCellEditor(new DefaultCellEditor(new JTextField()));
        column2.setCellEditor(new DefaultCellEditor(new JTextField()));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15, 120, 320, 383);

        add(ExpenseLabel());

        panel.add(scrollPane);


        return panel;
    }



    public JPanel sumPanel(){
        JPanel sumPanel = new RoundedPanel(20, 20);
        sumPanel.setBounds(415, 75, 490, 30);
        sumPanel.setBackground(new Color(255,219,195));

        sumLabel = new JLabel("SUMMARY OF BILLS");
        sumLabel.setForeground(new Color(25,9,51));
        sumLabel.setFont(new Font("Canva Sans", Font.BOLD, 12));
        sumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sumPanel.add(sumLabel);
        return sumPanel;
    }
    public JPanel ExpenseLabel(){
        JPanel panel = new RoundedPanel(20,20);
        panel.setBounds(395+525+15, 75, 320, 25);
        panel.setBackground(new Color(255,219,195));

        labelExp = new JLabel("SIDE EXPENSES FOR THE DAY");
        labelExp.setBounds(0,0,525,720);
        labelExp.setForeground(new Color(25,9,51));
        labelExp.setFont(new Font("Canva Sans", Font.BOLD, 12));
        labelExp.setHorizontalAlignment(JLabel.CENTER);
        panel.add(labelExp);

        return panel;
    }


    public MainPage(){
        initComponents();
    }

    private void initComponents(){
        setTitle("Budget Buddy");
        setSize(new Dimension(1280, 720));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(mainPanel());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainPage().setVisible(true);
        });
    }
}
