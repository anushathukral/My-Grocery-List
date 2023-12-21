package model;

import org.json.JSONObject;
import persistence.Writable;


// Creates a single grocery item with a name and price
public class GroceryItem implements Writable {
    private final String name;
    private final int price;

    // EFFECTS: creates a grocery item with a name and a price(in cents)
    public GroceryItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // EFFECTS: returns the name of the grocery item
    public String getName() {
        return name;
    }

    // EFFECTS: returns the price(in cents) of the grocery item
    public int getPrice() {
        return price;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        return json;
    }
}
