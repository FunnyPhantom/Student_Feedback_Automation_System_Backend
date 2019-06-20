package ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer;

import ir.ac.sbu.ie.studentfeedback.Dao.EmployeeDao;
import ir.ac.sbu.ie.studentfeedback.Entities.Employee;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.EmployeeInput;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.FailReason;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.InputValidationResponse;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.RegisterUserResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.Optional;

@Named
public class EmployeeLogicBean {
    private final InputValidationLogic inputValidationLogic;
    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeLogicBean(InputValidationLogic inputValidationLogic, EmployeeDao employeeDao) {
        this.inputValidationLogic = inputValidationLogic;
        this.employeeDao = employeeDao;
    }

    public RegisterUserResponse registerEmployee(EmployeeInput employeeInput) {
        InputValidationResponse validationResponse = inputValidationLogic.validateEmployeeInput(employeeInput);
        if (validationResponse.hasSucceded()) {
            try {
                Employee savedEmployee = employeeDao.save(Employee.buildFromEmployeeInput(employeeInput));
                System.out.println("successfully employee saved");
                System.out.println(savedEmployee);
                return RegisterUserResponse.SUCCESS;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return RegisterUserResponse.buildFailedResponse(FailReason.SERVER_ERROR);
            }
        } else {
            return RegisterUserResponse.buildFailedResponse(validationResponse.getFailReason());
        }
    }

    public Employee getEmployee(Long id) {
        return employeeDao.findById(id).orElse(null);
    }


}
