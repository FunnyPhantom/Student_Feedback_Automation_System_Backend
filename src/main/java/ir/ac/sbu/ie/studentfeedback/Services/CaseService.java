package ir.ac.sbu.ie.studentfeedback.Services;

import ir.ac.sbu.ie.studentfeedback.Entities.Case;
import ir.ac.sbu.ie.studentfeedback.Entities.User;
import ir.ac.sbu.ie.studentfeedback.Entities.Util.UserType;
import ir.ac.sbu.ie.studentfeedback.Repositories.CaseRepository;
import ir.ac.sbu.ie.studentfeedback.Services.util.CaseServiceStatus;
import ir.ac.sbu.ie.studentfeedback.Services.util.ServiceStatus;
import ir.ac.sbu.ie.studentfeedback.Types.CaseInputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ir.ac.sbu.ie.studentfeedback.Services.util.ServiceResponseMapKeys.*;

@Service
public class CaseService {

    private final CaseRepository caseRepository;
    private final AuthService authService;
    @Autowired
    public CaseService(CaseRepository caseRepository, AuthService authService) {
        this.caseRepository = caseRepository;
        this.authService = authService;
    }

    public Map<String, Object> createCase(String userToken, CaseInputType caseEntry) {
        Map<String, Object> ans = new HashMap<>();
        Map<String, Object> authRes = authService.getUser(userToken);
        if (authRes.get(KEY_SERVICE_STATUS) == ServiceStatus.FAILED) {
            ans.put(KEY_SERVICE_STATUS, ServiceStatus.FAILED);
            ans.put(KEY_CASE_SERVICE_STATUS, authRes.get(KEY_AUTH_STATUS));
        } else {
            User u = ((User) authRes.get(KEY_SAVED_USER));
            if (u.getUserType() != UserType.STUDENT) {
                ans.put(KEY_SERVICE_STATUS, ServiceStatus.FAILED);
                ans.put(KEY_CASE_SERVICE_STATUS, CaseServiceStatus.USER_NOT_PERMITTED);
            } else {
                Case aCase = new Case();
                aCase.setTitle(caseEntry.getTitle());
                aCase.setDescription(caseEntry.getDescription());
                aCase.setCaseCategory(caseEntry.getCaseCategory());
                aCase.setIssuingUser(u);
                caseRepository.save(aCase);
                ans.put(KEY_SERVICE_STATUS, ServiceStatus.SUCCESS);
                ans.put(KEY_SAVED_CASE, aCase);
            }
        }
        return ans;
    }

    public Map<String, Object> getUserCasesByToken(String userToken) {
        Map<String, Object> ans = new HashMap<>();
        Map<String, Object> serviceAns = this.authService.getUser(userToken);
        if(serviceAns.get(KEY_SERVICE_STATUS) == ServiceStatus.FAILED) {
            ans.put(KEY_SERVICE_STATUS, ServiceStatus.FAILED);
            ans.put(KEY_CASE_SERVICE_STATUS, serviceAns.get(KEY_AUTH_STATUS));
        } else {
            ans.put(KEY_SERVICE_STATUS, ServiceStatus.SUCCESS);
            ans.put(KEY_PAYLOAD_MY_CASES, ((User) serviceAns.get(KEY_SAVED_USER)).getIssuedCases());
        }
        return ans;
    }

}
