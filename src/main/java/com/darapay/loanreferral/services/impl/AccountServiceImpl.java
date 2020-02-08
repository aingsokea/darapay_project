package com.darapay.loanreferral.services.impl;

import com.darapay.loanreferral.anonymous.AnonymousFunc;
import com.darapay.loanreferral.models.*;
import com.darapay.loanreferral.repositories.AccountRepository;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.security.Exceptioins.RecoredNotFoundException;
import com.darapay.loanreferral.services.*;
import com.darapay.loanreferral.viewmodels.AccountModel;
import com.darapay.loanreferral.viewmodels.AccountRoleAuthorizeModel;
import com.darapay.loanreferral.viewmodels.ConfigureModel;
import com.darapay.loanreferral.viewmodels.core.ChangePasswordModel;
import com.darapay.loanreferral.viewmodels.export.AccountExportModel;
import com.darapay.loanreferral.viewmodels.export.AccountRoleAuthorizeExportModel;
import com.darapay.loanreferral.viewmodels.presentation.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Repository
public class AccountServiceImpl extends BaseServiceImpl<Account, String, AccountModel, AccountExportModel> implements AccountService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountRoleService accountRoleService;

    @Autowired
    private ConfigureService configureService;

    @Autowired
    private LogMastersService logMastersService;

    @Autowired
    private MainAddressService mainAddressService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    private AccountRoleAuthorizeService accountRoleAuthorizeService;

    @Override
    protected ExtendedRepository<Account, String> getBaseRepository() {
        return accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    Class aliasModel() {
        return AccountModel.class;
    }

    @Override
    Class aliasExportModel() {
        return AccountExportModel.class;
    }

    @Override
    @Transactional
    public AccountDisplayModel save(AccountModel accm) {
        // Check Account (Username, and Phone 1 make sure it Unique)
        checkUnique(accm.getUsername(), accm.getPhone1());

        // Create Account
        // Encrypt Password
        String encPwd = passwordEncoder.encode(accm.getPassword());
        Account acc = new Account(accm.getFirstname(), accm.getLastname(), accm.getUsername(), accm.getEmail(),
                encPwd, accm.getGender(), accm.getDob(), accm.getPhone1(), accm.getPhone2(),
                accm.getPhone3(), accm.getNumberoflock(), accm.getIslocked(), accm.getFirstlogin(),accm.getMfiid(),accm.getUsertype(),accm.getIdentityid(),
                accm.getPosition(),accm.getPositionkh());
        acc.setCreatedby(AnonymousFunc.GetCurrentPrincipalName().getName());
        acc.setCreateddate(new Date());
        Account oldAcct = save(acc); // End Create Account

        // Create Account Role
        String reportTo = "";
       /* for (String rt: accm.getReportto()) {
            if (reportTo.equals("")) {
                reportTo = rt;
            } else {
                reportTo = reportTo + "|" + rt;
            }
        }*/
        reportTo="1|2|1982|1995|1996|1997|1998|2331|c93628c7-15a6-4631-873a-154442c527eb|45710fee-6a84-4224-b8ed-7e0faffaadca|OPO01|OPO02|OPO03|OPO04|OPO05|OPO06";
        AccountRole accr = new AccountRole(oldAcct.getId(), accm.getRoleid(), reportTo);
        accountRoleService.save(accr);  // End Create Account Role

        // Save Log
        String todata = AnonymousFunc.converFromModelToToJson(oldAcct);
        LogMasters lm = new LogMasters(oldAcct.getId(), "TABLE_ACCOUNT", "", todata, "CREATE_RECORD");
        lm.setCreatedby(AnonymousFunc.GetCurrentPrincipalName().getName());
        lm.setCreateddate(new Date());
        logMastersService.save(lm);
        // End Save Log

        AccountDisplayModel adm = new AccountDisplayModel(oldAcct.getFirstname(), oldAcct.getLastname(),oldAcct.getUsername(), oldAcct.getEmail());

        return adm;
    }

    private void checkUnique(String username, String phone1) {
        if (accountRepository.existsByUsername(username)) {
            throw new RecoredNotFoundException("The Username is Already Exited");
        }

        if (accountRepository.existsByPhone1(phone1)) {
            throw new RecoredNotFoundException("The Phone1 is Already Exited");
        }
    }

    @Override
    public Account findAccountByUsernameAndEnable(String username, Boolean enable) {
        Optional<Account> account = accountRepository.findAccountByUsernameAndEnable(username, enable);
        if (!account.isPresent()) {
            throw new RecoredNotFoundException("The Phone1 is Already Exited");
        }
        return account.get();
    }

    @Override
    public List<AccountDisplayDdlModel> findAccountByMfiProc(String mfiid) {
        String sql = "SELECT ACC.id AS key, ACC.firstname, ACC.lastname, R.branchid AS branchcode FROM account AS ACC INNER JOIN accountrole AS ACCR" +
                " ON ACCR.accountid = ACC.id INNER JOIN role AS R ON R.id = ACCR.roleid WHERE R.mfiid = '" + mfiid + "' AND R.branchid <> '' AND ACC.enable = true;";

        return entityManager.createNativeQuery(sql).unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.aliasToBean(AccountDisplayDdlModel.class)).list();
    }

    @Override
    @Transactional
    public Boolean changePwd(String newPwd) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        String newPassword = passwordEncoder.encode(newPwd);
        String sql = "UPDATE account SET password = '" + newPassword + "', firstlogin = false, numberoflock = 0 WHERE username = '" +
                auth + "'";

        entityManager.createNativeQuery(sql).executeUpdate();
        return true;
    }


    @Override
    public Boolean changePasswordByStaff(ChangePasswordModel changePasswordModel) {
        String newPassword = passwordEncoder.encode(changePasswordModel.getPassword());
        Account acc =  find(changePasswordModel.getId());
        acc.setPassword(newPassword);
        acc.setFirstlogin(true);
        acc.setNumberoflock(0);
        acc.setIslocked(false);
        save(acc);
        return true;
    }
