package model.journaltest;

import model.journal.MilestoneQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// represents tests for MilestoneQuestion
public class MilestoneQuestionTest {
    private MilestoneQuestion milestoneQuestion;

    @BeforeEach
    public void runBefore() {
        milestoneQuestion = new MilestoneQuestion("test question");
    }

    @Test
    public void testGetQuestion() {
        assertEquals("test question", milestoneQuestion.getQuestion());
    }

    @Test
    public void testAddAnswerGetAnswer() {
        assertEquals(null, milestoneQuestion.getAnswer());
        milestoneQuestion.addAnswer("test answer");
        assertEquals("test answer", milestoneQuestion.getAnswer());
    }

    @Test
    public void testDeleteAnswer() {
        assertEquals(null, milestoneQuestion.getAnswer());
        milestoneQuestion.addAnswer("test answer");
        assertEquals("test answer", milestoneQuestion.getAnswer());
        milestoneQuestion.deleteAnswer();
        assertEquals(null, milestoneQuestion.getAnswer());
    }
}
