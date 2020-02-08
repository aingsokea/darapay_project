package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FrmLoanOnloadMfiAssignModel {

    public FrmLoanOnloadMfiAssignModel() {}

    public FrmLoanOnloadMfiAssignModel(String id, List<UserTypeDisplayModel> statusDdlModels, List<BranchDisPlayDdkModel> branchesDdlModel, List<AccountDisplayDdlModel> accountDdlModels,FrmLoanInprogressModel frmloan) {
        this.id = id;
        this.statusDdlModels = statusDdlModels;
        this.branchesDdlModel = branchesDdlModel;
        this.accountDdlModels = accountDdlModels;
        this.frmModel=frmloan;
    }

    private String id;
    private List<UserTypeDisplayModel> statusDdlModels;
    private List<BranchDisPlayDdkModel> branchesDdlModel;
    private List<AccountDisplayDdlModel> accountDdlModels;
    private  FrmLoanInprogressModel frmModel;
}
