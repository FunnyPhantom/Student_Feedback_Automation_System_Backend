package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.IdSchema;

import ir.ac.sbu.ie.studentfeedback.Entities.util.UserRoles;
import lombok.Data;

@Data
public abstract class UserIdSchema {
    private Long id;

    public abstract UserRoles getUserRole();

    public UserIdSchema() {
    }

}
