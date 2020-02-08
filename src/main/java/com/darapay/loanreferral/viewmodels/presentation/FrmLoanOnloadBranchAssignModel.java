package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FrmLoanOnloadBranchAssignModel {
    public FrmLoanOnloadBranchAssignModel() {}

    public FrmLoanOnloadBranchAssignModel(String id, List<UserTypeDisplayModel> statusDdlModels) {
        this.id = id;
        this.statusDdlModels = statusDdlModels;
    }

    private String id;
    private List<UserTypeDisplayModel> statusDdlModels;
}
