package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


// Grocery application
// (Use JsonSerializationDemo-master) for save and load methods
public class GroceryApp {
    private static final String JSON_STORE = "./data/groceryList.json";
    private GroceryList groceryList;
    private Scanner inputString;
    private Scanner inputInt;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    // EFFECTS: runs the grocery application
    public GroceryApp() {
        groceryList = new GroceryList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTeller();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTeller() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = inputString.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("r")) {
            doRemoveItem();
        } else if (command.equals("t")) {
            doTotal();
        } else if (command.equals("a")) {
            doAddItem();
        } else if (command.equals("s")) {
            saveWorkRoom();
        } else if (command.equals("l")) {
            loadWorkRoom();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes grocery items
    private void init() {
        inputString = new Scanner(System.in);
        inputString.useDelimiter("\n");

        inputInt = new Scanner(System.in);
        inputInt.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add an item");
        System.out.println("\tr -> remove an item");
        System.out.println("\tt -> get total price of list");
        System.out.println("\ts -> save work room to file");
        System.out.println("\tl -> load work room from file");
        System.out.println("\tq -> quit");
    }


    // MODIFIES: this
    // EFFECTS: adds a grocery item to the grocery list
    private void doAddItem() {
        System.out.print("Enter new item name: ");
        String itemName = inputString.nextLine();
        System.out.print("Enter new item price: ");
        int itemPrice = inputInt.nextInt();
        GroceryItem groceryItem = new GroceryItem(itemName, itemPrice);

        groceryList.addItem(groceryItem);
        System.out.println("Item added to the list! \n");

        printGroceryList(groceryList);
    }

    // MODIFIES: this
    // EFFECTS: removes a grocery item to the grocery list
    private void doRemoveItem() {
        System.out.print("Enter item name to remove: ");
        String itemName = inputString.nextLine();

        groceryList.removeItem(itemName);
        System.out.println("Item removed from the list!...\n");

        printGroceryList(groceryList);
    }

    // MODIFIES: this
    // EFFECTS: totals the prices of a grocery items in a grocery list
    private void doTotal() {
        System.out.print("Total: "  + "$" + groceryList.getTotal());
    }


    // EFFECTS: prints grocery items in grocery list to the screen
    private void printGroceryList(GroceryList selected) {
        if (selected != null) {
            System.out.println("Grocery List: \n");
            for (GroceryItem item : selected.getAllItems()) {
                System.out.println(item.getName() + " $" + item.getPrice());
            }
        }
    }

    // EFFECTS: saves the grocery list to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(groceryList);
            jsonWriter.close();
            System.out.println("Saved grocery list to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads grocery list from file
    private void loadWorkRoom() {
        try {
            groceryList = jsonReader.read();
            System.out.println("Loaded grocery list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
