package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FrmLoanTableDpDisplayModel {

    public FrmLoanTableDpDisplayModel() {}

    public FrmLoanTableDpDisplayModel(String key, String custname, String custphone, Double reqloanamount, String reqcurrency, Double loanperiod, String status, String mfipic, Date createddate,
                                      String village,String commune,String district,String province,String firstname,String lastname,String username,String phone,String picmfireq,String picmfipro) {
        this.key = key;
        this.custname = custname;
        this.custphone = custphone;
        this.reqloanamount = reqloanamount;
        this.reqcurrency = reqcurrency;
        this.loanperiod = loanperiod;
        this.status = status;
        this.mfipic = mfipic;
        this.createddate = createddate;
        this.village=village;
        this.commune=commune;
        this.district=district;
        this.province=province;
        this.firstname=firstname;
        this.lastname=lastname;
        this.username=username;
        this.phone=phone;
        this.picmfireq=picmfireq;
        this.picmfipro=picmfipro;
    }

    private String key;
    private String custname;
    private String custphone;
    private Double reqloanamount;
    private String reqcurrency;
    private Double loanperiod;
    private String status;
    private String mfipic;
    private Date createddate;
    private String username;
    private String firstname;
    private String lastname;
    private String phone;
    private String village;
    private String commune;
    private String district;
    private String province;
    private String picmfireq;
    private String picmfipro;
}
