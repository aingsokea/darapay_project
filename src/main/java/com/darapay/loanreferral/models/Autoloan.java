package com.darapay.loanreferral.models;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.entities.TransactionEntityImpl;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "autoloan")
@Getter
@Setter
public class Autoloan extends TransactionEntityImpl<String> {

    public Autoloan() {}

    public Autoloan(Autoloan al) {
        this.customername = al.getCustomername();
        this.phone1 = al.getPhone1();
        this.phone2 = al.getPhone2();
        this.loanamount = al.getLoanamount();
        this.loanamountcurrency = al.getLoanamountcurrency();
        this.price = al.getPrice();
        this.pricecurrency = al.getPricecurrency();
        this.yearmade = al.getYearmade();
        this.avg_income = al.getAvg_income();
        this.avgincomecurrency = al.avgincomecurrency;
        this.province = al.getProvince();
        this.district = al.getDistrict();
        this.commune = al.getCommune();
        this.village = al.getVillage();
        this.status = al.getStatus();
    }

    public Autoloan(String customername, String phone1, String phone2, Double loanamount, String loanamountcurrency, Double price, String pricecurrency, String yearmade, Double avg_income, String avgincomecurrency, String province, String district, String commune, String village, String status) {
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
    }

    public Autoloan(String id, String customername, String phone1, String phone2, Double loanamount, String loanamountcurrency, Double price, String pricecurrency, String yearmade, Double avg_income, String avgincomecurrency, String province, String district, String commune, String village, String status) {
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
    }

    @ModelBinding()
    @Column(name = "customername")
    private @NonNull
    String customername;

    @ModelBinding()
    @Column(name = "phone1")
    private @NonNull
    String phone1;

    @ModelBinding()
    @Column(name = "phone2")
    private String phone2;

    @ModelBinding()
    @Column(name = "loanamount")
    private @NonNull
    Double loanamount;

    @ModelBinding()
    @Column(name = "loanamountcurrency")
    private @NonNull
    String loanamountcurrency;

    @ModelBinding()
    @Column(name = "price")
    private @NonNull
    Double price;

    @ModelBinding()
    @Column(name = "pricecurrency")
    private @NonNull
    String pricecurrency;

    @ModelBinding()
    @Column(name = "yearmade")
    private @NonNull
    String yearmade;

    @ModelBinding()
    @Column(name = "avg_income")
    private @NonNull
    Double avg_income;

    @ModelBinding()
    @Column(name = "avgincomecurrency")
    private @NonNull
    String avgincomecurrency;

    @ModelBinding()
    @Column(name = "province")
    private @NonNull
    String province;

    @ModelBinding()
    @Column(name = "district")
    private @NonNull
    String district;

    @ModelBinding()
    @Column(name = "commune")
    private @NonNull
    String commune;

    @ModelBinding()
    @Column(name = "village")
    private String village;

    @ModelBinding()
    @Column(name = "status")
    private @NonNull
    String status;
}
