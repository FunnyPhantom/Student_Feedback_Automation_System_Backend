package ir.ac.sbu.ie.studentfeedback.BusinessLayer;

import ir.ac.sbu.ie.studentfeedback.utils.FailReasons;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.EmployeeInput;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureStatuses;
import org.apache.logging.log4j.util.Strings;

import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

import static ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunicationKeys.KEY_FAIL_REASON;
import static ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunicationKeys.KEY_INPUT_VALIDATION_STATUS;

@Named
public class InputValidationLogics {

    public Map<String, Object> validateInputs(EmployeeInput employeeInput) {
        Map<String, Object> ans = new HashMap<>();
        if (Strings.isBlank(employeeInput.getFirstName()) ||
                Strings.isBlank(employeeInput.getLastName()) ||
                Strings.isBlank(employeeInput.getUsername()) ||
                Strings.isBlank(employeeInput.getJobTitle()) ||
                Strings.isBlank(employeeInput.getLastName()) ||
                Strings.isBlank(employeeInput.getPassword())
        ) {
            ans.put(KEY_FAIL_REASON, FailReasons.BLANK_ENTRY);
            ans.put(KEY_INPUT_VALIDATION_STATUS, ProcedureStatuses.FAILED);
            return ans;
        }
        // todo: if username exist
//        else if () {
//
//        }
        else {
            ans.put(KEY_INPUT_VALIDATION_STATUS, ProcedureStatuses.SUCCESS);
            return ans;
        }
    }


}
