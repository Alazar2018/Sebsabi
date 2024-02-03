package et.com.gebeya.safaricom.sebsabi.controller;

import et.com.gebeya.safaricom.sebsabi.dto.FormDto;
import et.com.gebeya.safaricom.sebsabi.dto.FormQuestionDto;
import et.com.gebeya.safaricom.sebsabi.dto.UserResponseDto;
import et.com.gebeya.safaricom.sebsabi.model.Form;
import et.com.gebeya.safaricom.sebsabi.model.UserResponse;
import et.com.gebeya.safaricom.sebsabi.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/core/forms")
public class FormController {

    private final FormService formService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Form createForm(@RequestBody FormDto formDTO) throws InvocationTargetException, IllegalAccessException {
        Form createdForm = formService.createForm(formDTO);
        return createdForm;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Form> getAllForms() {
        List<Form> forms = formService.getAllForms();
        return forms;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Form getFormById(@PathVariable Long id) {
        Form form = formService.getFormById(id);
        return form;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Form updateForm(@PathVariable Long id, @RequestBody FormDto formDTO) throws InvocationTargetException, IllegalAccessException {
        Form updatedForm = formService.updateForm(id, formDTO);
        return  updatedForm;
    }

    @PostMapping("/{formId}/questions")
    public Form addQuestionToForm(@PathVariable Long formId, @RequestBody FormQuestionDto questionDTO) throws InvocationTargetException, IllegalAccessException {
        Form updatedForm = formService.addQuestionToForm(formId, questionDTO);
        return  updatedForm;
    }
    @PostMapping("/submit-response")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse submitResponse(@RequestBody UserResponseDto responseDTO) {
        UserResponse userResponse = formService.submitResponse(responseDTO);
        return  userResponse;
    }
}