@Autowired
  private MfiService  mfiService;
    @Override
    public AccountDisplayOnloadModel getAccountOnload() {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        // Get List Gender
        List<UserTypeDisplayModel> udl  = configureService.getUserTypeByTypeAndEnableOrderByItem2("GENDERTYPE", true);
        List<GenderDisplayDdlModel> genderDisplayDdlModelList = new ArrayList<>();
        udl.forEach(u -> {
            GenderDisplayDdlModel gddm = new GenderDisplayDdlModel(u.getUserkey(), u.getName(), u.getNamekh());
            genderDisplayDdlModelList.add(gddm);
        });

        // Get List of Province
        List<MainAddressDisplayDdlModel> maddml = mainAddressService.getMainAddressByParentCode("855");

        // Get List Role
        List<RoleDisplayDdlModel> roleDisplayDdlModelList = new ArrayList<>();
        List<AccountRoleAuthorizeModel> aram = accountRoleAuthorizeService.getAllAccountRoleAuthorizeByUsername(auth, true);
        aram.forEach(a -> {
            RoleDisplayDdlModel rdm = roleService.getRoleById(a.getRoleid());
            roleDisplayDdlModelList.add(rdm);
        });
        // Get Mfis
        List<MfiDisplayOnloadModel> mfimodels = mfiService.getAllMfiByEnableOrderByTabOrder(true);
        AccountDisplayOnloadModel model=new AccountDisplayOnloadModel(genderDisplayDdlModelList, maddml, roleDisplayDdlModelList,mfimodels);
        return model;
    }


    @Override
    public List<AccountDisplayCbModel> getAccountLevel(String roleId) {
        Role r = roleService.find(roleId);
        String sql = "SELECT ACC.id, CONCAT(ACC.firstname, ' ', ACC.lastname) AS name FROM account AS ACC " +
                "INNER JOIN accountrole AS ACCR ON ACCR.accountid = ACC.id " +
                "INNER JOIN role AS R ON R.id = ACCR.roleid " +
                "WHERE R.level < '" + r.getLevel() + "' AND R.protype = ('"+ r.getProtype() +"')";

        return entityManager.createNativeQuery(sql).unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.aliasToBean(AccountDisplayCbModel.class)).list();
    }

    @Override
    public Boolean unlockAccount(String id) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        Date modifieddate = new Date();
        Account acc =  find(id);
        acc.setNumberoflock(0);
        acc.setIslocked(false);
        acc.setModifiedby(auth);
        acc.setModifieddate(modifieddate);
        save(acc);
        return true;
    }

    @Override
    public List<AccountDisplayCbModel> GetCOUserType(String usertype) {

        String sql = "SELECT id, CONCAT(firstname, ' ', lastname) AS name FROM account where  usertype='"+usertype+"' ";

        return entityManager.createNativeQuery(sql).unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.aliasToBean(AccountDisplayCbModel.class)).list();
    }

    @Override
    public AccountDataModel getAccountById(String id) {
        // Check Frmloan Id
        if(!accountRepository.existsById(id)) {
            throw new RecoredNotFoundException("The Id did not found");
        }
        //Get account
        Account accm = find(id);
        // Get List Gender
        List<UserTypeDisplayModel> udl  = configureService.getUserTypeByTypeAndEnableOrderByItem2("GENDERTYPE", true);
        List<GenderDisplayDdlModel> genderDisplayDdlModelList = new ArrayList<>();
        udl.forEach(u -> {
            GenderDisplayDdlModel gddm = new GenderDisplayDdlModel(u.getUserkey(), u.getName(), u.getNamekh());
            genderDisplayDdlModelList.add(gddm);
        });
        // Get Mfis
        List<MfiDisplayOnloadModel> mfimodels = mfiService.getAllMfiByEnableOrderByTabOrder(true);
        AccountDataModel  _accountModel =new AccountDataModel(accm.getFirstname(), accm.getLastname(), accm.getUsername(), accm.getEmail(),
                accm.getPassword(), accm.getGender(), accm.getDob(), accm.getPhone1(), accm.getPhone2(),
                accm.getPhone3(), accm.getNumberoflock(), accm.getIslocked(), accm.getFirstlogin(),accm.getMfiid(),accm.getUsertype(),accm.getIdentityid(),mfimodels,genderDisplayDdlModelList);
        return _accountModel;
    }

    @Override
    public AccountModel getAccountUpdate(String id, AccountModel model) {
        String auth = AnonymousFunc.GetCurrentPrincipalName().getName();
        Date createddate = new Date();

        // Check Frmloan Id
        if(!accountRepository.existsById(id)) {
            throw new RecoredNotFoundException("The Id did not found");
        }

        //Get Frm Loan
        Account account = find(id);



        Account oldFrmLoan = new Account(account);
        oldFrmLoan.setCreatedby(account.getCreatedby());
        oldFrmLoan.setCreateddate(account.getCreateddate());

        // Update Frm Loan
        account.setFirstname(model.getFirstname());
        account.setLastname(model.getLastname());
        account.setUsername(model.getUsername());
        account.setEmail(model.getEmail());
        account.setGender(model.getGender());
        account.setDob(model.getDob());
        account.setPhone1(model.getPhone1());
        account.setPhone2(model.getPhone2());
        account.setPhone3(model.getPhone3());
        account.setMfiid(model.getMfiid());
        account.setUsertype(model.getUsertype());
        account.setIdentityid(model.getIdentityid());
        account.setModifiedby(auth);
        account.setModifieddate(createddate);
        Account newaccount =  save(account);
        // Create Log for Update FrmLoan
        String fromdata = AnonymousFunc.converFromModelToToJson(oldFrmLoan);
        String todata = AnonymousFunc.converFromModelToToJson(newaccount);
        LogMasters lm = new LogMasters(newaccount.getId(), "TABLE_FRMLOAN", fromdata, todata, "UPDATE_RECORD");
        lm.setCreatedby(auth);
        lm.setCreateddate(createddate);
        logMastersService.save(lm);
        return model;
    }
}
