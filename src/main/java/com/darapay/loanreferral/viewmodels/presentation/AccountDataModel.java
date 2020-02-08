package com.darapay.loanreferral.viewmodels.presentation;

import com.darapay.loanreferral.annotations.ModelBinding;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class AccountDataModel {

    public AccountDataModel(String firstname, String lastname, String username, String email, String password, String gender, Date dob, String phone1, String phone2, String phone3,Integer numberoflock, Boolean islocked, Boolean firstlogin,String mfiid, String usertype, String identityid, List<MfiDisplayOnloadModel> mfiModel, List<GenderDisplayDdlModel> genderModels) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.dob = dob;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
        this.islocked = islocked;
        this.numberoflock = numberoflock;
        this.firstlogin = firstlogin;
        this.mfiid = mfiid;
        this.usertype = usertype;
        this.identityid = identityid;
        this.mfiModel = mfiModel;
        this.genderModels = genderModels;
    }

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String gender;
    private Date dob;
    private String phone1;
    private String phone2;
    private String phone3;
    private Boolean islocked;
    private Integer numberoflock;
    private Boolean firstlogin;
    private String roleid;
    private String[] reportto;
    private String mfiid;
    private String usertype;
    private String identityid;
    private List<MfiDisplayOnloadModel> mfiModel;
    private List<GenderDisplayDdlModel> genderModels;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public Boolean getIslocked() {
        return islocked;
    }

    public void setIslocked(Boolean islocked) {
        this.islocked = islocked;
    }

    public Integer getNumberoflock() {
        return numberoflock;
    }

    public void setNumberoflock(Integer numberoflock) {
        this.numberoflock = numberoflock;
    }

    public Boolean getFirstlogin() {
        return firstlogin;
    }

    public void setFirstlogin(Boolean firstlogin) {
        this.firstlogin = firstlogin;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String[] getReportto() {
        return reportto;
    }

    public void setReportto(String[] reportto) {
        this.reportto = reportto;
    }

    public String getMfiid() {
        return mfiid;
    }

    public void setMfiid(String mfiid) {
        this.mfiid = mfiid;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getIdentityid() {
        return identityid;
    }

    public void setIdentityid(String identityid) {
        this.identityid = identityid;
    }

    public List<MfiDisplayOnloadModel> getMfiModel() {
        return mfiModel;
    }

    public void setMfiModel(List<MfiDisplayOnloadModel> mfiModel) {
        this.mfiModel = mfiModel;
    }

    public List<GenderDisplayDdlModel> getGenderModels() {
        return genderModels;
    }

    public void setGenderModels(List<GenderDisplayDdlModel> genderModels) {
        this.genderModels = genderModels;
    }
}
