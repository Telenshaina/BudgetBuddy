package BudgetBuddy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;

public class MainPage extends JFrame implements ActionListener {
    private JLabel labelExp;
    private JTable table;
    private DefaultTableModel taskTable;

    public JPanel mainPanel(){
        JPanel panel = new JPanel(null);
        panel.setSize(1280, 720);
        //panel.setTitle("Budget Buddy");
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
        //panel1.setBackground(Color.RED);
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
        JPanel panel2 = new JPanel();
        panel2.setBackground(getContentPane().getBackground());
        panel2.setBackground(Color.DARK_GRAY);
        panel2.setBounds(395, 0, 525, 720);
        add(panel2);

        return panel2;
    }
    public JPanel panel3(){
        // Create a RoundedPanel with specified arc width and arc height
        JPanel panel = new RoundedPanel(20, 20);
        panel.setLayout(null); // Set layout to null to manually position components
        panel.setBackground(getContentPane().getBackground());
        panel.setBackground(new Color(253,248,244)); // 253-248 - 244
        panel.setBounds(395+525, 0, 360, 720);

        // Create the table model and add columns
        DefaultTableModel taskTable = new DefaultTableModel();
        taskTable.addColumn("rthr");
        taskTable.addColumn("gggg");

        // Create the table
        JTable table = new JTable(taskTable);

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

        column1.setPreferredWidth(150);
        column2.setPreferredWidth(50);

        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 30));
        table.setRowHeight(25);

        // Create the scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15, 90, 320, 600);

        // Add the scroll pane containing the table to the rounded panel
        panel.add(scrollPane);
        add(ExpenseLabel());

        // Return the rounded panel
        return panel;
    }





    public JPanel ExpenseLabel(){
        JPanel panel = new RoundedPanel(20,20);
        panel.setBounds(395+525+15, 60, 320, 25);
        panel.setBackground(new Color(255,219,195));

        labelExp = new JLabel("SIDE EXPENSES FOR THE DAY");
        labelExp.setBounds(0,0,525,720);
        labelExp.setForeground(new Color(25,9,51));
        labelExp.setFont(new Font("Arial", Font.PLAIN, 12));
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

