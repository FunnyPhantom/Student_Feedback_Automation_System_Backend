package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AdminRegisterInput extends UserRegisterInput {

    public static boolean isBlankEntry(AdminRegisterInput adminInput) {
        return UserRegisterInput.isBlankEntry(adminInput);
    }
}
