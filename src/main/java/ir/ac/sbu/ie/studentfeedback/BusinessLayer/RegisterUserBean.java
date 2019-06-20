package ir.ac.sbu.ie.studentfeedback.BusinessLayer;

import ir.ac.sbu.ie.studentfeedback.Dao.AdminDao;
import ir.ac.sbu.ie.studentfeedback.Dao.EmployeeDao;
import ir.ac.sbu.ie.studentfeedback.Dao.StudentDao;
import ir.ac.sbu.ie.studentfeedback.Entities.Employee;
import ir.ac.sbu.ie.studentfeedback.Entities.Student;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.EmployeeInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.StudentInput;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@Named
public class RegisterUserBean {
    private EmployeeDao employeeDao;
    private StudentDao studentDao;
    private AdminDao adminDao;
    private InputValidationLogic inputValidationLogic;

    @Autowired
    public RegisterUserBean(EmployeeDao employeeDao, StudentDao studentDao, AdminDao adminDao, InputValidationLogic inputValidationLogic) {
        this.employeeDao = employeeDao;
        this.studentDao = studentDao;
        this.adminDao = adminDao;
        this.inputValidationLogic = inputValidationLogic;
    }

    public Employee registerEmployee(EmployeeInput employeeInput) {
        Employee e = employeeDao.save(Employee.buildFromEmployeeInput(employeeInput));
        return e;
    }

    public Student registerStudent(StudentInput studentInput) {
        Student s = studentDao.save(Student.buildFromStudentInput(studentInput));
        return s;
    }

}
