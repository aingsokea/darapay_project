package com.darapay.loanreferral.viewmodels.presentation;

import com.darapay.loanreferral.viewmodels.FrmLoanModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FrmLoanDisplayModel {

    public FrmLoanDisplayModel() {}

    public FrmLoanDisplayModel(List<StatusNavDisplayModel> statusNavDisplayModel,List<FrmLoanModel> frmLoanModels, Integer counts) {
        this.statusNavDisplayModel = statusNavDisplayModel;
        this.frmLoanModels = frmLoanModels;
        this.counts = counts;
    }
    private List<StatusNavDisplayModel> statusNavDisplayModel;
    private List<FrmLoanModel> frmLoanModels;
    private Integer counts;
}
