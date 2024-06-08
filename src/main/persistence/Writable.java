package persistence;

import org.json.JSONObject;

// Persistence package inspired by: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// represents interface for any class that is to be written into file destination
public interface Writable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
