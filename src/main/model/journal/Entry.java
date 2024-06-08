package model.journal;

import org.json.JSONObject;

// Interface of 2 subtypes of journal entries: Memory and Milestone.
// TODO: refactor all the duplications with Milestone and Memory and
//       perhaps make a abstract class since there's a lot of duplications
public interface Entry {

    // EFFECTS: returns title of entry
    String getTitle();

    // EFFECTS: returns content of entry
    String getContent();

    // EFFECTS: adds title to entry
    void addTitle(String s);

    // EFFECTS: adds content to entry
    void addContent(String s);

    void addImage(String s);

    String getImage();

    // EFFECTS: returns this as JSON object
    JSONObject toJson();


}
