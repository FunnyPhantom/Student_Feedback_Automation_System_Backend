package ir.ac.sbu.ie.studentfeedback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.ac.sbu.ie.studentfeedback.Entities.util.CaseStatus;
import ir.ac.sbu.ie.studentfeedback.Entities.util.CaseType;
import ir.ac.sbu.ie.studentfeedback.Entities.util.Satisfaction;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.UserSchema.EmployeeInputOutputSchema;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.UserSchema.StudentInputOutputSchema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "sf_case")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String title;
    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private CaseType caseType;

    @Setter
    @Getter
    private CaseStatus caseStatus;

    @Setter
    @Getter
    private Date issueDate;


    @Setter
    @ManyToOne
    private Student issuingStudent;

    public StudentInputOutputSchema getIssuingStudent() {
        return new StudentInputOutputSchema(this.issuingStudent);
    }

    @Setter
    @ManyToOne
    private Employee responsibleEmployee;

    public EmployeeInputOutputSchema getResponsibleEmployee() {
        return new EmployeeInputOutputSchema(this.responsibleEmployee);
    }


    @OneToMany(mappedBy = "targetCase")
    @Setter
    @Getter
    private List<ActionOnCase> actions;

    @Setter
    @Getter
    private Satisfaction studentSatisfaction;


    public Case() {
        this.issueDate = new Date();
        this.studentSatisfaction = Satisfaction.UNKNOWN;
    }

    public Case(String title, String description, CaseType caseType, CaseStatus caseStatus, Student issuingStudent, Employee responsibleEmployee) {
        this();
        this.title = title;
        this.description = description;
        this.caseType = caseType;
        this.caseStatus = caseStatus;
        this.issuingStudent = issuingStudent;
        this.responsibleEmployee = responsibleEmployee;
    }

    public Case(Case anotherCase) {
        this(anotherCase.title, anotherCase.description, anotherCase.caseType, anotherCase.caseStatus, anotherCase.issuingStudent, anotherCase.responsibleEmployee);
        this.issueDate = anotherCase.issueDate;
        this.id = anotherCase.id;
        this.studentSatisfaction = anotherCase.studentSatisfaction;
        this.actions = anotherCase.actions;
    }

    public Case copyWithoutActions() {
        Case c = new Case(this);
        c.actions = null;
        return c;
    }

}
