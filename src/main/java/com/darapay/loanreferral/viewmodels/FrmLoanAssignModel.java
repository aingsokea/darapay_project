package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FrmLoanAssignModel {

    public FrmLoanAssignModel () {}

    public FrmLoanAssignModel(String loanst, String processedmfi,String assigntoperson,String username, TranStatusModel tranStatusModels,double approvedamt,String acc) {
        this.loanst = loanst;
        this.processedmfi = processedmfi;
        this.tranStatusModels = tranStatusModels;
        this.assigntoperson=assigntoperson;
        this.username=username;
        this.approvedamt=approvedamt;
        this.acc=acc;
    }

    @ModelBinding()
    private String loanst;

    @ModelBinding()
    private String processedmfi;

    @ModelBinding()
    private String assigntoperson;

    @ModelBinding()
    private String username;

    @ModelBinding()
    private double approvedamt;

    @ModelBinding()
    private String acc;

    private TranStatusModel tranStatusModels;
}
