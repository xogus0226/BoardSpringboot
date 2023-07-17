package com.mysite.sbb.answer;
import com.mysite.sbb.question.Question;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import com.mysite.sbb.question.QuestionService;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RequestMapping("/answer")
@Controller
public class AnswerController {
    public final AnswerService answerService;
    public final QuestionService questionService;
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id,
                               @Valid AnswerForm answerForm, BindingResult bindingResult){
        Question q = questionService.getQuestion(id);
        if(bindingResult.hasErrors()) {
            model.addAttribute("question", q);
            return "question_detail";
        }
        answerService.create(q, answerForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }
}
