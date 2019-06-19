package ir.ac.sbu.ie.studentfeedback.Types;

import ir.ac.sbu.ie.studentfeedback.Entities.Case;
import ir.ac.sbu.ie.studentfeedback.Entities.User;
import ir.ac.sbu.ie.studentfeedback.Entities.Util.UserType;
import lombok.Data;

import java.util.List;

@Data
public class UserOutputType {
    private Long id;
    private String username;
    private String sid;
    private UserType userType;

    private UserOutputType(User u) {
        this.id = u.getId();
        this.username = u.getUsername();
        this.sid = u.getSid();
        this.userType = u.getUserType();
    }

    public static UserOutputType buildUserOutputType(User u) { return new UserOutputType(u);}
}
