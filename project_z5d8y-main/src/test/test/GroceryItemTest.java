package test;

import model.GroceryItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroceryItemTest {
    private GroceryItem item1;
    private GroceryItem item2;

    @BeforeEach
    public void runBefore() {
        item1 = new GroceryItem("Carrots", 200);
        item2 = new GroceryItem("Peppers", 350);
    }

    @Test
    public void testGetName() {
        assertEquals("Carrots", item1.getName());
        assertEquals("Peppers", item2.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(200, item1.getPrice());
        assertEquals(350, item2.getPrice());
    }
}

