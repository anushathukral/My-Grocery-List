package test;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// (Use JsonSerializationDemo-master)
public class JsonTest {
    protected void checkGroceryItem(String name, int price) {
        GroceryItem item = new GroceryItem(name, price);
        assertEquals(name, item.getName());
        assertEquals(price, item.getPrice());
    }
}
