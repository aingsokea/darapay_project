package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountDisplayOnloadModel {
    public AccountDisplayOnloadModel() {}

    public AccountDisplayOnloadModel(List<GenderDisplayDdlModel> genderDisplayDdlModelList, List<MainAddressDisplayDdlModel> provinceModelList,
                                     List<RoleDisplayDdlModel> roleDisplayDdlModelList,   List<MfiDisplayOnloadModel> _mfiDisplayOnloadModel) {
        this.genderDisplayDdlModelList = genderDisplayDdlModelList;
        this.provinceModelList = provinceModelList;
        this.roleDisplayDdlModelList = roleDisplayDdlModelList;
        this.mfiDisplayOnloadModel=_mfiDisplayOnloadModel;
    }

    List<GenderDisplayDdlModel> genderDisplayDdlModelList;
    List<MainAddressDisplayDdlModel> provinceModelList;
    List<RoleDisplayDdlModel> roleDisplayDdlModelList;
    List<MfiDisplayOnloadModel> mfiDisplayOnloadModel;
//    List<AccountDisplayCbModel> accountDisplayCbModelList;
}
