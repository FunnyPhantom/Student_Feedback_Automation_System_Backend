package ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer;

import ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer.util.AuthorizationTokenGenerator;
import ir.ac.sbu.ie.studentfeedback.Dao.AdminDao;
import ir.ac.sbu.ie.studentfeedback.Dao.EmployeeDao;
import ir.ac.sbu.ie.studentfeedback.Dao.StudentDao;
import ir.ac.sbu.ie.studentfeedback.Entities.Admin;
import ir.ac.sbu.ie.studentfeedback.Entities.Employee;
import ir.ac.sbu.ie.studentfeedback.Entities.Student;
import ir.ac.sbu.ie.studentfeedback.Entities.User;
import ir.ac.sbu.ie.studentfeedback.Entities.util.UserValidationStatus;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.IdSchema.UserIdSchema;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.AdminRegisterInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.UserLoginInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.UserSchema.EmployeeInputOutputSchema;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.UserSchema.StudentInputOutputSchema;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.FailReason;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import javax.inject.Named;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Named
public class AdminLogicBean {
    private final AdminDao adminDao;
    private final StudentDao studentDao;
    private final EmployeeDao employeeDao;
    private final InputValidationLogic validationLogic;
    private final AuthorizationTokenGenerator tokenGenerator;
    private final int PAGE_SIZE = 15;

    @Autowired
    public AdminLogicBean(AdminDao adminDao, StudentDao studentDao, EmployeeDao employeeDao, InputValidationLogic validationLogic, AuthorizationTokenGenerator tokenGenerator) {
        this.adminDao = adminDao;
        this.studentDao = studentDao;
        this.employeeDao = employeeDao;
        this.validationLogic = validationLogic;
        this.tokenGenerator = tokenGenerator;
    }

    public BooleanProcedureResponse registerAdmin(AdminRegisterInput registerInput) {
        InputValidationResponse validationResponse = validationLogic.validateAdminRegisterInput(registerInput);
        if (validationResponse.hasFailed()) {
            return BooleanProcedureResponse.buildFailedResponse(validationResponse.getFailReason());
        }
        adminDao.save(Admin.buildAdminFromRegisterInput(registerInput));
        return BooleanProcedureResponse.SUCCESS;
    }

    public LoginProcedureResponse loginAdmin(UserLoginInput loginInput) {
        InputValidationResponse validationResponse = validationLogic.validateUserLoginInput(loginInput);
        if (validationResponse.hasFailed()) {
            return LoginProcedureResponse.buildFailedResponse(validationResponse.getFailReason());
        }
        Optional<Admin> adminContainer = adminDao.findByUsername(loginInput.getUsername());
        if (adminContainer.isEmpty()) {
            return LoginProcedureResponse.buildFailedResponse(FailReason.USERNAME_NOT_FOUND);
        }
        Admin admin = adminContainer.get();
        if (!admin.getPassword().equals(loginInput.getPassword())) {
            return LoginProcedureResponse.buildFailedResponse(FailReason.PASSWORD_NOT_MATCH);
        }
        String authToken = tokenGenerator.generateAuthorizationToken(admin);
        admin.setAuthorizationToken(authToken);
        adminDao.save(admin);
        return LoginProcedureResponse.buildSuccessResponse(authToken);
    }

    public BooleanProcedureResponse validateUser(String requesterAuthToken, UserIdSchema idSchema) {
        Optional<Admin> adminContainer = adminDao.findByAuthorizationToken(requesterAuthToken);
        if (adminContainer.isEmpty()) {
            return BooleanProcedureResponse.buildFailedResponse(FailReason.AUTH_TOKEN_INVALID);
        }
        // we dont need the below line rn.
//        Admin admin = adminContainer.get();
        Optional<User> userConainer = Optional.empty();
        switch (idSchema.getUserRole()) {
            case EMPLOYEE:
                userConainer = employeeDao.findById(idSchema.getId()).map(employee -> employee);
                break;
            case STUDENT:
                userConainer = studentDao.findById(idSchema.getId()).map(student -> student);
                break;
        }
        if (userConainer.isEmpty()) {
            return BooleanProcedureResponse.buildFailedResponse(FailReason.USER_ID_NOT_FOUND);
        }
        User user = userConainer.get();
        user.setValidationStatus(UserValidationStatus.VALIDATED);
        switch (user.getUserRole()) {
            case ADMIN:
                break;
            case EMPLOYEE:
                employeeDao.save(((Employee) user));
            case STUDENT:
                studentDao.save(((Student) user));

        }
        return BooleanProcedureResponse.SUCCESS;
    }

    public EmployeeListProcedureResponse getListOfAllPendingEmployees(String requesterAuthToken) {
        Optional<Admin> adminContainer = adminDao.findByAuthorizationToken(requesterAuthToken);
        if (adminContainer.isEmpty()) {
            return EmployeeListProcedureResponse.buildFailedResponse(FailReason.AUTH_TOKEN_INVALID);
        }

        return EmployeeListProcedureResponse.buildSuccessResponse(
                StreamSupport.stream(employeeDao.findAll().spliterator(), false)
                        .filter(employee -> employee.getValidationStatus() == UserValidationStatus.PENDING)
                        .map(EmployeeInputOutputSchema::new)
                        .collect(Collectors.toList())
        );
    }

    public EmployeeListProcedureResponse getListOfEmployeesByPage(String requesterAuthToken, int page) {
        Optional<Admin> adminContainer = adminDao.findByAuthorizationToken(requesterAuthToken);
        if (adminContainer.isEmpty()) {
            return EmployeeListProcedureResponse.buildFailedResponse(FailReason.AUTH_TOKEN_INVALID);
        }
        return EmployeeListProcedureResponse.buildSuccessResponse(
                employeeDao.findAll(PageRequest.of(page, PAGE_SIZE))
                        .stream()
                        .map(EmployeeInputOutputSchema::new)
                        .collect(Collectors.toList())
        );
    }

    public StudentListProcedureResponse getListOfAllPendingStudent(String requesterAuthToken) {
        Optional<Admin> adminContainer = adminDao.findByAuthorizationToken(requesterAuthToken);
        if (adminContainer.isEmpty()) {
            return StudentListProcedureResponse.buildFailedResponse(FailReason.AUTH_TOKEN_INVALID);
        }
        return StudentListProcedureResponse.buildSuccessResponse(
                StreamSupport.stream(studentDao.findAll().spliterator(), false)
                        .filter(student -> student.getValidationStatus() == UserValidationStatus.PENDING)
                        .map(StudentInputOutputSchema::new)
                        .collect(Collectors.toList())
        );

    }

    public StudentListProcedureResponse getListOfStudentByPage(String requesterAuthToken, int page) {
        Optional<Admin> adminContainer = adminDao.findByAuthorizationToken(requesterAuthToken);
        if (adminContainer.isEmpty()) {
            return StudentListProcedureResponse.buildFailedResponse(FailReason.AUTH_TOKEN_INVALID);
        }
        return StudentListProcedureResponse.buildSuccessResponse(
                studentDao.findAll(PageRequest.of(page, PAGE_SIZE))
                        .stream()
                        .map(StudentInputOutputSchema::new)
                        .collect(Collectors.toList())
        );
    }

}
