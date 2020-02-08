package com.darapay.loanreferral.viewmodels.presentation;

import com.darapay.loanreferral.models.Configure;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TranStatusDisplayModel {
    public TranStatusDisplayModel() {}

    public TranStatusDisplayModel(Configure fromstatus, Configure tostatus, String comment, String phone, String createdby, Date createddate) {
        this.fromstatus = fromstatus;
        this.tostatus = tostatus;
        this.comment = comment;
        this.phone = phone;
        this.createdby = createdby;
        this.createddate = createddate;
    }

    private Configure fromstatus;
    private Configure tostatus;
    private String comment;
    private String phone;
    private String createdby;
    private Date createddate;
}
