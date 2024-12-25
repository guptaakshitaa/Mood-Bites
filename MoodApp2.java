import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MoodApp2 {       
    JFrame frame;
    CardLayout cardLayout;
    JPanel container;
    HashMap<String, String> users; // Simulating a user database
    ArrayList<String> cart; // Cart to store selected items

    public MoodApp2() {
        // Initialize user database (for demo purposes)
        users = new HashMap<>();
        cart = new ArrayList<>(); // Initialize the cart
        
        // Create the main frame
        frame = new JFrame("MoodApp2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);
        
        // CardLayout to switch between screens
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);
        
        // Add pages to the container
        container.add(new SignUpPage(this), "signup");
        container.add(new LoginPage(this), "login");
        container.add(new QuestionnairePage(this), "questionnaire");
        container.add(new CartPage(this), "cart"); // Add the cart page
        container.add(new PaymentPage(this), "payment"); // Add the payment page
        
        // Set the initial page
        cardLayout.show(container, "signup");
        
        // Add the container to the frame
        frame.add(container);
        frame.setVisible(true);
    }
    
    // Method to switch screens
    public void showScreen(String screenName) {
        cardLayout.show(container, screenName);
        // Update cart display when returning to cart
        if (screenName.equals("cart")) {
            ((CartPage) container.getComponent(3)).updateCartDisplay();
        }
    }
    
    public static void main(String[] args) {
        new MoodApp2();
    }
}

// Utility method to create styled labels
class UIHelper {
    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(Color.DARK_GRAY);
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the label

        return label;
    }
}

// SignUp Page
class SignUpPage extends JPanel {
    JTextField nameField, emailField;
    JPasswordField passwordField;
    MoodApp2 app;

    public SignUpPage(MoodApp2 app) {
        this.app = app;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(255, 248, 220)); // Light pastel background
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); // Add padding
        
        JLabel titleLabel = new JLabel("Sign Up");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(34, 139, 34)); // Green header color
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);
        
        add(Box.createVerticalStrut(20)); // Space between components

        add(UIHelper.createLabel("Name:"));
        nameField = new JTextField();
        add(nameField);
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        add(UIHelper.createLabel("Email:"));
        emailField = new JTextField();
        add(emailField);
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        add(UIHelper.createLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        add(Box.createVerticalStrut(20)); // Space

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpButton.setBackground(new Color(30, 144, 255)); // Bright blue button
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);
        signUpButton.setFont(new Font("Arial", Font.BOLD, 16));
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Validate user inputs
                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(SignUpPage.this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Simulate storing user info
                app.users.put(email, password);
                JOptionPane.showMessageDialog(SignUpPage.this, "Sign-Up Successful!");

                // Clear fields
                nameField.setText("");
                emailField.setText("");
                passwordField.setText("");

                // Move to login page
                app.showScreen("login");
            }
        });
        add(signUpButton);
    }
}

// Login Page
class LoginPage extends JPanel {
    JTextField emailField;
    JPasswordField passwordField;
    MoodApp2 app;

    public LoginPage(MoodApp2 app) {
        this.app = app;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(240, 255, 255)); // Light cyan background
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); // Padding
        
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(0, 100, 0)); // Dark green header color
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        add(Box.createVerticalStrut(20)); // Space between components

        add(UIHelper.createLabel("Email:"));
        emailField = new JTextField();
        add(emailField);
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        add(UIHelper.createLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        add(Box.createVerticalStrut(20)); // Space

        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setBackground(new Color(0, 191, 255)); // Light blue button
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Validate inputs
                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Please enter both email and password!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (app.users.containsKey(email) && app.users.get(email).equals(password)) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Login Successful!");
                    app.showScreen("questionnaire");
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Invalid Credentials!");
                }
            }
        });
        add(loginButton);
    }
}

// Questionnaire Page
class QuestionnairePage extends JPanel {
    MoodApp2 app;
    JComboBox<String> flavorOptions;
    JComboBox<String> textureOptions;
    JComboBox<String> weatherOptions;
    JComboBox<String> dietOptions;
    JComboBox<String> newOrOldOptions;

