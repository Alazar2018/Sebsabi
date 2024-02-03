package et.com.gebeya.safaricom.sebsabi.model;

import et.com.gebeya.safaricom.sebsabi.model.enums.QuestionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FormQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;
    private QuestionType questionType;
    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;  // Add this field


}
