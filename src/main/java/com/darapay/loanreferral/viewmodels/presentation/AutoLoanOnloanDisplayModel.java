package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AutoLoanOnloanDisplayModel {
    public AutoLoanOnloanDisplayModel() {}

    public AutoLoanOnloanDisplayModel(List<CurrencyDisplayModel> currencyDisplayModelList, List<MainAddressDisplayDdlModel> mainAddressDisplayModelList) {
        this.currencyDisplayModelList = currencyDisplayModelList;
        this.mainAddressDisplayModelList = mainAddressDisplayModelList;
    }

    List<CurrencyDisplayModel> currencyDisplayModelList;
    List<MainAddressDisplayDdlModel> mainAddressDisplayModelList;
}
