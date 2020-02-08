package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FrmLoanDetailDisplayModel {

    public FrmLoanDetailDisplayModel() {}

    public FrmLoanDetailDisplayModel(String key, String custname, String custphone, String custvillage, String custcommune, String custdistrict, String custprovince, Double reqamount, String currency, Double period, Double appamount, String status, String reqmfi, String reqmfipic, String loantype, String otherloantype, String processedmfi, String processedmfipic, String branch, String branchuser, String agfullname, String agphone1, String agphone2, String agvillage, String agcommnue, String agdistrict, String agprovince, List<TranStatusDisplayModel> transtatus) {
        this.key = key;
        this.custname = custname;
        this.custphone = custphone;
        this.custvillage = custvillage;
        this.custcommune = custcommune;
        this.custdistrict = custdistrict;
        this.custprovince = custprovince;
        this.reqamount = reqamount;
        this.currency = currency;
        this.period = period;
        this.appamount = appamount;
        this.status = status;
        this.reqmfi = reqmfi;
        this.reqmfipic = reqmfipic;
        this.loantype = loantype;
        this.otherloantype = otherloantype;
        this.processedmfi = processedmfi;
        this.processedmfipic = processedmfipic;
        this.branch = branch;
        this.branchuser = branchuser;
        this.agfullname = agfullname;
        this.agphone1 = agphone1;
        this.agphone2 = agphone2;
        this.agvillage = agvillage;
        this.agcommnue = agcommnue;
        this.agdistrict = agdistrict;
        this.agprovince = agprovince;
        this.transtatus = transtatus;
    }

    // General Information
    private String key;
    private String custname;
    private String custphone;
    private String custvillage;
    private String custcommune;
    private String custdistrict;
    private String custprovince;

    // Request Information
    private Double reqamount;
    private String currency;
    private Double period;
    private Double appamount;

    // Status
    private String status;

    // Request MFI
    private String reqmfi;
    private String reqmfipic;
    private String loantype;
    private String otherloantype;
    private String processedmfi;
    private String processedmfipic;
    private String branch;
    private String branchuser;

    // Agent Information
    private String agfullname;
    private String agphone1;
    private String agphone2;
    private String agvillage;
    private String agcommnue;
    private String agdistrict;
    private String agprovince;

    // Transaction History
    private List<TranStatusDisplayModel> transtatus;
}
