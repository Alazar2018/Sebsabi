package et.com.gebeya.safaricom.sebsabi.controller;

import et.com.gebeya.safaricom.sebsabi.model.Answer;
import et.com.gebeya.safaricom.sebsabi.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// AnswerController.java
@RestController
@RequiredArgsConstructor
@RequestMapping("/pi/core/forms/answers")
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Answer saveAnswer(@RequestBody Answer answer) {
        Answer savedAnswer = answerService.saveAnswer(answer);
        return savedAnswer;
    }
}
