package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.IdSchema;

import ir.ac.sbu.ie.studentfeedback.Entities.util.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeIdSchema extends UserIdSchema {
    @Override
    public UserRole getUserRole() {
        return null;
    }
}
