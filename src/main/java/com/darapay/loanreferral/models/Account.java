package com.darapay.loanreferral.models;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.entities.TransactionEntityImpl;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter @Setter
@ToString
public class Account extends TransactionEntityImpl<String> {

    public Account() {
    }
    public Account(Account _acc) {
        this.firstname = _acc.firstname;
        this.lastname = _acc.lastname;
        this.username = _acc.username;
        this.email = _acc.email;
        this.password = _acc.password;
        this.gender = _acc.gender;
        this.dob = _acc.dob;
        this.phone1 = _acc.phone1;
        this.phone2 = _acc.phone2;
        this.phone3 = _acc.phone3;
        this.numberoflock = _acc.numberoflock;
        this.islocked = _acc.islocked;
        this.firstlogin = _acc.firstlogin;
        this.mfiid=_acc.mfiid;
        this.usertype=_acc.usertype;
        this.identityid=_acc.identityid;
        this.position=_acc.position;
        this.positionkh=_acc.positionkh;
    }
    public Account(String firstname, String lastname, String username, String email, String password, String gender, Date dob, String phone1, String phone2, String phone3, Integer numberoflock, Boolean islocked, Boolean firstlogin,String mfiid,
                   String usertype,String identityid, String position, String positionkh) {
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
        this.numberoflock = numberoflock;
        this.islocked = islocked;
        this.firstlogin = firstlogin;
        this.mfiid=mfiid;
        this.usertype=usertype;
        this.identityid=identityid;
        this.position=position;
        this.positionkh=positionkh;
    }

    @ModelBinding()
    @Column(name = "mfiid")
    private @NonNull
    String mfiid;

    @ModelBinding()
    @Column(name = "firstname")
    private @NonNull
    String firstname;

    @ModelBinding()
    @Column(name = "lastname")
    private @NonNull
    String lastname;

    @ModelBinding()
    @Column(name = "username", unique = true)
    private @NonNull
    String username;

    @ModelBinding()
    @Column(name = "email", unique = true)
    private @NonNull
    String email;

    @Column(name = "password")
    private @NonNull String password;

    @ModelBinding()
    @Column(name = "gender")
    private @NonNull
    String gender;

    @ModelBinding()
    @Column(name = "dob")
    private Date dob;


    @ModelBinding()
    @Column(name = "phone1")
    private String phone1;

    @ModelBinding()
    @Column(name = "phone2")
    private String phone2;

    @ModelBinding()
    @Column(name = "phone3")
    private String phone3;

    @ModelBinding()
    @Column(name = "village")
    private String village;

    @ModelBinding()
    @Column(name = "commune")
    private String commune;

    @ModelBinding()
    @Column(name = "district")
    private String district;

    @ModelBinding()
    @Column(name = "province")
    private String province;

    @ModelBinding()
    @Column(name = "islocked")
    private @NonNull
    Boolean islocked;

    @ModelBinding()
    @Column(name = "numberoflock")
    private @NonNull
    Integer numberoflock;

    @ModelBinding()
    @Column(name = "firstlogin")
    private @NonNull Boolean firstlogin;

    @ModelBinding()
    @Column(name = "usertype")
    private String usertype;

    @ModelBinding()
    @Column(name = "identityid")
    private String identityid;

    @ModelBinding()
    @Column(name = "positionkh")
    String positionkh;

    @ModelBinding()
    @Column(name ="position")
    String position;

    @ModelBinding()
    @Column(name ="contactperson")
    String contactperson;

    @ModelBinding()
    @Column(name ="shopaddress")
    String shopaddress;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "accountrole",
            catalog = "public",
            joinColumns = {@JoinColumn(name = "accountid")},
            inverseJoinColumns = {@JoinColumn(name = "roleid")}
    )
    private Set<Role> roles;
}