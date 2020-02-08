package com.darapay.loanreferral.entities;

import java.util.Date;

public interface TransactionEntity {
    Date createddate = new Date();
    String createdby = "";
    Date modifieddate = new Date();
    String modifiedby = "";
    boolean enable = true;
}
