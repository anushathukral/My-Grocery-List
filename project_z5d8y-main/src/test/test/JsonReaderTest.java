package test;

import model.*;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// (Use JsonSerializationDemo-master)
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            GroceryList gl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGroceryList() {
        JsonReader reader = new JsonReader("./data/testEmptyGroceryList.json");
        try {
            GroceryList gl = reader.read();
            List<GroceryItem> empty = gl.getAllItems();
            assertEquals(empty, gl.getAllItems());
            assertEquals(0, gl.getTotal());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGroceryList() {
        GroceryItem item1 = new GroceryItem("carrots", 200);
        GroceryItem item2 = new GroceryItem("peppers", 350);
        JsonReader reader = new JsonReader("./data/testGeneralGroceryList.json");
        try {
            GroceryList gl = reader.read();
            assertEquals(550, gl.getTotal());
            checkGroceryItem("carrots", 200);
            checkGroceryItem("peppers", 350);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
