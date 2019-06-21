package ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer;

import ir.ac.sbu.ie.studentfeedback.Dao.AdminDao;
import ir.ac.sbu.ie.studentfeedback.Dao.EmployeeDao;
import ir.ac.sbu.ie.studentfeedback.Dao.StudentDao;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.AdminRegisterInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.EmployeeRegisterInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.StudentRegisterInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.UserLoginInput;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.InputValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@Named
public class InputValidationLogic {

    private final EmployeeDao employeeDao;
    private final StudentDao studentDao;
    private final AdminDao adminDao;

    @Autowired
    public InputValidationLogic(EmployeeDao employeeDao, StudentDao studentDao, AdminDao adminDao) {
        this.employeeDao = employeeDao;
        this.studentDao = studentDao;
        this.adminDao = adminDao;
    }

    public InputValidationResponse validateEmployeeRegisterInput(EmployeeRegisterInput employeeInput) {
        if (EmployeeRegisterInput.isBlankEntry(employeeInput)) {
            return InputValidationResponse.FAILED__BLANK_ENTRY;
        }
        if (employeeDao.findByUsername(employeeInput.getUsername()).isPresent()) {
            return InputValidationResponse.FAILED__USERNAME_EXISTS;
        }
        return InputValidationResponse.SUCCESS;
    }

    public InputValidationResponse validateStudentRegisterInput(StudentRegisterInput studentInput) {
        if (StudentRegisterInput.isBlankEntry(studentInput)) {
            return InputValidationResponse.FAILED__BLANK_ENTRY;
        }
        if (studentDao.findByUsername(studentInput.getUsername()).isPresent()) {
            return InputValidationResponse.FAILED__USERNAME_EXISTS;
        }
        if (studentDao.findByStudentId(studentInput.getStudentId()).isPresent()) {
            return InputValidationResponse.FAILED__STUDENT_ID_EXISTS;
        }
        return InputValidationResponse.SUCCESS;
    }

    public InputValidationResponse validateAdminRegisterInput(AdminRegisterInput adminInput) {
        if (AdminRegisterInput.isBlankEntry(adminInput)) {
            return InputValidationResponse.FAILED__BLANK_ENTRY;
        }
        if (adminDao.findByUsername(adminInput.getUsername()).isPresent()) {
            return InputValidationResponse.FAILED__USERNAME_EXISTS;
        }
        return InputValidationResponse.SUCCESS;
    }

    public InputValidationResponse validateUserLoginInput(UserLoginInput input) {
        if (UserLoginInput.isBlankEntry(input)) {
            return InputValidationResponse.FAILED__BLANK_ENTRY;
        }

        return InputValidationResponse.SUCCESS;
    }


}
