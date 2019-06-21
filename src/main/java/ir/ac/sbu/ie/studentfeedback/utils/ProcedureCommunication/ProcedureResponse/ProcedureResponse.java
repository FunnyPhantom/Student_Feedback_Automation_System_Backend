package ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse;

import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.FailReason;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureStatus;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.Nullable;

@ToString
public abstract class ProcedureResponse {
    @Getter
    protected ProcedureStatus procedureStatus;
    @Getter
    protected FailReason failReason;

    ProcedureResponse(ProcedureStatus procedureStatus, @Nullable FailReason failReason) {
        this.procedureStatus = procedureStatus;
        this.failReason = failReason;
    }

    public boolean hasFailed() {
        return this.procedureStatus == ProcedureStatus.FAILED;
    }

    public boolean hasSucceeded() {
        return this.procedureStatus == ProcedureStatus.SUCCESS;
    }
}
