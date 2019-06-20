package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StudentInput extends UserInput {
    protected String studentId;
}
