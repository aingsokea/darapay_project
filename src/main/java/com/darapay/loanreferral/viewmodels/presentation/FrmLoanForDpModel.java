package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FrmLoanForDpModel {

    public FrmLoanForDpModel() {}

    public FrmLoanForDpModel(List<StatusNavDisplayModel> statusNavDisplayModel, List<FrmLoanTableDpDisplayModel> loanTableDisplayModels, Integer counts) {
        this.statusNavDisplayModel = statusNavDisplayModel;
        this.loanTableDisplayModels = loanTableDisplayModels;
        this.counts = counts;
    }

    private List<StatusNavDisplayModel> statusNavDisplayModel;
    private List<FrmLoanTableDpDisplayModel> loanTableDisplayModels;
    private Integer counts;
}
