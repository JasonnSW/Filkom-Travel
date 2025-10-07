import customer.*;
import order.*;
import promotion.*;
import vehicle.*;
import vehicle.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main extends JFrame implements ActionListener {
    private JButton createMemberButton;
    private JButton createGuestButton;
    private JButton createMenuButton;
    private JButton createPromoButton;
    private JButton addToCartButton;
    private JButton removeFromCartButton;
    private JButton applyPromoButton;
    private JButton topUpButton;
    private JButton checkOutButton;
    private JButton printButton;
    private JButton printHistoryButton;

    private JTextField inputField;

    private LinkedList<Customer> userList;
    private ArrayList<Menu> menuList;
    private ArrayList<Promotion> promoList;
    private ArrayList<Vehicle> vehicles;
    private Map<String, ArrayList<Menu>> menuOrder;

    public Main() {
        userList = new LinkedList<>();
        menuList = new ArrayList<>();
        promoList = new ArrayList<>();
        vehicles = new ArrayList<>();
        menuOrder = new HashMap<>();

        createMemberButton = new JButton("CREATE MEMBER");
        createGuestButton = new JButton("CREATE GUEST");
        createMenuButton = new JButton("CREATE MENU");
        createPromoButton = new JButton("CREATE PROMO");
        addToCartButton = new JButton("ADD TO CART");
        removeFromCartButton = new JButton("REMOVE FROM CART");
        applyPromoButton = new JButton("APPLY PROMO");
        topUpButton = new JButton("TOPUP");
        checkOutButton = new JButton("CHECK OUT");
        printButton = new JButton("PRINT");
        printHistoryButton = new JButton("PRINT HISTORY");

        inputField = new JTextField();

        createMemberButton.addActionListener(this);
        createGuestButton.addActionListener(this);
        createMenuButton.addActionListener(this);
        createPromoButton.addActionListener(this);
        addToCartButton.addActionListener(this);
        removeFromCartButton.addActionListener(this);
        applyPromoButton.addActionListener(this);
        topUpButton.addActionListener(this);
        checkOutButton.addActionListener(this);
        printButton.addActionListener(this);
        printHistoryButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout(11, 1));
        buttonPanel.add(createMemberButton);
        buttonPanel.add(createGuestButton);
        buttonPanel.add(createMenuButton);
        buttonPanel.add(createPromoButton);
        buttonPanel.add(addToCartButton);
        buttonPanel.add(removeFromCartButton);
        buttonPanel.add(applyPromoButton);
        buttonPanel.add(topUpButton);
        buttonPanel.add(checkOutButton);
        buttonPanel.add(printButton);
        buttonPanel.add(printHistoryButton);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(new JLabel("Input:"), BorderLayout.WEST);
        inputPanel.add(inputField, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String input = inputField.getText();

        switch (command) {
            case "CREATE MEMBER":
                // Handle create member logic
                break;
            case "CREATE GUEST":
                // Handle create guest logic
                break;
            case "CREATE MENU":
                // Handle create menu logic
                break;
            case "CREATE PROMO":
                // Handle create promo logic
                break;
            case "ADD TO CART":
                // Handle add to cart logic
                break;
            case "REMOVE FROM CART":
                // Handle remove from cart logic
                break;
            case "APPLY PROMO":
                // Handle apply promo logic
                break;
            case "TOPUP":
                // Handle topup logic
                break;
            case "CHECK OUT":
                // Handle check out logic
                break;
            case "PRINT":
                // Handle print logic
                break;
            case "PRINT HISTORY":
                // Handle print history logic
                break;
        }

        inputField.setText("");
    }
}
