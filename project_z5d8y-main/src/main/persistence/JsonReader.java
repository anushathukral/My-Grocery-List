package persistence;

import model.*;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads groceries from JSON data stored in file
// (Use JsonSerializationDemo-master)
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads grocery list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GroceryList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGroceryList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses GroceryList from JSON object and returns it
    private GroceryList parseGroceryList(JSONObject jsonObject) {
        GroceryList gl = new GroceryList();
        JSONArray jsonArray = jsonObject.getJSONArray("grocery list: ");
        for (Object json : jsonArray) {
            JSONObject x = (JSONObject) json;
            String name = x.getString("name");
            int price = x.getInt("price");

            GroceryItem groceryItem = new GroceryItem(name, price);
            gl.addItem(groceryItem);
        }
        return gl;
    }
}