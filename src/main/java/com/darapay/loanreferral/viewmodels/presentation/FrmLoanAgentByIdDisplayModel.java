package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FrmLoanAgentByIdDisplayModel {

    public FrmLoanAgentByIdDisplayModel() {}

    public FrmLoanAgentByIdDisplayModel(String key, String custname, String custphone, Double reqamount, String currency, Double period, String province, String district, String commune, String village, String reqmfi, String loantype, String otherloantype, List<UserTypeDisplayModel> currencies, List<MainAddressDisplayDdlModel> provinces, List<MainAddressDisplayDdlModel> districts, List<MainAddressDisplayDdlModel> communes, List<MainAddressDisplayDdlModel> villages, List<MfiDisplayOnloadModel> mfis) {
        this.key = key;
        this.custname = custname;
        this.custphone = custphone;
        this.reqamount = reqamount;
        this.currency = currency;
        this.period = period;
        this.province = province;
        this.district = district;
        this.commune = commune;
        this.village = village;
        this.reqmfi = reqmfi;
        this.loantype = loantype;
        this.otherloantype = otherloantype;
        this.currencies = currencies;
        this.provinces = provinces;
        this.districts = districts;
        this.communes = communes;
        this.villages = villages;
        this.mfis = mfis;
    }

    private String key;
    private String custname;
    private String custphone;
    private Double reqamount;
    private String currency;
    private Double period;
    private String province;
    private String district;
    private String commune;
    private String village;
    private String reqmfi;
    private String loantype;
    private String otherloantype;

    private List<UserTypeDisplayModel> currencies;
    private List<MainAddressDisplayDdlModel> provinces;
    private List<MainAddressDisplayDdlModel> districts;
    private List<MainAddressDisplayDdlModel> communes;
    private List<MainAddressDisplayDdlModel> villages;
    private List<MfiDisplayOnloadModel> mfis;
}
