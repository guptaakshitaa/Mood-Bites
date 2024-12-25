import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

// Main class
public class MoodApp {
    JFrame frame;
    CardLayout cardLayout;
    JPanel container;
    HashMap<String, String> users; // Simulating a user database

    public MoodApp() {
        // Initialize user database (for demo purposes)
        users = new HashMap<>();
        
        // Create the main frame
        frame = new JFrame("MoodApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);
        
        // CardLayout to switch between screens
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);
        
        // Add pages to the container
        container.add(new SignUpPage(this), "signup");
        container.add(new LoginPage(this), "login");
        container.add(new QuestionnairePage(this), "questionnaire");
        
        // Set the initial page
        cardLayout.show(container, "signup");
        
        // Add the container to the frame
        frame.add(container);
        frame.setVisible(true);
    }
    
    // Method to switch screens
    public void showScreen(String screenName) {
        cardLayout.show(container, screenName);
    }
    
    public static void main(String[] args) {
        new MoodApp();
    }
}

// Utility method to create styled labels
class UIHelper {
    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(Color.DARK_GRAY);
        return label;
    }
}

// SignUp Page
class SignUpPage extends JPanel {
    JTextField nameField, emailField;
    JPasswordField passwordField;
    MoodApp app;

    public SignUpPage(MoodApp app) {
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

                // Simulate storing user info
                app.users.put(email, password);
                JOptionPane.showMessageDialog(SignUpPage.this, "Sign-Up Successful!");

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
    MoodApp app;

    public LoginPage(MoodApp app) {
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
    MoodApp app;

    public QuestionnairePage(MoodApp app) {
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
        JComboBox<String> flavorOptions = new JComboBox<>(flavors);
        add(flavorOptions);
        flavorOptions.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        flavorOptions.setToolTipText("Select your preferred flavor");

        add(UIHelper.createLabel("Are you craving something light, filling, or a specific texture?"));
        String[] textures = {"Light", "Filling", "Crispy", "Creamy"};
        JComboBox<String> textureOptions = new JComboBox<>(textures);
        add(textureOptions);
        textureOptions.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        textureOptions.setToolTipText("Choose texture or filling preference");

        add(UIHelper.createLabel("Does the weather or season influence your choice today?"));
        String[] weatherInfluences = {"Refreshing", "Warming"};
        JComboBox<String> weatherOptions = new JComboBox<>(weatherInfluences);
        add(weatherOptions);
        weatherOptions.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        weatherOptions.setToolTipText("How does the weather influence your choice?");

        add(UIHelper.createLabel("Do you have any dietary restrictions or preferences?"));
        String[] diets = {"None", "Vegan", "Gluten-Free", "Low-Carb"};
        JComboBox<String> dietOptions = new JComboBox<>(diets);
        add(dietOptions);
        dietOptions.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        dietOptions.setToolTipText("Select dietary restrictions or preferences");

        add(UIHelper.createLabel("Would you like to try something new or a dish you've enjoyed before?"));
        String[] newOrOld = {"Try Something New", "Enjoy Something Familiar"};
        JComboBox<String> newOrOldOptions = new JComboBox<>(newOrOld);
        add(newOrOldOptions);
        newOrOldOptions.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        newOrOldOptions.setToolTipText("Choose your preference");

        add(Box.createVerticalStrut(20)); // Space

        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setBackground(new Color(60, 179, 113)); // Sea green submit button
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedFlavor = (String) flavorOptions.getSelectedItem();
                String selectedTexture = (String) textureOptions.getSelectedItem();
                String selectedWeather = (String) weatherOptions.getSelectedItem();
                String selectedDiet = (String) dietOptions.getSelectedItem();
                String selectedNewOrOld = (String) newOrOldOptions.getSelectedItem();

                // You can process the collected data here or pass it to the next step
                JOptionPane.showMessageDialog(QuestionnairePage.this, "Thanks for submitting your preferences!");
            }
        });
        add(submitButton);
    }
}