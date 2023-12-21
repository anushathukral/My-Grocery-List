package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// creates a Grocery List that contains GroceryItem
public class GroceryList implements Writable {
    private final List<GroceryItem> items;

    // REQUIRES: list contains only grocery items with the price always being >=0
    // EFFECTS: creates a grocery list that contains grocery items which have a name and a price(in cents)
    public GroceryList() {
        this.items = new ArrayList<>();
    }

    // EFFECTS: returns the first item of the list, if empty return null
    public GroceryItem getFirstItem() {
        if (!(items.isEmpty())) {
            return items.get(0);
        }
        return null;
    }

    // EFFECTS: adds a grocery item to the grocery list
    public void addItem(GroceryItem food) {
        items.add(food);
        EventLog.getInstance().logEvent(new Event("Grocery item added to the list!"));
    }

    // EFFECTS: removes a grocery item to the grocery list
    public void removeItem(String food) {
        for (int i = 0; i < items.size(); i++) {
            if (food.equals(items.get(i).getName())) {
                items.remove(i);
                EventLog.getInstance().logEvent(new Event("Grocery item removed from the list!"));
            }
        }
    }

    // EFFECTS: returns true if the grocery list contains a grocery item
    public boolean containsItem(GroceryItem food) {
        return items.contains(food);
    }

    // EFFECTS: returns true if the grocery list contains a grocery item
    public List<GroceryItem> getAllItems() {
        return items;
    }

    // REQUIRES: total amount(in cents) is >= 0
    // MODIFIES: this, total
    // EFFECTS: returns the total amount of all items in the list
    public int getTotal() {
        int total = 0;
        if (!items.isEmpty()) {
            for (GroceryItem item : items) {
                total += item.getPrice();
            }
        }
        EventLog.getInstance().logEvent(new Event("Total Price of list calculated!"));
        return total;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("grocery list: ", groceriesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray groceriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (GroceryItem i : items) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }

    //EFFECTS: prints the events from the event log
    public void logPrinter(EventLog eventLog) {
        for (Event e : eventLog) {
            System.out.println(e.getDescription());
        }
    }
}
