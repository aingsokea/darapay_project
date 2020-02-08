package com.darapay.loanreferral.viewmodels.presentation;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.viewmodels.TranStatusModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FrmLoanInprogressModel {
    public FrmLoanInprogressModel() {}

    public FrmLoanInprogressModel(String loanst, String branchid, String accountid, TranStatusModel tranStatusModels) {
        this.loanst = loanst;
        this.branchid = branchid;
        this.accountid = accountid;
        this.tranStatusModels = tranStatusModels;
    }

    @ModelBinding()
    private String loanst;

    @ModelBinding()
    private String branchid;

    @ModelBinding()
    private String accountid;

    private TranStatusModel tranStatusModels;
}
