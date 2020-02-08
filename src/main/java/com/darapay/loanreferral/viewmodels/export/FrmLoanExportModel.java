package com.darapay.loanreferral.viewmodels.export;

import com.darapay.loanreferral.annotations.ModelBinding;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class FrmLoanExportModel {

    private String key;

    private String custname;

    private String custphone;

    private Double reqloanamount;

    private String currency;

    private String reqcurrency;

    private Double loanperiod;

    private String loanst;

    private String statusname;

    private String processedmfi;

    private String mfiname;

    private String mfipic;

    private Date createddate;

    private Double appram;

    private String branchname;

    private String loantype;

    private Date approveddate;

    private Date rejecteddate;

    private double approvedamt;

    private String assigntobusiness;

    private Date modifieddate;

    private String acc;


    private String village;

    private String commune;

    private String district;

    private String province;
    private String firstname;
    private String lastname;
    private String username;
    private String phone;
    private Date assigneddate;
    private String comment;
    private Date disbursedate;
}
