package com.darapay.loanreferral.viewmodels.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FrmLoanAgentModel {

    public FrmLoanAgentModel() {}

    public FrmLoanAgentModel(String custname, String custphone, Double reqloanamount, String reqcurrency, Double loanperiod, String reqmfi, String loantype, String otherloantype, String province, String district, String commune, String village, String loanst) {
        this.custname = custname;
        this.custphone = custphone;
        this.reqloanamount = reqloanamount;
        this.reqcurrency = reqcurrency;
        this.loanperiod = loanperiod;
        this.reqmfi = reqmfi;
        this.loantype = loantype;
        this.otherloantype = otherloantype;
        this.province = province;
        this.district = district;
        this.commune = commune;
        this.village = village;
        this.loanst = loanst;
    }

    private String custname;
    private String custphone;
    private Double reqloanamount;
    private String reqcurrency;
    private Double loanperiod;
    private String reqmfi;
    private String loantype;
    private String otherloantype;
    private String province;
    private String district;
    private String commune;
    private String village;
    private String loanst;
}
