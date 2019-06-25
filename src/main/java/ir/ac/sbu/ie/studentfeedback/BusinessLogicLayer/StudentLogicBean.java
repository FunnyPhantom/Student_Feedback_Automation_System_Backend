package ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer;

import ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer.util.AuthorizationTokenGenerator;
import ir.ac.sbu.ie.studentfeedback.Dao.CaseDao;
import ir.ac.sbu.ie.studentfeedback.Dao.EmployeeDao;
import ir.ac.sbu.ie.studentfeedback.Dao.StudentDao;
import ir.ac.sbu.ie.studentfeedback.Entities.Case;
import ir.ac.sbu.ie.studentfeedback.Entities.Employee;
import ir.ac.sbu.ie.studentfeedback.Entities.Student;
import ir.ac.sbu.ie.studentfeedback.Entities.util.CaseStatus;
import ir.ac.sbu.ie.studentfeedback.Entities.util.Satisfaction;
import ir.ac.sbu.ie.studentfeedback.Entities.util.UserValidationStatus;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.CaseInputOutputSchema.CaseInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.StudentRegisterInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.UserLoginInput;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.FailReason;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.BooleanProcedureResponse;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.InputValidationResponse;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.LoginProcedureResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import javax.naming.AuthenticationException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@Named
public class StudentLogicBean {

    private final StudentDao studentDao;
    private final InputValidationLogic validationLogic;
    private final AuthorizationTokenGenerator tokenGenerator;
    private final EmployeeDao employeeDao;
    private final CaseDao caseDao;

    @Autowired
    public StudentLogicBean(StudentDao studentDao,
                            InputValidationLogic inputValidationLogic,
                            AuthorizationTokenGenerator authorizationTokenGenerator,
                            EmployeeDao employeeDao,
                            CaseDao caseDao) {
        this.studentDao = studentDao;
        this.validationLogic = inputValidationLogic;
        this.tokenGenerator = authorizationTokenGenerator;
        this.employeeDao = employeeDao;
        this.caseDao = caseDao;
    }

    public BooleanProcedureResponse registerStudent(StudentRegisterInput registerInput) {
        InputValidationResponse validationResponse = this.validationLogic.validateStudentRegisterInput(registerInput);
        if (validationResponse.hasFailed()) {
            return BooleanProcedureResponse.buildFailedResponse(validationResponse.getFailReason());
        } else {
            Student s = Student.buildFromStudentInput(registerInput);
            this.studentDao.save(s);
            return BooleanProcedureResponse.SUCCESS;
        }
    }

    public LoginProcedureResponse loginStudent(UserLoginInput loginInput) {
        InputValidationResponse validationResponse = this.validationLogic.validateUserLoginInput(loginInput);
        if (validationResponse.hasFailed()) {
            return LoginProcedureResponse.buildFailedResponse(validationResponse.getFailReason());
        } else {
            Optional<Student> studentContainer = studentDao.findByUsername(loginInput.getUsername());
            if (studentContainer.isEmpty()) {
                return LoginProcedureResponse.buildFailedResponse(FailReason.USERNAME_NOT_FOUND);
            }
            Student s = studentContainer.get();
            if (!s.getPassword().equals(loginInput.getPassword())) {
                return LoginProcedureResponse.buildFailedResponse(FailReason.PASSWORD_NOT_MATCH);
            }
            if (s.getValidationStatus() == UserValidationStatus.PENDING) {
                return LoginProcedureResponse.buildFailedResponse(FailReason.USER_NOT_YET_VALIDATED);
            }
            String authToken = this.tokenGenerator.generateAuthorizationToken(s);
            s.setAuthorizationToken(authToken);
            studentDao.save(s);
            return LoginProcedureResponse.buildSuccessResponse(authToken);


        }
    }

    public Student getStudentByAuthToken(String token) throws AuthenticationException {
        Optional<Student> s = studentDao.findByAuthorizationToken(token);
        if (s.isEmpty()) {
            throw new AuthenticationException("Token not found");
        }
        return s.get();

    }

    public Case createNewCase(String authToken, CaseInput caseInput) throws AuthenticationException, NotFoundException {
        Student s = this.getStudentByAuthToken(authToken);
        Employee e = employeeDao.findById(caseInput.getSelectedEmployeeId()).orElse(null);
        if (e == null) {
            throw new NotFoundException();
        }
        Case aCase = new Case(caseInput.getTitle(), caseInput.getDescription(), caseInput.getCaseType(), CaseStatus.OPEN, s, e);
        return this.caseDao.save(aCase);

    }

    public List<Case> getStudentCasesByAuthToken(String token) throws AuthenticationException {
        Student s = this.getStudentByAuthToken(token);
        return s.getIssuedCases();
    }

    public Case getStudentCaseByAuthTokenAndId(String authToken, Long id) throws AuthenticationException, NotFoundException {
        Student s = this.getStudentByAuthToken(authToken);
        Case c = caseDao.findById(id).orElse(null);
        if (c == null) {
            throw new NotFoundException("case NotFound.");
        }
        return c;
    }

    public void submitSatisfactionForCase(String authToken, Long caseId, Satisfaction s) throws AuthenticationException, NotFoundException, ForbiddenException {
        Case c = getStudentCaseByAuthTokenAndId(authToken, caseId);
        if (c.getCaseStatus() == CaseStatus.OPEN) {
            throw new ForbiddenException("cannot rate ongoing case, must wait till case status not to be open");
        }
        c.setStudentSatisfaction(s);
        caseDao.save(c);
    }

}
