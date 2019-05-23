package ir.ac.sbu.ie.studentfeedback.Entities;

public enum UserType {
    STUDENT(1),
    EMPLOYEE(2),
    PROFESSOR(3),
    ADMIN(4);

    private int userTypeNum;
    UserType(int userTypeNum) {
        this.userTypeNum = userTypeNum;
    }

    public int getUserTypeNum() {
        return userTypeNum;
    }
}
