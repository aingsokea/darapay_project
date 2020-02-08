package com.darapay.loanreferral.models;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.entities.TransactionEntityImpl;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "frmloan")
@Getter
@Setter
public class FrmLoan extends TransactionEntityImpl<String> {

    public FrmLoan () {}

    public FrmLoan (FrmLoan f) {
        this.custname = f.getCustname();
        this.custphone = f.getCustphone();
        this.reqloanamount = f.getReqloanamount();
        this.reqcurrency = f.getReqcurrency();
        this.loanperiod = f.getLoanperiod();
        this.reqmfi = f.getReqmfi();
        this.loantype = f.getLoantype();
        this.otherloantype = f.getOtherloantype();
        this.village = f.getVillage();
        this.commune = f.getCommune();
        this.district = f.getDistrict();
        this.province = f.getProvince();
        this.assignedtobranch = f.getAssignedtobranch();
        this.assignedtoperson = f.getAssignedtoperson();
        this.processedmfi = f.getProcessedmfi();
        this.loanst = f.getLoanst();
        this.appram = f.getAppram();
        this.approvedamt=f.getApprovedamt();
    }

    public FrmLoan(String custname, String custphone, Double reqloanamount, String reqcurrency, Double loanperiod, String reqmfi, String loantype, String otherloantype, String village, String commune, String district, String province, String assignedtobranch, String assignedtoperson, String processedmfi, String loanst, Double appram,Double approvedamt) {
        this.custname = custname;
        this.custphone = custphone;
        this.reqloanamount = reqloanamount;
        this.reqcurrency = reqcurrency;
        this.loanperiod = loanperiod;
        this.reqmfi = reqmfi;
        this.loantype = loantype;
        this.otherloantype = otherloantype;
        this.village = village;
        this.commune = commune;
        this.district = district;
        this.province = province;
        this.assignedtobranch = assignedtobranch;
        this.assignedtoperson = assignedtoperson;
        this.processedmfi = processedmfi;
        this.loanst = loanst;
        this.appram = appram;
        this.approvedamt=approvedamt;
    }

    @ModelBinding()
    @Column(name = "custname")
    private @NonNull
    String custname;

    @ModelBinding()
    @Column(name = "custphone")
    private @NonNull
    String custphone;

    @ModelBinding()
    @Column(name = "reqloanamount")
    private @NonNull
    Double reqloanamount;

    @ModelBinding()
    @Column(name = "reqcurrency")
    private @NonNull
    String reqcurrency;

    @ModelBinding()
    @Column(name = "loanperiod")
    private @NonNull
    Double loanperiod;

    @ModelBinding()
    @Column(name = "reqmfi")
    private @NonNull
    String reqmfi;

    @ModelBinding()
    @Column(name = "loantype")
    private String loantype;

    @ModelBinding()
    @Column(name = "otherloantype")
    private String otherloantype;

    @ModelBinding()
    @Column(name = "village")
    private @NonNull
    String village;

    @ModelBinding()
    @Column(name = "commune")
    private @NonNull
    String commune;

    @ModelBinding()
    @Column(name = "district")
    private @NonNull
    String district;

    @ModelBinding()
    @Column(name = "province")
    private @NonNull
    String province;

    @ModelBinding()
    @Column(name = "assignedtobranch")
    private String assignedtobranch;

    @ModelBinding()
    @Column(name = "assignedtoperson")
    private String assignedtoperson;

    @ModelBinding()
    @Column(name = "processedmfi")
    private String processedmfi;

    @ModelBinding()
    @Column(name = "loanst")
    private @NonNull
    String loanst;

    @ModelBinding()
    @Column(name = "appram")
    private Double appram;

    @ModelBinding()
    @Column(name = "approvedamt")
    private Double approvedamt;

    @ModelBinding()
    @Column(name = "acc")
    private String acc;


}
