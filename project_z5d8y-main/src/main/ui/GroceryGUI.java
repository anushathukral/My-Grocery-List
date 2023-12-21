package ui;

import model.Event;
import model.EventLog;
import model.GroceryItem;
import model.GroceryList;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

// Grocery List GUI made for adding, removing, totaling, saving, and loading items
public class GroceryGUI extends JFrame {

    private final Color yellow = new Color(255, 249, 229);
    private final Color green = new Color(153, 204, 153);
    private GroceryList groceryList;
    private JPanel inputPanel;
    private JPanel itemsPanel;
    private JTextField addTextField;
    private final ActionListener addAction = new ActionListener() {
        // EFFECTS: adds the item to the grocery list and displays it on the panel
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = addItemName();
            int price = addItemPrice();
            GroceryItem item = new GroceryItem(name, price);
            groceryList.addItem(item);
            displayGroceryList();
        }
    };

    private final ActionListener removeAction = new ActionListener() {
        // EFFECTS: removes the item from the grocery list and removes it from the panel
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = addItemName();
            groceryList.removeItem(name);
            displayGroceryList();
        }
    };

    private final ActionListener totalAction = new ActionListener() {
        // EFFECTS: shows the total price of all the items in grocery list and displays it on the panel
        @Override
        public void actionPerformed(ActionEvent e) {
            totalItems();
        }
    };

    private final ActionListener saveAction = new ActionListener() {
        // EFFECTS: saves the grocery list
        @Override
        public void actionPerformed(ActionEvent e) {
            setSaveAction();
            System.out.println("Grocery List Total: $ " + groceryList.getTotal());
        }
    };

    private final ActionListener loadAction = new ActionListener() {
        // EFFECTS: loads the previously saved grocery list and displays it on the panel
        @Override
        public void actionPerformed(ActionEvent e) {
            setLoadAction();
            System.out.println("Loaded List total: $" + groceryList.getTotal());
        }
    };

    private final ActionListener quitAction = new ActionListener() {
        // EFFECTS: quits the application
        @Override
        public void actionPerformed(ActionEvent e) {
            closeApplication();
        }
    };

    // EFFECTS: creates grocery GUI of a grocery list where you can add, remove, and total items
    public GroceryGUI() {
        super("Grocery List");
        initializeFields();
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS:  sets inputPanel and itemsPanel as new JPanels, sets the background for inputPanel,
    //           sets grocery list to a new grocery list and text field to a new text field
    //           this method is called by the DrawingEditor constructor
    private void initializeFields() {
        this.inputPanel = new JPanel();
        this.itemsPanel = new JPanel();
        this.inputPanel.setBackground(green);
        this.groceryList = new GroceryList();
        this.addTextField = new JTextField();
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this GroceryGUI will operate, and populates the panels to be used
    //           with the methods that manipulate this frame
    private void initializeGraphics() {
        this.setLayout(new BorderLayout());
        this.setTitle("My Grocery List");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.addButton();
        this.removeButton();
        this.totalButton();
        this.saveButton();
        this.loadButton();
        this.addImage();
        this.quitButton();
        this.displayGroceryList();
        this.inputPanel.add(addTextField);
        this.addTextField.setPreferredSize(new Dimension(200, 40));
        this.addTextField.setVisible(false);
        this.add(inputPanel);
        this.inputPanel.add(itemsPanel, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // EFFECTS: displays pop up window to add an item name
    public String addItemName() {
        String name = JOptionPane.showInputDialog("Enter item name:");
        return name;
    }

    // EFFECTS: displays pop up window to add an item price
    public int addItemPrice() {
        String priceStr = JOptionPane.showInputDialog("Enter item price:");
        int price = Integer.parseInt(priceStr);
        return price;
    }

    // MODIFIES: this
    // EFFECTS: produces the total of all the items in the grocery list on the itemsPanel
    public void totalItems() {
        JLabel label = new JLabel("Total: $" + groceryList.getTotal());
        itemsPanel.add(label);
        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS:  adds "add item" button adds given item to inputPanel
    public void addButton() {
        JButton button = new JButton("Add item");
        inputPanel.add(button);
        button.addActionListener(addAction);
    }

    // MODIFIES: this
    // EFFECTS:  adds "remove item" button and removes given item from inputPanel
    public void removeButton() {
        JButton button = new JButton("Remove item");
        inputPanel.add(button);
        button.addActionListener(removeAction);
    }

    // MODIFIES: this
    // EFFECTS:  adds "total items price" button on inputPanel
    public void totalButton() {
        JButton button = new JButton("Total items price");
        inputPanel.add(button);
        button.addActionListener(totalAction);
    }

    // MODIFIES: this
    // EFFECTS:  adds "save items" button on inputPanel
    public void saveButton() {
        JButton button = new JButton("Save items");
        inputPanel.add(button);
        button.addActionListener(saveAction);
    }

    // EFFECTS:  saves all the items added to the list to a JSON file
    public void setSaveAction() {
        JSONArray groceryItems = new JSONArray();
        for (GroceryItem item : groceryList.getAllItems()) {
            JSONObject object = new JSONObject();
            object.put("name", item.getName());
            object.put("price", item.getPrice());
            groceryItems.put(object);
        }
        try {
            FileWriter writer = new FileWriter("grocerylist.json");
            writer.write(groceryItems.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS:  adds "load items" button on inputPanel
    public void loadButton() {
        JButton button = new JButton("Load items");
        inputPanel.add(button);
        button.addActionListener(loadAction);
    }

    // EFFECTS:  loads all the items saved previously on JSON file to the itemsPanel
    public void setLoadAction() {
        File file = new File("grocerylist.json");
        if (!file.exists()) {
            System.out.println("File not found: " + file.getAbsolutePath());
            return;
        }
        try {
            String jsonString = Files.readString(Paths.get("grocerylist.json"));
            JSONArray groceryItems = new JSONArray(jsonString);

            for (int i = 0; i < groceryItems.length(); i++) {
                JSONObject itemObject = groceryItems.getJSONObject(i);
                String name = itemObject.getString("name");
                int price = itemObject.getInt("price");
                GroceryItem item = new GroceryItem(name, price);
                groceryList.addItem(item);
            }
            displayGroceryList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: uses itemsPanel to create a box to display all the items previously added
    public void displayGroceryList() {
        this.itemsPanel.setPreferredSize(new Dimension(200, 300));
        this.itemsPanel.setBackground(yellow);
        this.itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        this.itemsPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 10, 10));
        itemsPanel.removeAll();
        for (GroceryItem item : groceryList.getAllItems()) {
            JLabel label = new JLabel(item.getName() + "   $" + item.getPrice());
            itemsPanel.add(label);
        }
        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: creates a new panel for the title and image
    public void addImage() {
        JPanel titlePanel = new JPanel();
        titlePanel.setSize(new Dimension(800, 100));
        titlePanel.setBackground(yellow);
        JLabel title = new JLabel("My Grocery List");
        titlePanel.add(title);
        title.setFont(new Font("Tahoma", Font.PLAIN, 30));

        ImageIcon icon = new ImageIcon(getClass().getResource("groceries.png"));
        JLabel image = new JLabel(icon);

        titlePanel.add(image);
        add(titlePanel, BorderLayout.NORTH);
        titlePanel.revalidate();
        titlePanel.repaint();
    }

    // EFFECTS: creates the quit button
    public void quitButton() {
        JButton button = new JButton("Quit");
        inputPanel.add(button);
        button.addActionListener(quitAction);
    }

    // EFFECTS: closes the application and prints all event log actions in the console after quitting
    public void closeApplication() {
        System.out.println("Goodbye!");
        for (Event event : EventLog.getInstance()) {
            System.out.println(event);
        }
        this.dispose();
    }
}