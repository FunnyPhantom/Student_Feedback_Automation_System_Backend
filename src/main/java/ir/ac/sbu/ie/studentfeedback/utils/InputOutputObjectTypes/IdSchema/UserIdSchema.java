package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.IdSchema;

import ir.ac.sbu.ie.studentfeedback.Entities.util.UserRole;
import lombok.Data;

@Data
public abstract class UserIdSchema {
    private Long id;

    public abstract UserRole getUserRole();

    public UserIdSchema() {
    }

}
