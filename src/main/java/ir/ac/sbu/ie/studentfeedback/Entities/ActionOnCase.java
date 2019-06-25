package ir.ac.sbu.ie.studentfeedback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.ac.sbu.ie.studentfeedback.Entities.util.ActionType;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.ActionSchema.ActionInput;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.UserSchema.EmployeeInputOutputSchema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
public class ActionOnCase {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String description;

    @OneToOne
    @Setter
    private Employee employee;

    public EmployeeInputOutputSchema getEmployee() {
        return new EmployeeInputOutputSchema(this.employee);
    }

    @Setter
    @Getter
    private ActionType actionType;

    @OneToOne
    @Setter
    @Nullable
    private Employee referredEmployee;

    @ManyToOne
    @Setter
    private Case targetCase;

    public Case getTargetCase() {
        return this.targetCase.copyWithoutActions();
    }

    public EmployeeInputOutputSchema getReferredEmployee() {
        return new EmployeeInputOutputSchema(this.referredEmployee);
    }

    public ActionOnCase() {
    }

    public ActionOnCase(ActionInput actionInput, Employee responsibleEmployee, Employee referredEmployee, Case targetCase) {
        this.actionType = actionInput.getActionType();
        this.description = actionInput.getDescription();
        this.employee = responsibleEmployee;
        this.referredEmployee = referredEmployee;
        this.targetCase = targetCase;
    }
}
