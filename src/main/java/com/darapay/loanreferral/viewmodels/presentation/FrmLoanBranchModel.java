package com.darapay.loanreferral.viewmodels.presentation;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.viewmodels.TranStatusModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FrmLoanBranchModel {

    public FrmLoanBranchModel() {}

    public FrmLoanBranchModel(String loanst, Double appram, TranStatusModel tranStatusModels) {
        this.loanst = loanst;
        this.appram = appram;
        this.tranStatusModels = tranStatusModels;
    }

    @ModelBinding()
    private String loanst;

    @ModelBinding()
    private Double appram;

    private TranStatusModel tranStatusModels;
}
