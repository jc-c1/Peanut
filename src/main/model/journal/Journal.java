package model.journal;

import model.Event;
import model.EventLog;
import persistence.Writable;
import model.user.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;

// represents a journal; owned by a Peanut with a mainParent (journal creator), list of parents that
// can collaborate on the journal, list of entries in the journal (with types of entries being
// Memory and Milestone. A list of predetermined Milestone questions is also created.
public class Journal implements Writable {
    private final Peanut peanut;                                             // main character of journal
    private Parent mainParent;                                               // creator of journal
    private List<Parent> parents = new ArrayList<Parent>();                       // list of journal collaborator
    private List<Entry> index = new ArrayList<Entry>();                          // list of entries in journal

    private List<Milestone> milestones = new ArrayList<Milestone>();                 // list of milestones
    private List<MilestoneQuestion> sampleQuestions1 = new ArrayList<MilestoneQuestion>();   // sample questions
    private List<MilestoneQuestion> sampleQuestions2 = new ArrayList<MilestoneQuestion>();
    private List<MilestoneQuestion> sampleQuestions3 = new ArrayList<MilestoneQuestion>();
    private List<MilestoneQuestion> sampleQuestions4 = new ArrayList<MilestoneQuestion>();

    // REQUIRES: non-null Peanut and Parent
    // EFFECTS: peanut is set to pn, mainParent is set to pr, pr is added to parents list
    //          Milestones with associated questions are added to milestones list
    public Journal(Peanut pn, Parent pr) {
        this.peanut = pn;
        this.mainParent = pr;
        this.parents.add(pr);
        this.milestones.add(new Milestone(1, "a", null, pn, sampleQuestions1));
        this.milestones.add(new Milestone(2, "b", null, pn, sampleQuestions2));
        this.milestones.add(new Milestone(3, "c", null, pn, sampleQuestions3));
        this.milestones.add(new Milestone(4, "d", null, pn, sampleQuestions4));
        this.milestones.add(new Milestone(5, "e", null, pn, sampleQuestions1));
    }

    // EFFECTS: returns peanut
    public Peanut getPeanut() {
        return this.peanut;
    }

    // EFFECTS: returns mainParent
    public Parent getMainParent() {
        return this.mainParent;
    }

    // REQUIRES: non-null Parent p
    // MODIFIES: this
    // EFFECTS: updates mainParent with Parent p
    public void updateMainParent(Parent p) {
        this.mainParent = p;
    }

    // EFFECTS: returns parents
    public List<Parent> getParents() {
        return this.parents;
    }

    // REQUIRES: non-null Parent p
    // MODIFIES: this
    // EFFECTS: adds Parent p to parents in chronological order of joining
    public void addParent(Parent p) {
        this.parents.add(p);
    }

    // REQUIRES: parents.contains(p)
    // MODIFIES: this
    // EFFECTS: removes Parent p from parents
    public void removeParent(Parent p) {
        parents.remove(p);
    }

    // EFFECTS: returns index
    public List<Entry> getIndex() {
        return this.index;
    }

    // REQUIRES: 0 < id < index.size()
    // EFFECTS: returns id-ith (array index + 1) entry in index,
    //          returns null if id <= 0 or out of bounds id entered
    public Entry getEntryByIndex(Integer id) {
        if ((id < 1) || (id > this.index.size())) {
            return null;
        }
        return this.index.get(id - 1);
    }

    // REQUIRES: non-null Entry e
    // MODIFIES: this
    // EFFECTS: adds entry into index in chronological order
    public void addEntry(Entry e) {
        this.index.add(e);
        EventLog.getInstance().logEvent(new Event("Added a new memory, titled \"" + e.getTitle() + "\"."));
    }

    // REQUIRES: non-null Entry e
    // MODIFIES: this + Entry e if Milestone
    // EFFECTS: removes Entry e from index and if Entry e is of type Milestone then
    //          updates milestone status to incomplete.
    public void removeEntry(Entry e) {
        if (e instanceof Milestone) {
            ((Milestone) e).incompleteStatus();
        }
        this.index.remove(e);
        EventLog.getInstance().logEvent(new Event("Deleted a new memory, titled \"" + e.getTitle() + "\"."));
    }

    // EFFECTS: return milestones list
    public List<Milestone> getAllMilestones() {
        return this.milestones;
    }

    // REQUIRES: 0 < id < milestones.size()
    // EFFECTS: returns id-ith (array index + 1) Milestone in milestones,
    //          returns null if id <= 0 or out of bounds id entered
    public Milestone getMilestoneByIndex(Integer id) {
        if ((id < 1) || (id > this.milestones.size())) {
            return null;
        }
        return this.milestones.get(id - 1);
    }

    // EFFECTS: returns a list of incomplete milestones from list milestones
    public List<Milestone> getIncompleteMilestones() {
        List<Milestone> incompleteMilestones = new ArrayList<Milestone>();
        for (Milestone m : milestones) {
            if (m.getStatus() == false) {
                incompleteMilestones.add(m);
            }
        }
        return incompleteMilestones;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("index", entriesToJson());
        return json;
    }

    // EFFECTS: returns Entries in this Journal as a JSON array
    private JSONArray entriesToJson() {
        JSONArray jsonEntries = new JSONArray();

        for (Entry e : index) {
            jsonEntries.put(e.toJson());
        }

        return jsonEntries;
    }
}
