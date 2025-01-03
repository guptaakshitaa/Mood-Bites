import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ShoppingCartApp {
public static void main(String[] args) {
// Create frame
JFrame frame = new JFrame("Shopping Cart");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(400, 400);
// Panel for layout
JPanel panel = new JPanel();
panel.setLayout(new BorderLayout());
// List of available items
String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};
JList<String> itemList = new JList<>(items);
JScrollPane itemScroll = new JScrollPane(itemList);
itemScroll.setPreferredSize(new Dimension(150, 150));
// Button to add to cart
// Cart list
DefaultListModel<String> cartModel = new DefaultListModel<>();
JList<String> cartList = new JList<>(cartModel);
JScrollPane cartScroll = new JScrollPane(cartList);
cartScroll.setPreferredSize(new Dimension(150, 150));
JButton addButton = new JButton("Add to Cart");
// Add action listener to the button
addButton.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
String selectedItem = itemList.getSelectedValue();
if (selectedItem != null && !cartModel.contains(selectedItem)) {
cartModel.addElement(selectedItem);
}
}
});
// Layout components
panel.add(new JLabel("Items"), BorderLayout.WEST);
panel.add(itemScroll, BorderLayout.CENTER);
panel.add(addButton, BorderLayout.SOUTH);
panel.add(new JLabel("Cart"), BorderLayout.EAST);
panel.add(cartScroll, BorderLayout.LINE_END);
// Add panel to frame
frame.add(panel);
// Set frame visible
frame.setVisible(true);
}
}