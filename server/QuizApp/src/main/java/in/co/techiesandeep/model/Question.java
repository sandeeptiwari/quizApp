package in.co.techiesandeep.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "question")
public class Question extends BaseModel implements UserOwned {

    @Size(min = 2, max = 150, message = "The question should be between 2 and 150 characters")
    @NotNull(message = "Question text not provided")
    private String text;

    @ManyToOne
    @JsonIgnore
    private Quiz quiz;

    @Column(name = "a_order")
    private Integer oreder;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> answers;

    @OneToOne
    private Answer correctAnswer;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Calendar createdDate;

    @JsonIgnore
    private boolean isActive;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Integer getOreder() {
        return oreder;
    }

    public void setOreder(Integer oreder) {
        this.oreder = oreder;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Answer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public User getUser() {
        return quiz.getUser();
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", quiz=" + quiz +
                ", oreder=" + oreder +
                ", answers=" + answers +
                ", correctAnswer=" + correctAnswer +
                ", createdDate=" + createdDate +
                ", isActive=" + isActive +
                '}';
    }
}
