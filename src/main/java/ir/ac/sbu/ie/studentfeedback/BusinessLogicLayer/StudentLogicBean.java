package ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer;

import ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer.util.AuthorizationTokenGenerator;
import ir.ac.sbu.ie.studentfeedback.Dao.StudentDao;
import ir.ac.sbu.ie.studentfeedback.Entities.Student;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.StudentRegisterInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.UserLoginInput;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.FailReason;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.BooleanProcedureResponse;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.InputValidationResponse;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.LoginProcedureResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.Optional;

@Named
public class StudentLogicBean {

    private final StudentDao studentDao;
    private final InputValidationLogic validationLogic;
    private final AuthorizationTokenGenerator tokenGenerator;

    @Autowired
    public StudentLogicBean(StudentDao studentDao, InputValidationLogic inputValidationLogic, AuthorizationTokenGenerator authorizationTokenGenerator) {
        this.studentDao = studentDao;
        this.validationLogic = inputValidationLogic;
        this.tokenGenerator = authorizationTokenGenerator;
    }

    public BooleanProcedureResponse registerStudent(StudentRegisterInput registerInput) {
        InputValidationResponse validationResponse = this.validationLogic.validateStudentRegisterInput(registerInput);
        if (validationResponse.hasFailed()) {
            return BooleanProcedureResponse.buildFailedResponse(validationResponse.getFailReason());
        } else {
            this.studentDao.save(Student.buildFromStudentInput(registerInput));
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

            String authToken = this.tokenGenerator.generateAuthorizationToken(s);
            s.setAuthorizationToken(authToken);
            studentDao.save(s);
            return LoginProcedureResponse.buildSuccessResponse(authToken);


        }
    }

}
