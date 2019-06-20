package ir.ac.sbu.ie.studentfeedback.BusinessLogicLayer;

import ir.ac.sbu.ie.studentfeedback.Dao.AdminDao;
import ir.ac.sbu.ie.studentfeedback.Dao.EmployeeDao;
import ir.ac.sbu.ie.studentfeedback.Dao.StudentDao;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.AdminInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.EmployeeInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.StudentInput;
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

    public InputValidationResponse validateEmployeeInput(EmployeeInput employeeInput) {
        if (EmployeeInput.isBlankEntry(employeeInput)) {
            return InputValidationResponse.FAILED__BLANK_ENTRY;
        }
        if (employeeDao.findByUsername(employeeInput.getUsername()).isPresent()) {
            return InputValidationResponse.FAILED__USERNAME_EXISTS;
        }
        return InputValidationResponse.SUCCESS;
    }

    public InputValidationResponse validateStudentInput(StudentInput studentInput) {
        if (StudentInput.isBlankEntry(studentInput)) {
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

    public InputValidationResponse validateAdminInput(AdminInput adminInput) {
        if (AdminInput.isBlankEntry(adminInput)) {
            return InputValidationResponse.FAILED__BLANK_ENTRY;
        }
        if (adminDao.findByUsername(adminInput.getUsername()).isPresent()) {
            return InputValidationResponse.FAILED__USERNAME_EXISTS;
        }
        return InputValidationResponse.SUCCESS;
    }
}
