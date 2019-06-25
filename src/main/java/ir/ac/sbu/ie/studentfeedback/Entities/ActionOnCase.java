package ir.ac.sbu.ie.studentfeedback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.ac.sbu.ie.studentfeedback.Entities.util.ActionType;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.UserSchema.EmployeeInputOutputSchema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
}
