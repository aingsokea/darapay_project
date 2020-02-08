package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.models.FrmApp;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class FrmAppModel extends ModelFactory<FrmApp, String> implements IModelFactory<FrmApp, String> {
    @ModelBinding()
    @NotNull(message = "Family Name can not be null.")
    private String familyname;

    @ModelBinding()
    @NotNull(message = "First Name can not be null.")
    private String firstname;

    @ModelBinding()
    @NotNull(message = "Gender can not be null.")
    private String gender;

    @ModelBinding()
    @NotNull(message = "Date Of Birth can not be null.")
    private Date dob;

    @ModelBinding()
    @NotNull(message = "Nationality can not be null.")
    private String nationality;

    @ModelBinding()
    @NotNull(message = "Current Province can not be null.")
    private String currentprovince;

    @ModelBinding()
    @NotNull(message = "Current District can not be null.")
    private String currentdistrict;

    @ModelBinding()
    @NotNull(message = "Current Commune can not be null.")
    private String currentcommune;

    @ModelBinding()
    private String currentvillage;

    @ModelBinding()
    @NotNull(message = "Phone can not be null.")
    private String phone;

    @ModelBinding()
    @NotNull(message = "Institutionname can not be null")
    private String institutionname;

    @ModelBinding()
    @NotNull(message = "Occupation can not be null.")
    private String occupation;

    @ModelBinding()
    @NotNull(message = "App Form Status can not be null.")
    private String appfrmstatus;

    @ModelBinding()
    private String processedmfi;

    @ModelBinding()
    private String assignedtobranch;

    @ModelBinding()
    private String assignedtoperson;
}
