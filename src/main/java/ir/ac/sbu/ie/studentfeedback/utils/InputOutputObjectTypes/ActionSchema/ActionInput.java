package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.ActionSchema;

import ir.ac.sbu.ie.studentfeedback.Entities.util.ActionType;
import lombok.Data;

@Data
public class ActionInput {
    private String description;
    private ActionType actionType;
    private Long referredEmployeeId;

    public static boolean isBlankEntry(ActionInput actionInput) {
        return actionInput == null || actionInput.getActionType() == null;
    }
}
