package model.user;

import model.Event;
import model.EventLog;
import persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

// represents a Parent, extends User, has a list of Peanuts and partners that collaborate with
public class Parent extends User implements Writable {
    private List<Peanut> peanutsOfParent = new ArrayList<Peanut>();
    private List<Parent> partnersOfParent = new ArrayList<Parent>();

    // REQUIRES: non-empty fn and ln
    // EFFECTS: Constructs Parent with first and last name
    public Parent(String fn, String ln) {
        super(fn, ln);
        EventLog.getInstance().logEvent(
                new Event("Added a new parent, named \"" + this.getFirstName() + "\"."));
    }

    // REQUIRES: non-null Peanut
    // MODIFIES: this
    // EFFECTS: adds Peanut into peanutsOfParent
    public void addPeanutsOfParent(Peanut p) {
        this.peanutsOfParent.add(p);
        EventLog.getInstance().logEvent(
                new Event("Added a new peanut, named \"" + p.getFirstName() + "\" to"
                        + " parent \"" + this.getFirstName() + "\"."));
    }

    // REQUIRES: non-null Parent
    // MODIFIES: this
    // EFFECTS: adds Parent into partnersOfParent
    public void addPartnersOfParent(Parent p) {
        this.partnersOfParent.add(p);
    }

    // EFFECTS: returns peanutsOfParent
    public List<Peanut> getPeanutsOfParent() {
        return this.peanutsOfParent;
    }

    // REQUIRES: 0 < id < peanutsOfParent.size()
    // EFFECTS: returns the peanut at the given id-1-ith in array index
    public Peanut getPeanutByIndex(Integer id) {
        if ((id < 1) || (id > this.peanutsOfParent.size())) {
            return null;
        }
        return this.peanutsOfParent.get(id - 1);
    }

    // EFFECTS: returns partnersOfParent
    public List<Parent> getPartnersOfParent() {
        return this.partnersOfParent;
    }

    // REQUIRES: non-null Peanut
    // MODIFIES: this, peanut p
    // EFFECTS: removes Peanut from peanutsOfParent and remove Parent from p.parentsOfPeanut
    public void removePeanutsOfParent(Peanut p) {
        this.peanutsOfParent.remove(p);
        p.getParentsOfPeanut().remove(this);
    }

    // REQUIRES: non-null Parent
    // MODIFIES: this
    // EFFECTS: removes Parent from partnerOfParent
    // TODO: Remove Parent from p.partnersOfPeanut
    public void removePartnersOfParent(Parent p) {
        this.partnersOfParent.remove(p);
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("fn", firstName);
        json.put("ln", lastName);
        json.put("pnOfPr", peanutsToJson());

        return json;
    }

    // EFFECTS: returns Peanuts in this peanutsOfParent as a JSON array
    private JSONArray peanutsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Peanut p : peanutsOfParent) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

}
