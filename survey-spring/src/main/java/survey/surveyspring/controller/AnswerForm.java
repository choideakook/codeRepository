package survey.surveyspring.controller;

public class AnswerForm {

    private String newName;
    private String newid;
    private String newPw;
    private String checkPw;

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewid() {
        return newid;
    }

    public void setNewid(String newid) {
        this.newid = newid;
    }

    public String getNewPw() {
        return newPw;
    }

    public void setNewPw(String newPw) {
        this.newPw = newPw;
    }

    public String getCheckPw() {
        return checkPw;
    }

    public void setCheckPw(String checkPw) {
        this.checkPw = checkPw;
    }
}
