package model.journal;

//import model.tag.Tag;
import model.user.Parent;
import model.user.Peanut;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// represents a memory entry; owned by a Peanut with list of parents that authored on the entry
// it has an entry date and the event/memory date, a title, image, content of memory,
// a list of tags the memory is associated with, and peanut's height and weight at the time of the memory.
public class Memory implements Entry {
    private Peanut peanut;
    private List<Parent> authors = new ArrayList<Parent>();

    private LocalDateTime entryDate;
    private LocalDateTime memoryDate;
    private String title;
    private String image;                               //String for now, TODO: figure out how to add file path
    private String content;
    private Integer height;
    private Integer weight;
    private Integer age;                                // added variable for now, TODO: implement calculations of age
//    private List<Tag> tagList = new ArrayList<Tag>();

    // REQUIRES: non-null Peanut and Parent, entry made on earth's time space continuum
    // EFFECTS: create a Memory, peanut is set to pn, pr is added to authors list
    //          title and content initiated to empty strings;
    public Memory(Peanut pn, Parent pr) {
        this.entryDate = LocalDateTime.now();
        this.title = "";
        this.content = "";
        this.peanut = pn;
        this.authors.add(pr);
    }

    // MODIFIES: this
    // EFFECTS: update title
    public void addTitle(String title) {
        this.title = title;
    }

    // MODIFIES: this
    // EFFECTS: update content
    public void addContent(String content) {
        this.content = content;
    }

    // REQUIRES: existing filepath of a certain format (once switched type, still Sting for current phase)
    // MODIFIES: this
    // EFFECTS: add image
    public void addImage(String image) {
        this.image = image;
    }

    // REQUIRES: earth time
    // MODIFIES: this
    // EFFECTS: add memoryDate
    public void addMemoryDate(LocalDateTime memory) {
        this.memoryDate = memory;
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

    // REQUIRES: non-null Tag t
    // MODIFIES: this
    // EFFECTS: add Tag t to tagList in chronological order
//    public void addTag(Tag t) {
//        this.tagList.add(t);
//    }

    // REQUIRES: non-null Parent p
    // MODIFIES: this
    // EFFECTS: add Parent p into authors list
    // TODO: add a guard so Parent p is from a list of existing/registered Parent users
    // TODO: ideally modifies Parent p as well to add entry ref into their profile
    public void addAuthor(Parent p) {
        this.authors.add(p);
    }

    // EFFECTS: returns peanut
    public Peanut getPeanut() {
        return this.peanut;
    }

    // EFFECTS: returns authors
    public List<Parent> getAuthors() {
        return this.authors;
    }

    // EFFECTS: returns entryDate
    public LocalDateTime getEntryDate() {
        return this.entryDate;
    }

    // EFFECTS: returns title
    public String getTitle() {
        return this.title;
    }

    // EFFECTS: returns content
    public String getContent() {
        return this.content;
    }

    // EFFECTS: returns image; String for now, change type to file path later on
    public String getImage() {
        return this.image;
    }

    // EFFECTS: returns memoryDate
    public LocalDateTime getMemoryDate() {
        return this.memoryDate;
    }

    // EFFECTS: returns height
    public Integer getHeight() {
        return this.height;
    }

    // EFFECTS: returns weight
    public Integer getWeight() {
        return this.weight;
    }

    // EFFECTS: returns tagList
//    public List<Tag> getTags() {
//        return this.tagList;
//    }

    // REQUIRES: non-null Parent p
    // MODIFIES: this
    // EFFECTS: remove Parent p into authors
    public void deleteAuthor(Parent p) {
        this.authors.remove(p);
    }

    // MODIFIES: this
    // EFFECTS: turn title into null, effectively removing it
    public void deleteTitle() {
        this.title = null;
    }

    // MODIFIES: this
    // EFFECTS: turn content into null, effectively removing it
    public void deleteContent() {
        this.content = null;
    }

    // MODIFIES: this
    // EFFECTS: turn image into null, effectively removing it
    public void deleteImage() {
        this.image = null;
    }

    // MODIFIES: this
    // EFFECTS: turn memoryDate into null, effectively removing it
    public void deleteMemoryDate() {
        this.memoryDate = null;
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
        json.put("type", "memory");
        json.put("image", image);
        return json;
    }

}
