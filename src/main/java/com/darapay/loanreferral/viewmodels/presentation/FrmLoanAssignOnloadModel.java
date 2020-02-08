package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FrmLoanAssignOnloadModel {

    public FrmLoanAssignOnloadModel() {}

    public FrmLoanAssignOnloadModel(String id, List<UserTypeDisplayModel> status, List<MfiDisplayOnloadModel> mfi) {
        this.id = id;
        this.status = status;
        this.mfi = mfi;
    }

    private String id;
    private List<UserTypeDisplayModel> status;
    private List<MfiDisplayOnloadModel> mfi;
}
