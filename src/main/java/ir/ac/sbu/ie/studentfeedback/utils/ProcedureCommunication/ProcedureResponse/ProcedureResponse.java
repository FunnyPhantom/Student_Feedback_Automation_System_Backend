package ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureResponse;

import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.FailReason;
import ir.ac.sbu.ie.studentfeedback.utils.ProcedureCommunication.ProcedureStatus;
import lombok.ToString;
import org.springframework.lang.Nullable;

@ToString
public abstract class ProcedureResponse {
    protected ProcedureStatus procedureStatus;
    protected FailReason failReason;

    protected ProcedureResponse(ProcedureStatus procedureStatus, @Nullable FailReason failReason) {
        this.procedureStatus = procedureStatus;
        this.failReason = failReason;
    }

    public boolean hasFailed() {
        return this.procedureStatus == ProcedureStatus.FAILED;
    }

    public boolean hasSucceded() {
        return this.procedureStatus == ProcedureStatus.SUCCESS;
    }

    public FailReason getFailReason() {
        return this.failReason;
    }

    public ProcedureStatus getProcedureStatus() {
        return this.procedureStatus;
    }
}
