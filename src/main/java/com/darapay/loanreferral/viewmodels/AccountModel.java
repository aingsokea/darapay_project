package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.models.Account;
import com.darapay.loanreferral.viewmodels.presentation.MfiDisplayOnloadModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class AccountModel extends ModelFactory<Account, String> implements IModelFactory<Account, String> {
    @ModelBinding()
    @NotNull(message = "First Name cannot be null.")
    private String firstname;

    @ModelBinding()
    @NotNull(message = "Last Name cannot be null.")
    private String lastname;

    @ModelBinding()
    @NotNull(message = "Username cannot be null.")
    private String username;

    @ModelBinding()
    @NotNull(message = "Email cannot be null.")
    private String email;

    @ModelBinding()
    @NotNull(message = "Password cannot be null")
    private String password;

    @ModelBinding()
    @NotNull(message = "Gender cannot be null.")
    private String gender;

    @ModelBinding()
    private Date dob;

    @ModelBinding()
    @NotNull(message = "Phone 1 cannot be null.")
    private String phone1;

    @ModelBinding()
    private String phone2;

    @ModelBinding()
    private String phone3;

    @ModelBinding()
    private Boolean islocked;

    @ModelBinding()
    private String village;

    @ModelBinding()
    private String commune;

    @ModelBinding()
    private String district;

    @ModelBinding()
    private String province;

    @ModelBinding()
    private Integer numberoflock;

    @ModelBinding()
    private Boolean firstlogin;

    private String roleid;

    private String[] reportto;

    @ModelBinding()
    private String mfiid;

    @ModelBinding()
    private String usertype;

    @ModelBinding()
    private String identityid;

    @ModelBinding()
    private String position;

    @ModelBinding()
    private String positionkh;

    public AccountModel(@NotNull(message = "First Name cannot be null.") String firstname, @NotNull(message = "Last Name cannot be null.") String lastname, @NotNull(message = "Username cannot be null.") String username, @NotNull(message = "Email cannot be null.") String email, @NotNull(message = "Password cannot be null") String password, @NotNull(message = "Gender cannot be null.") String gender, Date dob, @NotNull(message = "Phone 1 cannot be null.") String phone1, String phone2, String phone3, Boolean islocked, String village, String commune, String district, String province, Integer numberoflock, Boolean firstlogin, String roleid, String[] reportto, String mfiid, String usertype, String identityid,
                        String position,String positionkh ) {
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
        this.village = village;
        this.commune = commune;
        this.district = district;
        this.province = province;
        this.numberoflock = numberoflock;
        this.firstlogin = firstlogin;
        this.roleid = roleid;
        this.reportto = reportto;
        this.mfiid = mfiid;
        this.usertype = usertype;
        this.identityid = identityid;
        this.position=position;
        this.positionkh=positionkh;
    }
public AccountModel(){

}
}
