package et.com.gebeya.safaricom.sebsabi.service;


import et.com.gebeya.safaricom.sebsabi.dto.FormDto;
import et.com.gebeya.safaricom.sebsabi.dto.FormQuestionDto;
import et.com.gebeya.safaricom.sebsabi.dto.UserResponseDto;
import et.com.gebeya.safaricom.sebsabi.model.*;
import et.com.gebeya.safaricom.sebsabi.model.enums.QuestionType;
import et.com.gebeya.safaricom.sebsabi.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class FormService {
    private final FormRepository formRepository;
    private final GigWorkerService gigWorkerService;
    private final UserResponseService userResponseService;

    public Form createForm(FormDto formDTO) throws InvocationTargetException, IllegalAccessException {
        Form form = new Form();
        BeanUtils.copyProperties(formDTO, form);
        return formRepository.save(form);
    }
    public Form addQuestionToForm(Long formId, FormQuestionDto questionDTO) throws InvocationTargetException, IllegalAccessException {
        Form form = getFormById(formId);

        FormQuestion question = new FormQuestion();
        BeanUtils.copyProperties(questionDTO, question);

        form.getQuestions().add(question);
        question.setForm(form);

        return formRepository.save(form);
    }
    public List<Form> getAllForms() {
        return formRepository.findAll();
    }
    public Form getFormById(Long id) {
        return formRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Form not found with id: " + id));
    }

    public Form updateForm(Long id, FormDto formDTO) throws InvocationTargetException, IllegalAccessException {
        Form existingForm = getFormById(id);
        BeanUtils.copyProperties(formDTO, existingForm);
        return formRepository.save(existingForm);
    }

    public Form addQuestionToForm(Long formId, String questionText, QuestionType questionType) {
        Form form = formRepository.findById(formId).orElseThrow(() -> new RuntimeException("Form not found"));

        FormQuestion question = new FormQuestion();
        question.setQuestionText(questionText);
        question.setQuestionType(questionType);

        form.getQuestions().add(question);

        return formRepository.save(form);
    }

    public UserResponse submitResponse(UserResponseDto responseDTO) {
        Form form = getFormById(responseDTO.getFormId());
        FormQuestion question = form.getQuestions().stream()
                .filter(q -> q.getId().equals(responseDTO.getQuestionId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Question not found in the form"));

        GigWorker gigWorker = gigWorkerService.getGigWorkerById(responseDTO.getGigWorkerId());

        UserResponse userResponse = new UserResponse();
        userResponse.setForm(form);
        userResponse.setQuestion(question);
        userResponse.setGigWorker(gigWorker);

        // Create Answer entity and set answerText
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setAnswerText(responseDTO.getUserAnswer());

        // Set the answer in the UserResponse entity
        userResponse.setAnswers(Collections.singletonList(answer));

        return userResponseService.saveResponse(userResponse);
    }
}
