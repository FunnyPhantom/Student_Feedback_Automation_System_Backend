package ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse;

import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.UserSchema.StudentInputOutputSchema;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.FailReason;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse.BooleanProcedureResponse;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public final class StudentListProcedureResponse extends BooleanProcedureResponse {
    @Getter
    @Setter
    private List<StudentInputOutputSchema> studentList;

    public StudentListProcedureResponse(ProcedureStatus p, FailReason f, List<StudentInputOutputSchema> studentList) {
        super(p, f);
        this.studentList = studentList;
    }

    private static StudentListProcedureResponse buildReponse(ProcedureStatus p, FailReason f, List<StudentInputOutputSchema> studentList) {
        return new StudentListProcedureResponse(p, f, studentList);
    }

    public static StudentListProcedureResponse buildFailedResponse(FailReason f) {
        return buildReponse(ProcedureStatus.FAILED, f, null);
    }

    public static StudentListProcedureResponse buildSuccessResponse(List<StudentInputOutputSchema> studentList) {
        return buildReponse(ProcedureStatus.SUCCESS, null, studentList);
    }
}