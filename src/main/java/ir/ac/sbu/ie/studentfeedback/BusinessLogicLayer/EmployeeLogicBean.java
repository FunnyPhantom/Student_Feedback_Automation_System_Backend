package ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer;

import ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer.util.AuthorizationTokenGenerator;
import ir.ac.sbu.ie.studentfeedback.Dao.EmployeeDao;
import ir.ac.sbu.ie.studentfeedback.Entities.Case;
import ir.ac.sbu.ie.studentfeedback.Entities.Employee;
import ir.ac.sbu.ie.studentfeedback.Entities.util.UserValidationStatus;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.EmployeeRegisterInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.UserLoginInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.UserSchema.EmployeeBriefSchema;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.FailReason;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.BooleanProcedureResponse;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.InputValidationResponse;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.LoginProcedureResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
                    if (e.getValidationStatus() == UserValidationStatus.PENDING) {
                        return LoginProcedureResponse.buildFailedResponse(FailReason.USER_NOT_YET_VALIDATED);
                    }
                    String token = tokenGenerator.generateAuthorizationToken(e);
                    e.setAuthorizationToken(token);
                    employeeDao.save(e);
                    return LoginProcedureResponse.buildSuccessResponse(token);
                }
            }
        }
    }

    public Employee getEmployeeWithAuthToken(String token) throws AuthenticationException {
        Optional<Employee> employeeOptional = employeeDao.findByAuthorizationToken(token);
        if (employeeOptional.isPresent()) {
            return employeeOptional.get();
        } else {
            throw new AuthenticationException("Token not found.");
        }
    }

    public List<Case> getEmployeeCasesWithAuthToken(String token) throws AuthenticationException {
        Employee e = this.getEmployeeWithAuthToken(token);
        return e.getResponsibleCases();
    }


    public List<EmployeeBriefSchema> getListOfEmployeesAndTheirJobTitles() {
        return StreamSupport
                .stream(employeeDao.findAll().spliterator(), false)
                .map(EmployeeBriefSchema::new)
                .collect(Collectors.toList())
                ;
    }


}


