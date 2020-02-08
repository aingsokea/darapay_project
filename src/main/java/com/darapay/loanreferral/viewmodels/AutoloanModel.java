package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.models.Autoloan;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class AutoloanModel extends ModelFactory<Autoloan, String> implements IModelFactory<Autoloan, String> {
    public AutoloanModel() {}

    public AutoloanModel(AutoloanModel autoloanModel) {
        this.id = autoloanModel.getId();
        this.customername = autoloanModel.customername;
        this.phone1 = autoloanModel.phone1;
        this.phone2 = autoloanModel.phone2;
        this.loanamount = autoloanModel.loanamount;
        this.loanamountcurrency = autoloanModel.loanamountcurrency;
        this.price = autoloanModel.price;
        this.pricecurrency = autoloanModel.pricecurrency;
        this.yearmade = autoloanModel.yearmade;
        this.avg_income = autoloanModel.avg_income;
        this.avgincomecurrency = autoloanModel.avgincomecurrency;
        this.province = autoloanModel.province;
        this.district = autoloanModel.district;
        this.commune = autoloanModel.commune;
        this.village = autoloanModel.village;
        this.status = autoloanModel.status;
        this.statusname = autoloanModel.statusname;
        this.createdby = autoloanModel.createdby;
        this.createddate = autoloanModel.createddate;
    }

    public AutoloanModel(String id, @NotNull(message = "Customer Name can not be null.") String customername, @NotNull(message = "Phone 1 can not be null.") String phone1, String phone2, @NotNull(message = "Loan Amount can not be null.") Double loanamount, @NotNull(message = "Loan Amount Currency can not be null.") String loanamountcurrency, @NotNull(message = "Price can not be null.") Double price, @NotNull(message = "Price Currency can not be null.") String pricecurrency, @NotNull(message = "Year made can not be null.") String yearmade, @NotNull(message = "Avgrage can not be null.") Double avg_income, @NotNull(message = "Avgrage currency can not be null.") String avgincomecurrency, @NotNull(message = "Province can not be null.") String province, @NotNull(message = "District can not be null.") String district, @NotNull(message = "Commune can not be null.") String commune, String village, @NotNull(message = "Status can not be null.") String status, @NotNull(message = "Created By can not be null.") String createdby, @NotNull(message = " Created Date can not be null.") Date createddate) {
        this.id = id;
        this.customername = customername;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.loanamount = loanamount;
        this.loanamountcurrency = loanamountcurrency;
        this.price = price;
        this.pricecurrency = pricecurrency;
        this.yearmade = yearmade;
        this.avg_income = avg_income;
        this.avgincomecurrency = avgincomecurrency;
        this.province = province;
        this.district = district;
        this.commune = commune;
        this.village = village;
        this.status = status;
        this.createdby = createdby;
        this.createddate = createddate;
    }

    public AutoloanModel(@NotNull(message = "Customer Name can not be null.") String customername, @NotNull(message = "Phone 1 can not be null.") String phone1, String phone2, @NotNull(message = "Loan Amount can not be null.") Double loanamount, @NotNull(message = "Loan Amount Currency can not be null.") String loanamountcurrency, @NotNull(message = "Price can not be null.") Double price, @NotNull(message = "Price Currency can not be null.") String pricecurrency, @NotNull(message = "Year made can not be null.") String yearmade, @NotNull(message = "Avgrage can not be null.") Double avg_income, @NotNull(message = "Avgrage currency can not be null.") String avgincomecurrency, @NotNull(message = "Province can not be null.") String province, @NotNull(message = "District can not be null.") String district, @NotNull(message = "Commune can not be null.") String commune, String village, @NotNull(message = "Status can not be null.") String status, String statusname, String statusnamekh, @NotNull(message = "Created By can not be null.") String createdby, @NotNull(message = " Created Date can not be null.") Date createddate) {
        this.customername = customername;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.loanamount = loanamount;
        this.loanamountcurrency = loanamountcurrency;
        this.price = price;
        this.pricecurrency = pricecurrency;
        this.yearmade = yearmade;
        this.avg_income = avg_income;
        this.avgincomecurrency = avgincomecurrency;
        this.province = province;
        this.district = district;
        this.commune = commune;
        this.village = village;
        this.status = status;
        this.statusname = statusname;
        this.statusnamekh = statusnamekh;
        this.createdby = createdby;
        this.createddate = createddate;
    }


    @ModelBinding()
    @NotNull(message = "Customer Name can not be null.")
    private String customername;

    @ModelBinding()
    @NotNull(message = "Phone 1 can not be null.")
    private String phone1;

    @ModelBinding()
    private String phone2;

    @ModelBinding()
    @NotNull(message = "Loan Amount can not be null.")
    private Double loanamount;

    @ModelBinding()
    @NotNull(message = "Loan Amount Currency can not be null.")
    private String loanamountcurrency;

    @ModelBinding()
    @NotNull(message = "Price can not be null.")
    private Double price;

    @ModelBinding()
    @NotNull(message = "Price Currency can not be null.")
    private String pricecurrency;

    @ModelBinding()
    @NotNull(message = "Year made can not be null.")
    private String yearmade;

    @ModelBinding()
    @NotNull(message = "Avgrage can not be null.")
    private Double avg_income;

    @ModelBinding()
    @NotNull(message = "Avgrage currency can not be null.")
    private String avgincomecurrency;

    @ModelBinding()
    @NotNull(message = "Province can not be null.")
    private String province;

    @ModelBinding()
    @NotNull(message = "District can not be null.")
    private String district;

    @ModelBinding()
    @NotNull(message = "Commune can not be null.")
    private String commune;

    @ModelBinding()
    private String village;

    @ModelBinding()
    @NotNull(message = "Status can not be null.")
    private String status;

    private String statusname;

    private String statusnamekh;

    @ModelBinding()
    @NotNull(message = "Created By can not be null.")
    private String createdby;

    @ModelBinding()
    @NotNull(message = " Created Date can not be null.")
    private Date createddate;

}
