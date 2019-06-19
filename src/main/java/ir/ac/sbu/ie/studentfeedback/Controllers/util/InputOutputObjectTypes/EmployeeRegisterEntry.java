package ir.ac.sbu.ie.studentfeedback.Controllers.util.InputOutputObjectTypes;

import lombok.Data;

@Data
public class EmployeeRegisterEntry {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String jobTitle;
}
