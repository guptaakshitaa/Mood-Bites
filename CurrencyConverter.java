import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CurrencyConverter extends JFrame {
    private JTextField amountField;
    private JComboBox<String> fromCurrencyBox;
    private JComboBox<String> toCurrencyBox;
    private JLabel resultLabel;

    private HashMap<String, Double> exchangeRates;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Set up exchange rates (for simplicity)
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0); // Base currency
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("INR", 74.0);
        exchangeRates.put("GBP", 0.76);
        exchangeRates.put("JPY", 110.0);

        // Create components
        JLabel fromLabel = new JLabel("From Currency:");
        fromCurrencyBox = new JComboBox<>(new String[]{"USD", "EUR", "INR", "GBP", "JPY"});

        JLabel toLabel = new JLabel("To Currency:");
        toCurrencyBox = new JComboBox<>(new String[]{"USD", "EUR", "INR", "GBP", "JPY"});

        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();

        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result: ");

        // Add action listener to the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        // Add components to the frame
        add(fromLabel);
        add(fromCurrencyBox);
        add(toLabel);
        add(toCurrencyBox);
        add(amountLabel);
        add(amountField);
        add(convertButton);
        add(resultLabel);

        setVisible(true);
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String fromCurrency = (String) fromCurrencyBox.getSelectedItem();
            String toCurrency = (String) toCurrencyBox.getSelectedItem();

            // Get the exchange rate for both currencies
            double fromRate = exchangeRates.get(fromCurrency);
            double toRate = exchangeRates.get(toCurrency);

            // Perform the conversion
            double result = (amount / fromRate) * toRate;

            // Display the result
            resultLabel.setText(String.format("Result: %.2f %s", result, toCurrency));
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid amount!");
        }
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}