package ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer;

import ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer.util.AuthorizationTokenGenerator;
import ir.ac.sbu.ie.studentfeedback.Dao.EmployeeDao;
import ir.ac.sbu.ie.studentfeedback.Entities.Employee;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.EmployeeRegisterInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.UserLoginInput;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.FailReason;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.BooleanProcedureResponse;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.InputValidationResponse;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.LoginProcedureResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.Optional;

@Named
public class EmployeeLogicBean {
    private final InputValidationLogic inputValidationLogic;
    private final EmployeeDao employeeDao;
    private final AuthorizationTokenGenerator tokenGenerator;

    @Autowired
    public EmployeeLogicBean(InputValidationLogic inputValidationLogic, EmployeeDao employeeDao, AuthorizationTokenGenerator generator) {
        this.inputValidationLogic = inputValidationLogic;
        this.employeeDao = employeeDao;
        this.tokenGenerator = generator;
    }

    public BooleanProcedureResponse registerEmployee(EmployeeRegisterInput employeeInput) {
        InputValidationResponse validationResponse = inputValidationLogic.validateEmployeeRegisterInput(employeeInput);
        if (validationResponse.hasSucceeded()) {
            try {
                // todo: store hashed version of password. (also check password hashed way)
                Employee savedEmployee = employeeDao.save(Employee.buildFromEmployeeInput(employeeInput));
                System.out.println("successfully employee saved");
                System.out.println(savedEmployee);
                return BooleanProcedureResponse.SUCCESS;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return BooleanProcedureResponse.buildFailedResponse(FailReason.SERVER_ERROR);
            }
        } else {
            return BooleanProcedureResponse.buildFailedResponse(validationResponse.getFailReason());
        }
    }

    public LoginProcedureResponse loginEmployee(UserLoginInput loginInput) {
        InputValidationResponse validationResponse = inputValidationLogic.validateUserLoginInput(loginInput);
        if (validationResponse.hasFailed()) {
            return LoginProcedureResponse.buildFailedResponse(validationResponse.getFailReason());
        } else {
            Optional<Employee> employeeContainer = employeeDao.findByUsername(loginInput.getUsername());
            if (employeeContainer.isEmpty()) {
                return LoginProcedureResponse.buildFailedResponse(FailReason.USERNAME_NOT_FOUND);
            } else {
                //username equal so password check.
                // todo: check hashed version of password.
                // passwords not equal:
                Employee e = employeeContainer.get();
                if (!e.getPassword().equals(loginInput.getPassword())) {
                    return LoginProcedureResponse.buildFailedResponse(FailReason.PASSWORD_NOT_MATCH);
                } else {
                    String token = tokenGenerator.generateAuthorizationToken(e);
                    e.setAuthorizationToken(token);
                    employeeDao.save(e);
                    return LoginProcedureResponse.buildSuccessResponse(token);
                }
            }
        }
    }


}


