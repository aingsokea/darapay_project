package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.models.FrmLoan;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class FrmLoanModel extends ModelFactory<FrmLoan, String> implements IModelFactory<FrmLoan, String> {
    @ModelBinding()
    private String key;

    @ModelBinding()
    @NotNull(message = "Customer Name cannot be null.")
    private String custname;

    @ModelBinding()
    @NotNull(message = "Customer Phone cannot be null.")
    private String custphone;

    @ModelBinding()
    @NotNull(message = "Request Loan Amount cannot be null.")
    private Double reqloanamount;

    @ModelBinding()
    @NotNull(message = "Request Currency cannot be null.")
    private String reqcurrency;

    private String currency;

    @ModelBinding()
    @NotNull(message = "Loan Period cannot be null.")
    private Double loanperiod;

    @ModelBinding()
    @NotNull(message = "Request Mfi cannot be null.")
    private String reqmfi;

    @ModelBinding()
    private String loantype;

    @ModelBinding()
    private String otherloantype;

    @ModelBinding()
    @NotNull(message = "Village cannot be null.")
    private String village;

    @ModelBinding()
    @NotNull(message = "Commune cannot be null.")
    private String commune;

    @ModelBinding()
    @NotNull(message = "District cannot be null.")
    private String district;

    @ModelBinding()
    @NotNull(message = "Province cannot be null.")
    private String province;

    @ModelBinding()
    private String assignedtobranch;

    @ModelBinding()
    private String assignedtoperson;

    @ModelBinding()
    private String processedmfi;

    private String mfiname;

    private String mfipic;

    @ModelBinding()
    private String loanst;

    private String statusname;

    @ModelBinding()
    private Double appram;

    private String branchname;

    private Date approveddate;

    private Date rejecteddate;

    @ModelBinding()
    private double approvedamt;

    private String assigntobusiness;

    @ModelBinding()
    private String acc;

    private String firstname;
    private String lastname;
    private String username;
    private String phone;
    private String picmfireq;
    private String picmfipro;
    private Date assigneddate;
   private  String comment;
    private Date disbursedate;


}
