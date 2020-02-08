package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FrmLoanOnloadModel {

    public FrmLoanOnloadModel() {}

    public FrmLoanOnloadModel(List<UserTypeDisplayModel> currencies, List<MainAddressDisplayDdlModel> provinces, List<MfiDisplayOnloadModel> mfis) {
        this.currencies = currencies;
        this.provinces = provinces;
        this.mfis = mfis;
    }

    private List<UserTypeDisplayModel> currencies;
    private List<MainAddressDisplayDdlModel> provinces;
    private List<MfiDisplayOnloadModel> mfis;
}
