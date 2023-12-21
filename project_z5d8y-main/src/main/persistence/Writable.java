package persistence;

import org.json.JSONObject;

// (Use JsonSerializationDemo-master)
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

