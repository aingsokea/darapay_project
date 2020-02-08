package com.darapay.loanreferral.viewmodels.export;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AutoloanExportModel {
    private String no;
    private String customername;
    private String phone1;
    private Double loanamount;
    private String loanamountcurrencyname;
    private Double price;
    private String carpricecurrencyname;
    private String yearmade;
    private Double avg_income;
    private String avgincomecurrencyname;
    private String dealername;
    private Date createddate;
    private String statusname;
}
