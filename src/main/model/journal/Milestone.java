package model.journal;

import model.user.Parent;
import model.user.Peanut;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// represents a milestone entry; owned by a Peanut with list of parents that authored on the entry
// it has an id, entry date and event/milestone date, a title, image, content of milestone,
// completion status of milestone, and peanut's height/weight/age at the time of milestone
public class Milestone implements Entry {
    private final Integer id;                            // finite predetermined milestones constructed upon
                                                         // upon journal creation and individually id'd
    private Peanut peanut;
    private List<Parent> authors = new ArrayList<Parent>();

    private LocalDateTime entryDate;                    // added variable for now, TODO: figure out the entryDate
                                                        // of the user and not the construction date of milestone
    private LocalDateTime milestoneDate;
    private String title;
    private String image;                               //String for now, TODO: figure out how to add file path
    private String content;
    private Integer height;
    private Integer weight;
    private Boolean status;
    private Integer age;                                 // added variable for now, TODO: implement calculations of age


    private List<MilestoneQuestion> questions;          // added variable for now, TODO: Implement individual add/get
                                                        // eventually for-loop type of display

    // REQUIRES: id > 0 with no dups, non-empty String, non-null Peanut and Parent
    // EFFECTS: Creates a Milestone (pre-constructed by program and not users)
    //          peanut is set to pn, pr is added to authors list
    //          title and content initiated to empty strings;
    public Milestone(Integer id, String title, Parent pr, Peanut pn, List<MilestoneQuestion> q) {
        this.id = id;                                   // predetermined, no user add/remove
        this.title = title;
        this.authors.add(pr);
        this.peanut = pn;
        this.questions = q;                             // predetermined, no user add/remove
        this.status = false;                            // default is false, until parent enters
    }



    // REQUIRES: non-null Parent p
    // MODIFIES: this
    // EFFECTS: add Parent p into authors list
    // TODO: add a guard so Parent p is from a list of existing/registered Parent users
    // TODO: ideally modifies Parent p as well to add entry ref into their profile
    public void addAuthor(Parent p) {
        this.authors.add(p);
    }

    // REQUIRES: earth time
    // MODIFIES: this
    // EFFECTS: add milestoneDate
    public void addMilestoneDate(LocalDateTime date) {
        this.milestoneDate = date;
    }

    // REQUIRES: existing filepath of a certain format (once switched type, still Sting for current phase)
    // MODIFIES: this
    // EFFECTS: add image
    public void addImage(String image) {
        this.image = image;
    }

    // MODIFIES: this
    // EFFECTS: update comment
    public void addContent(String comment) {
        this.content = comment;
    }

    // REQUIRES: h > 0
    // MODIFIES: this
    // EFFECTS: add height
    public void addHeight(Integer h) {
        this.height = h;
    }

    // REQUIRES: w > 0
    // MODIFIES: this
    // EFFECTS: add weight
    public void addWeight(Integer w) {
        this.weight = w;
    }

    //EFFECTS: does not change title, titles are pre-determined
    public void addTitle(String s) {
        // no effect
    }

    // MODIFIES: this
    // EFFECTS: update status to true
    public void completedStatus() {
        this.status = true;
    }

    // MODIFIES: this
    // EFFECTS: update status to false
    public void incompleteStatus() {
        this.status = false;
    }

    // EFFECTS: returns peanut
    public Peanut getPeanut() {
        return this.peanut;
    }

    // EFFECTS: returns authors
    public List<Parent> getAuthors() {
        return this.authors;
    }

    // EFFECTS: returns id
    public Integer getID() {
        return this.id;
    }

    // EFFECTS: returns title
    public String getTitle() {
        return this.title;
    }

    // EFFECTS: returns status
    public Boolean getStatus() {
        return this.status;
    }

    // EFFECTS: returns milestoneDate
    public LocalDateTime getMilestoneDate() {
        return this.milestoneDate;
    }

    // EFFECTS: returns image
    public String getImage() {
        return this.image;
    }

    // EFFECTS: returns comment
    public String getContent() {
        return this.content;
    }

    // EFFECTS: returns height
    public Integer getHeight() {
        return this.height;
    }

    // EFFECTS: returns weight
    public Integer getWeight() {
        return this.weight;
    }

    // REQUIRES: non-null Parent p
    // MODIFIES: this
    // EFFECTS: remove Parent p into authors
    public void deleteAuthor(Parent p) {
        this.authors.remove(p);
    }

    // MODIFIES: this
    // EFFECTS: turn milestoneDate into null, effectively removing it
    public void deleteMilestoneDate() {
        this.milestoneDate = null;
    }

    // MODIFIES: this
    // EFFECTS: turn image into null, effectively removing it
    public void deleteImage() {
        this.image = null;
    }

    // MODIFIES: this
    // EFFECTS: turn comment into null, effectively removing it
    public void deleteComment() {
        this.content = null;
    }

    // MODIFIES: this
    // EFFECTS: turn height into null, effectively removing it
    public void deleteHeight() {
        this.height = null;
    }

    // MODIFIES: this
    // EFFECTS: turn weight into null, effectively removing it
    public void deleteWeight() {
        this.weight = null;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("content", content);
        json.put("type", "milestone");
        json.put("image", image);
        return json;
    }
}
