package in.co.techiesandeep.controller;

import in.co.techiesandeep.model.Quiz;
import in.co.techiesandeep.model.User;
import in.co.techiesandeep.service.QuizService;
import in.co.techiesandeep.util.AppUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppUtilities.ROOT_MAPPING)
public class QuizController {
    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    QuizService quizService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<Quiz> findAll(Pageable pageable,
                              @RequestParam(required = false, defaultValue = "false") Boolean published) {

        if (published) {
            return quizService.findAllPublished(pageable);
        } else {
            return quizService.findAll(pageable);
        }
    }

    @RequestMapping(value = "/create_quiz", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Quiz save(@RequestBody Quiz quiz){
      logger.debug("The Quiz " + quiz.getName() + " is going to be created");

      return quizService.save(quiz.getCreatedBy(), quiz);
    }
}
