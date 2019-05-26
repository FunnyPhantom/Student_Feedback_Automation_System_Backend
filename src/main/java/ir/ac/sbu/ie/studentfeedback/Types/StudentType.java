package ir.ac.sbu.ie.studentfeedback.Types;

import ir.ac.sbu.ie.studentfeedback.Entities.Case;
import ir.ac.sbu.ie.studentfeedback.Entities.User;
import ir.ac.sbu.ie.studentfeedback.Entities.Util.UserType;
import lombok.Data;

import java.util.List;

@Data
public class StudentType {
    private Long id;
    private String username;
    private String sid;
    private List<Case> issuedCases;

    private StudentType(User u) {
        this.id = u.getId();
        this.username = u.getUsername();
        this.sid = u.getSid();
        this.issuedCases = u.getIssuedCases();
    }

    public static StudentType buildStudentType(User u) {
        return new StudentType(u);
    }

}
