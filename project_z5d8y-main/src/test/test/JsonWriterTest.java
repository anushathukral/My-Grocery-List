package test;

import model.*;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// (Use JsonSerializationDemo-master)
class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            GroceryList gl = new GroceryList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            List<GroceryItem> groceryItems = gl.getAllItems();
            assertEquals(groceryItems, gl.getAllItems());
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyGroceryList() {
        try {
            GroceryList gl = new GroceryList();
            JsonWriter writer = new JsonWriter("./data/testEmptyGroceryList.json");
            writer.open();
            writer.write(gl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyGroceryList.json");
            gl = reader.read();
            List<GroceryItem> groceryItems = gl.getAllItems();
            assertEquals(groceryItems, gl.getAllItems());
            assertEquals(0, gl.getTotal());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralGroceryList() {
        try {
            GroceryList gl = new GroceryList();
            gl.addItem(new GroceryItem("carrots", 200));
            gl.addItem(new GroceryItem("peppers", 350));
            JsonWriter writer = new JsonWriter("./data/testGeneralGroceryList.json");
            writer.open();
            writer.write(gl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testGeneralGroceryList.json");
            gl = reader.read();
            assertEquals(550, gl.getTotal());
            checkGroceryItem("carrots", 200);
            checkGroceryItem("peppers", 350);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
