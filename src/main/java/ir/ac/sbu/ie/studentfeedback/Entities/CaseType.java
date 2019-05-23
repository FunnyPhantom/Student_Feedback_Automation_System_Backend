package ir.ac.sbu.ie.studentfeedback.Entities;

public enum CaseType {
    COMPLAINT(1),
    CRITICISM(2),
    SUGGESTION(3),
    REQUEST(4);

    private int caseTypeNum;
    CaseType(int caseTypeNum) {
        this.caseTypeNum = caseTypeNum;
    }

    public int getCaseTypeNum() {
        return caseTypeNum;
    }
}
