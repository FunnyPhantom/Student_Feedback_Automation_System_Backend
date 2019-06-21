package ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse;

import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.UserSchema.EmployeeInputOutputSchema;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.FailReason;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public final class EmployeeListProcedureResponse extends BooleanProcedureResponse {
    @Setter
    @Getter
    private List<EmployeeInputOutputSchema> employeeList;

    private EmployeeListProcedureResponse(ProcedureStatus p, FailReason f, List<EmployeeInputOutputSchema> employeeList) {
        super(p, f);
        this.employeeList = employeeList;
    }

    private static EmployeeListProcedureResponse buildResponse(ProcedureStatus p, FailReason f, List<EmployeeInputOutputSchema> employeeList) {
        return new EmployeeListProcedureResponse(p, f, employeeList);
    }

    public static EmployeeListProcedureResponse buildFailedResponse(FailReason f) {
        return buildResponse(ProcedureStatus.FAILED, f, null);
    }

    public static EmployeeListProcedureResponse buildSuccessResponse(List<EmployeeInputOutputSchema> employeeList) {
        return buildResponse(ProcedureStatus.SUCCESS, null, employeeList);
    }

}
