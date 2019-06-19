package ir.ac.sbu.ie.studentfeedback.Controllers;

import ir.ac.sbu.ie.studentfeedback.Controllers.util.FailReasons;
import ir.ac.sbu.ie.studentfeedback.Controllers.util.InputOutputObjectTypes.EmployeeRegisterEntry;
import ir.ac.sbu.ie.studentfeedback.Controllers.util.InputOutputObjectTypes.ProcedureStatuses;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.inject.Named;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static ir.ac.sbu.ie.studentfeedback.Controllers.util.WebServiceKeys.*;

@Named
@Path("/employee")
public class EmployeeController {

    @CrossOrigin
    @POST
    @Path("/register")
    public Response registerEmployee(EmployeeRegisterEntry employeeRegisterEntry) {
        System.out.println(employeeRegisterEntry);
        return Response.ok().build();
    }

    private Map<String, Object> validateInputs(EmployeeRegisterEntry employeeRegisterEntry) {
        Map<String, Object> ans = new HashMap<>();
        if (Strings.isBlank(employeeRegisterEntry.getFirstName()) ||
                Strings.isBlank(employeeRegisterEntry.getLastName()) ||
                Strings.isBlank(employeeRegisterEntry.getUsername()) ||
                Strings.isBlank(employeeRegisterEntry.getJobTitle()) ||
                Strings.isBlank(employeeRegisterEntry.getLastName()) ||
                Strings.isBlank(employeeRegisterEntry.getPassword())
        ) {
            ans.put(KEY_FAIL_REASON, FailReasons.BLANK_ENTRY);
            ans.put(KEY_INPUT_VALIDATION, ProcedureStatuses.FAILED);
            return ans;
        }
        // todo: if username exist
//        else if () {
//
//        }
        else {
            ans.put(KEY_INPUT_VALIDATION, ProcedureStatuses.SUCCESS);
            return ans;
        }
    }


}
