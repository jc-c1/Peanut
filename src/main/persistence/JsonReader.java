package persistence;

import model.user.*;
import model.journal.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Persistence package inspired by: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads ParentList from JSON data stored in file
public class JsonReader {
    private String source;

    // MODIFIES: this
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ParentList from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public ParentList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseParentList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ParentList from JSON object and returns it
    private ParentList parseParentList(JSONObject jsonObject) {
        ParentList pl = new ParentList();
        addParents(pl, jsonObject);
        return pl;
    }

    // MODIFIES: pl
    // EFFECTS: parses parents from JSON object and adds them to ParentList
    private void addParents(ParentList pl, JSONObject jsonParentList) {
        JSONArray jsonParents = jsonParentList.getJSONArray("parentList");
        for (Object jpr : jsonParents) {
            JSONObject jsonParent = (JSONObject) jpr;
            addParent(pl, jsonParent);
        }
    }

    // MODIFIES: parents in ParentList
    // EFFECTS: parses parent from JSON object and adds it to parents of ParentList
    private void addParent(ParentList pl, JSONObject jsonParent) {
        String fn = jsonParent.getString("fn");
        String ln = jsonParent.getString("ln");
        JSONArray jsonPeanuts = jsonParent.getJSONArray("pnOfPr");

        Parent pr = new Parent(fn, ln);

        for (Object jpn: jsonPeanuts) {
            JSONObject jsonPeanut = (JSONObject) jpn;
            addPeanut(pr, jsonPeanut);
        }

        pl.addParent(pr);
    }

    // MODIFIES: Parent and Peanut
    // EFFECTS: parses Peanut from JSON object and adds it to Parent then parses Index/Journal and adds to Peanut
    private void addPeanut(Parent pr, JSONObject jsonPeanut) {
        String fn = jsonPeanut.getString("fn");
        String ln = jsonPeanut.getString("ln");
        JSONObject jsonJournal = jsonPeanut.getJSONObject("j");
        JSONArray jsonIndex = jsonJournal.getJSONArray("index");
        Peanut pn = new Peanut(fn, ln, pr);
        List<Entry> index = pn.getJournal().getIndex();
        for (Object e: jsonIndex) {
            JSONObject jsonEntry = (JSONObject) e;
            addEntry(index, jsonEntry, pn, pr);
        }

    }

    // MODIFIES: Journal Index
    // EFFECTS: parses Entries from JSON object and adds it to Journal
    private void addEntry(List i, JSONObject jsonEntry, Peanut pn, Parent pr) {
        String title = jsonEntry.getString("title");
        String content = jsonEntry.getString("content");
        String type = jsonEntry.getString("type");
        String image = jsonEntry.getString("image");
        Entry e;

        if (type.equals("milestone")) {
            e = new Milestone(1, title, pr, pn, new ArrayList<MilestoneQuestion>());
        } else {
            e = new Memory(pn, pr);
        }
        e.addTitle(title);
        e.addContent(content);
        e.addImage(image);
        i.add(e);
    }

}
