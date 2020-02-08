package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountAutoloanChangeStatusDisplayModel {
    public AccountAutoloanChangeStatusDisplayModel() {}

    public AccountAutoloanChangeStatusDisplayModel(String fullname, String phone1, String phone2, String village, String commune, String district, String province) {
        this.fullname = fullname;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.village = village;
        this.commune = commune;
        this.district = district;
        this.province = province;
    }

    private String fullname;
    private String phone1;
    private String phone2;
    private String village;
    private String commune;
    private String district;
    private String province;
}
