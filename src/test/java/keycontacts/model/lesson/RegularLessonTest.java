package keycontacts.model.lesson;

import static keycontacts.testutil.Assert.assertThrows;
import static keycontacts.testutil.TypicalStudents.ALICE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import keycontacts.testutil.RegularLessonBuilder;

public class RegularLessonTest {

    @Test
    public void constructor_allFieldsNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RegularLesson(null, null, null));
    }

    @Test
    public void constructor_lessonDayNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RegularLesson(null, new Time("10:00"), new Time("11:00")));
    }

    @Test
    public void constructor_startTimeAfterEndTime_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                new RegularLesson(new Day("Monday"), new Time("11:00"), new Time("10:00")));
    }

    @Test
    public void equals() {
        RegularLesson regularLesson1 = new RegularLessonBuilder().build();
        RegularLesson regularLesson1Duplicate = new RegularLessonBuilder().build();
        // different start time
        RegularLesson regularLesson2 = new RegularLessonBuilder().withStartTime("15:00").build();;
        // different end time
        RegularLesson regularLesson3 = new RegularLessonBuilder().withEndTime("19:00").build();
        // different day
        RegularLesson regularLesson4 = new RegularLessonBuilder().withLessonDay("Tuesday").build();

        assertFalse(regularLesson1.equals(regularLesson2));
        assertFalse(regularLesson2.equals(regularLesson1));

        assertFalse(regularLesson1.equals(regularLesson3));
        assertFalse(regularLesson3.equals(regularLesson1));

        assertFalse(regularLesson1.equals(regularLesson4));
        assertFalse(regularLesson4.equals(regularLesson1));

        // same object
        assertTrue(regularLesson1.equals(regularLesson1));

        // same values
        assertTrue(regularLesson1Duplicate.equals(regularLesson1Duplicate));
        assertTrue(regularLesson1Duplicate.equals(regularLesson1));

        // null check
        assertFalse(regularLesson1.equals(null));

        // not a regular lesson
        assertFalse(regularLesson1.equals(""));
    }

    @Test
    public void toStringMethod() {
        RegularLesson regularLesson = ALICE.getRegularLesson();
        String expected = RegularLesson.class.getCanonicalName() + "{lessonDay=" + regularLesson.getLessonDay()
                + ", startTime=" + regularLesson.getStartTime() + ", endTime=" + regularLesson.getEndTime() + "}";
        assertEquals(expected, regularLesson.toString());
    }

    @Test
    public void hashCodeMethod() {
        RegularLesson regularLesson = ALICE.getRegularLesson();
        assertEquals(Objects.hash(regularLesson.getLessonDay(), regularLesson.getStartTime(),
                        regularLesson.getEndTime()), regularLesson.hashCode());
    }
}
