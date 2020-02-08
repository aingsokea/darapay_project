package com.darapay.loanreferral.services;

import com.darapay.loanreferral.models.FrmLoan;
import com.darapay.loanreferral.viewmodels.AdvanceModel;
import com.darapay.loanreferral.viewmodels.FrmLoanAssignModel;
import com.darapay.loanreferral.viewmodels.FrmLoanModel;
import com.darapay.loanreferral.viewmodels.TranStatusModel;
import com.darapay.loanreferral.viewmodels.core.FrmLoanAgentModel;
import com.darapay.loanreferral.viewmodels.export.FrmLoanExportModel;
import com.darapay.loanreferral.viewmodels.presentation.*;

public interface FrmLoanService extends BaseService<FrmLoan, String, FrmLoanModel, FrmLoanExportModel> {
    FrmLoanOnloadModel getOnLoad();
    FrmLoanAgentByIdDisplayModel getFrmLoanAgentById(String id);
    FrmLoanDisplayModel getFrmLoanForAgent(AdvanceModel advanceModel);
    FrmLoanDisplayModel getFrmLoanForMfi(AdvanceModel advanceModel);
    FrmLoanAssignOnloadModel getAssignOnload(String id);
    FrmLoanAssignOnloadModel getAssignOnloadFinalUser(String id);
    //FrmLoanAssignModel assigntoMfi(String id, FrmLoanAssignModel fam);
    boolean assigntoMfi(String id, FrmLoanAssignModel fam);
    FrmLoanOnloadMfiAssignModel getOnloadMfiAssign(String id);
    //FrmLoanInprogressModel assigntoBranch(String id, FrmLoanInprogressModel fim);
    boolean assigntoBranch(String id, FrmLoanInprogressModel fim);
    FrmLoanOnloadBranchAssignModel getOnloadBranchAssign(String id);
    FrmLoanBranchModel assignFromBranch(String id, FrmLoanBranchModel flbm);
    FrmLoanAgentModel updateFrmLoanByAgent(String id, FrmLoanAgentModel frmLoanAgentModel);
    Boolean deleteFrmLoan(String id);
    FrmLoanDetailDisplayModel getFrmLoanDetail(String id);
    Boolean rejectFrmLoan(String id, TranStatusModel tsm);
    Integer countAllByLoanstAndEnable(String loanst, Boolean enable);
    FrmLoanForDpModel getFrmloanForDp(AdvanceModel advanceModel);
    Integer countAllByEnable(Boolean enable);
}