    public QuestionnairePage(MoodApp2 app) {
        this.app = app;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(245, 245, 220)); // Beige background
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("Mood Questionnaire");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(128, 0, 0)); // Dark red header color
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);
        
        add(Box.createVerticalStrut(20)); // Space
        
        add(UIHelper.createLabel("What type of cuisine or flavors are you in the mood for today?"));
        String[] flavors = {"Spicy", "Sweet", "Savory", "Other"};
        flavorOptions = new JComboBox<>(flavors);
        add(flavorOptions);
        flavorOptions.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        flavorOptions.setToolTipText("Select your preferred flavor");

        add(UIHelper.createLabel("Are you craving something light, filling, or a specific texture?"));
        String[] textures = {"Light", "Filling", "Crispy", "Creamy"};
        textureOptions = new JComboBox<>(textures);
        add(textureOptions);
        textureOptions.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        textureOptions.setToolTipText("Choose texture or filling preference");

        add(UIHelper.createLabel("What kind of weather are you experiencing today?"));
        String[] weathers = {"Sunny", "Rainy", "Cold", "Hot"};
        weatherOptions = new JComboBox<>(weathers);
        add(weatherOptions);
        weatherOptions.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        weatherOptions.setToolTipText("Select weather condition");

        add(UIHelper.createLabel("Do you have any dietary restrictions?"));
        String[] diets = {"None", "Vegetarian", "Vegan", "Gluten-Free", "Other"};
        dietOptions = new JComboBox<>(diets);
        add(dietOptions);
        dietOptions.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        dietOptions.setToolTipText("Choose dietary restriction");

        add(UIHelper.createLabel("Are you looking for something new or a favorite you know?"));
        String[] newOrOld = {"New", "Old"};
        newOrOldOptions = new JComboBox<>(newOrOld);
        add(newOrOldOptions);
        newOrOldOptions.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        newOrOldOptions.setToolTipText("Select new or old preference");

        add(Box.createVerticalStrut(20)); // Space
        
        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setBackground(new Color(0, 128, 0)); // Dark green button
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Collect user responses
                String flavor = (String) flavorOptions.getSelectedItem();
                String texture = (String) textureOptions.getSelectedItem();
                String weather = (String) weatherOptions.getSelectedItem();
                String diet = (String) dietOptions.getSelectedItem();
                String newOrOld = (String) newOrOldOptions.getSelectedItem();

                // Show selected preferences in a dialog
                JOptionPane.showMessageDialog(QuestionnairePage.this, 
                    "Your preferences:\n" +
                    "Flavor: " + flavor + "\n" +
                    "Texture: " + texture + "\n" +
                    "Weather: " + weather + "\n" +
                    "Diet: " + diet + "\n" +
                    "New/Old: " + newOrOld);
                
                // Move to cart page after submission
                app.showScreen("cart");
            }
        });
        add(submitButton);
    }
}

// Cart Page
class CartPage extends JPanel {
    MoodApp2 app;

    public CartPage(MoodApp2 app) {
        this.app = app;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(240, 255, 240)); // Light green background
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); // Padding

        JLabel titleLabel = new JLabel("Cart");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(0, 128, 0)); // Dark green header color
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        add(Box.createVerticalStrut(20)); // Space
        
        // Placeholder for cart items
        JTextArea cartDisplay = new JTextArea();
        cartDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cartDisplay);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        add(scrollPane);
        
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkoutButton.setBackground(new Color(255, 140, 0)); // Dark orange button
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setFocusPainted(false);
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        add(checkoutButton);
        
        checkoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Move to payment page
                app.showScreen("payment");
            }
        });
    }

    public void updateCartDisplay() {
        // This method would update the display of the cart contents
        // For demonstration, we'll simulate adding items to the cart
        // Assuming preferences have been stored
        JTextArea cartDisplay = (JTextArea)((JScrollPane)getComponent(1)).getViewport().getView();
        cartDisplay.setText("Items in cart:\n");
        cartDisplay.append("1. Selected flavors\n");
        cartDisplay.append("2. Selected textures\n");
        cartDisplay.append("3. Selected dietary options\n");
        // Add actual cart items here as needed
    }
}

// Payment Page
class PaymentPage extends JPanel {
    MoodApp2 app;

    public PaymentPage(MoodApp2 app) {
        this.app = app;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(255, 228, 225)); // Light pink background
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("Payment");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(255, 20, 147)); // Deep pink header color
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);
        
        add(Box.createVerticalStrut(20)); // Space
        
        JLabel paymentInfoLabel = new JLabel("Enter your payment information:");
        paymentInfoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        paymentInfoLabel.setForeground(Color.DARK_GRAY);
        paymentInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(paymentInfoLabel);
        add(Box.createVerticalStrut(20)); // Space

        
        // Placeholder fields for payment information
        add(UIHelper.createLabel("Credit Card Number"));
        JTextField cardField = new JTextField("");
        add(cardField);
        cardField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        add(UIHelper.createLabel("Name on Card"));
        JTextField nameField = new JTextField("");
        add(nameField);
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        add(UIHelper.createLabel("Expiry Date (MM/YY)"));
        JTextField expiryField = new JTextField("");
        add(expiryField);
        expiryField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        add(UIHelper.createLabel("CVV"));
       JTextField cvvField = new JTextField("");
        add(cvvField);
        cvvField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        JButton payButton = new JButton("Pay Now");
        payButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        payButton.setBackground(new Color(255, 99, 71)); // Tomato color
        payButton.setForeground(Color.WHITE);
        payButton.setFocusPainted(false);
        payButton.setFont(new Font("Arial", Font.BOLD, 16));
        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Process payment logic here
                JOptionPane.showMessageDialog(PaymentPage.this, "Payment Successful!");
                // Return to login page after payment
                app.showScreen("login");
            }
        });
        add(payButton);
    }
}