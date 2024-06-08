package model.journal;

// represents a milestone question with a question and an answer pair
// TODO: perhaps add an id so that can loop through and assign, have to think about how to design this more
public class MilestoneQuestion {
    private String question;        // predetermined question
    private String answer;          // user inputted answer

    // EFFECTS: Constructs a MilestoneQuestion with question of q
    public MilestoneQuestion(String q) {
        this.question = q;
    }

    // EFFECTS: returns question
    public String getQuestion() {
        return this.question;
    }

    // EFFECTS: returns answer
    public String getAnswer() {
        return this.answer;
    }

    // MODIFIES: this
    // EFFECTS: adds answer
    public void addAnswer(String a) {
        this.answer = a;
    }

    // MODIFIES: this
    // EFFECTS: turns answer into null, effectively deleting it
    public void deleteAnswer() {
        this.answer = null;
    }
}
