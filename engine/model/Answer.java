package engine.model;

import java.util.Arrays;

public class Answer {
    private int [] answer;

    public Answer() {
    }

    public Answer(int[] answer) {
        this.answer = answer;
    }

    public int[] getAnswer() {
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer=" + Arrays.toString(answer) +
                '}';
    }

    @Override
    public boolean equals(Object o)
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer1 = (Answer) o;
        return Arrays.equals(answer, answer1.answer);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(answer);
    }
}
