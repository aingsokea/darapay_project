package com.darapay.loanreferral.models;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.entities.TransactionEntityImpl;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "frmapp")
@Getter
@Setter
public class FrmApp extends TransactionEntityImpl<String> {

    @ModelBinding()
    private @NonNull
    String familyname;

    @ModelBinding()
    private @NonNull
    String firstname;

    @ModelBinding()
    private @NonNull
    String gender;

    @ModelBinding()
    private @NonNull
    Date dob;

    @ModelBinding()
    private @NonNull
    String nationality;

    @ModelBinding()
    private @NonNull
    String currentprovince;

    @ModelBinding()
    private @NonNull
    String currentdistrict;

    @ModelBinding()
    private @NonNull
    String currentcommune;

    @ModelBinding()
    private String currentvillage;

    @ModelBinding()
    private @NonNull
    String phone;

    @ModelBinding()
    private @NonNull
    String institutionname;

    @ModelBinding()
    private @NonNull
    String occupation;

    @ModelBinding()
    private @NonNull
    String appfrmstatus;

    @ModelBinding()
    private String processedmfi;

    @ModelBinding()
    private String assignedtobranch;

    @ModelBinding()
    private String assignedtoperson;
}
