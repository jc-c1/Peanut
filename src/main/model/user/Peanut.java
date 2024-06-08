package model.user;

import model.journal.Journal;
import persistence.Writable;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// represents a Peanut, extends User, has a mainParent (parent that created this peanut),
// a Journal, list of Parent, and a list of siblings
public class Peanut extends User implements Writable {
    private List<Peanut> siblingOfPeanut = new ArrayList<Peanut>();
    private List<Parent> parentOfPeanut = new ArrayList<Parent>();
    private Parent mainParent;
    private Journal journal;

    // REQUIRES: non-empty fn and ln, non-null pr
    // EFFECTS: Constructs Peanut with first and last name (fn and ln), mainParent of parent pr creating
    // Peanut, Parent pr is added to parentOfPeanut list, Journal for peanut is created with mainParent
    // set as pr, peanut is added to pr's list of peanuts
    public Peanut(String fn, String ln, Parent pr) {
        super(fn, ln);
        this.mainParent = pr;
        this.parentOfPeanut.add(pr);
        this.journal = new Journal(this, mainParent);
        pr.addPeanutsOfParent(this);
    }

    // REQUIRES: non-null sibling Peanuts p
    // MODIFIES: this
    // EFFECTS: adds Parent p into siblingOfPeanut
    // TODO: add this peanut to sibling peanut
    public void addSiblingOfPeanut(Peanut p) {
        this.siblingOfPeanut.add(p);
    }

    // REQUIRES: non-null Parent
    // MODIFIES: this
    // EFFECTS: adds Parent into parentOfPeanut
    // TODO: add this peanut to parent's list of peanuts
    public void addParentOfPeanut(Parent p) {
        this.parentOfPeanut.add(p);
    }

    // returns Journal
    public Journal getJournal() {
        return journal;
    }

    // returns siblingOfPeanut
    public List<Peanut> getSiblingsOfPeanut() {
        return this.siblingOfPeanut;
    }

    // returns parentOfPeanut
    public List<Parent> getParentsOfPeanut() {
        return this.parentOfPeanut;
    }

    // REQUIRES: non-null Peanut
    // MODIFIES: this, peanut p
    // EFFECTS: removes sibling Peanut from siblingOfPeanut
    // TODO: remove this Peanut from p.siblingOfPeanut
    public void removeSiblingOfPeanut(Peanut p) {
        this.siblingOfPeanut.remove(p);
    }

    // REQUIRES: non-null Parent
    // MODIFIES: this, Parent p
    // EFFECTS: removes Parent parentOfPeanut and remove Peanut from p.peanutsOfParent
    public void removeParentOfPeanut(Parent p) {
        this.parentOfPeanut.remove(p);
        p.getPeanutsOfParent().remove(this);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("fn", firstName);
        json.put("ln", lastName);
        json.put("j", journal.toJson());
        return json;
    }

}
