package engine.model;

public class QuizAnswer {
    private boolean isSuccess;
    private String feedback;

    public QuizAnswer(boolean isSuccess, String feedback) {
        this.isSuccess = isSuccess;
        this.feedback = feedback;
    }

    public QuizAnswer() {
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
