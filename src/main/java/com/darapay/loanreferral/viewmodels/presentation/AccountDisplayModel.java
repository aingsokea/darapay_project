package com.darapay.loanreferral.viewmodels.presentation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDisplayModel {
    public AccountDisplayModel() {}

    public AccountDisplayModel(String fname, String lname, String uname, String email) {
        this.fname = fname;
        this.lname = lname;
        this.uname = uname;
        this.email = email;
    }
    private String fname;   // First Name

    private String lname;   // Last Name

    private String uname;   // Username

    private String email;   // Email
}
