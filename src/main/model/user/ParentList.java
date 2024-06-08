package model.user;

import model.Event;
import model.EventLog;
import persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


// Represents a list of Parents in the PeanutApp
public class ParentList implements Writable {
    private List<Parent> parents = new ArrayList<Parent>();

    // EFFECTS: Constructs ParentList
    public ParentList() {
    }

    // EFFECTS: returns parents
    public List<Parent> getParents() {
        return parents;
    }

    // REQUIRES: 0 < id < peanutsOfParent.size()
    // EFFECTS: returns the peanut at the given id-1-ith in array index
    public Parent getParentByIndex(Integer id) {
        if ((id < 1) || (id > this.parents.size())) {
            return null;
        }
        return this.parents.get(id - 1);
    }

    // REQUIRES: non-null Parent
    // MODIFIES: this
    // EFFECTS: adds Parent to parents
    public void addParent(Parent pr) {
        parents.add(pr);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("parentList", parentsToJson());
        return json;
    }

    // EFFECTS: returns Parent in parents as a JSON array
    private JSONArray parentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Parent p : parents) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }




}