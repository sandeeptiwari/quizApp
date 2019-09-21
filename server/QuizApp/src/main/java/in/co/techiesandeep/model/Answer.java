package in.co.techiesandeep.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;

@Entity
@Table(name = "answer")
public class Answer extends BaseModel implements UserOwned{

    @Size(min = 1, max = 60, message = "The answer should be between 1 and 60 characters")
    @NotNull(message = "No answer text provided.")
    private String text;

    @ManyToOne
    @JsonIgnore
    private Question question;

    @Column(name = "a_order")
    private Integer order;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Calendar createdDate;

    @Override
    public User getUser() {
        return question.getUser();
    }

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", question=" + question +
                ", order=" + order +
                ", createdDate=" + createdDate +
                '}';
    }
}
