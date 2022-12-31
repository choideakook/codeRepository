package survey.surveyspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import survey.surveyspring.domain.Servay;

import javax.swing.*;

@Controller
public class ServayController {

    @PostMapping ("/servay/question")
    public String questionForm (){
        return "/servay/questionForm";
    }

    @PostMapping ("/servay/result")
    public String resultForm (AnswerForm form){


        return  "/servay/resultForm";
    }

    @PostMapping ("/return")
    public String reStart (AnswerForm form){
        if (form.getNewPw() != form.getCheckPw()){
            JOptionPane.showMessageDialog(
                    null
                    ,"비밀번호가 일치하지 않습니다."
            );
            return "/servay/resultForm";
        }else {
            String id =form.getNewid();
            String name =form.getNewName();
            String pw =form.getNewPw();
            Servay member = new Servay(id,pw,name);

            return "redirect:/";
        }
    }

}
