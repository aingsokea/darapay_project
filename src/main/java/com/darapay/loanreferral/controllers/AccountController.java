package com.darapay.loanreferral.controllers;

import com.darapay.loanreferral.models.Account;
import com.darapay.loanreferral.services.AccountService;
import com.darapay.loanreferral.services.BaseService;
import com.darapay.loanreferral.viewmodels.AccountModel;
import com.darapay.loanreferral.viewmodels.AdvanceModel;
import com.darapay.loanreferral.viewmodels.core.ChangePasswordModel;
import com.darapay.loanreferral.viewmodels.core.FrmLoanAgentModel;
import com.darapay.loanreferral.viewmodels.export.AccountExportModel;
import com.darapay.loanreferral.viewmodels.presentation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController extends EntityApiController<Account, String, AccountModel, AccountExportModel> {
    @Autowired
    private AccountService accountService;

    @Override
    protected BaseService<Account, String, AccountModel, AccountExportModel> getBaseService() {
        return accountService;
    }

    @Override
    protected Account NewEntity() {
        return new Account();
    }

    @Override
    protected AccountModel NewModel() {
        return new AccountModel();
    }

    @Override
    public ResponseEntity<?> Post(@Valid @RequestBody AccountModel accountModel) {
        Map<String, Object> map = new HashMap<>();
        accountModel.setNumberoflock(0);
        accountModel.setIslocked(false);
        accountModel.setFirstlogin(true);
        // map.put("acctModel", accountService.save(accountModel));
        return new ResponseEntity<>(accountService.save(accountModel), HttpStatus.OK);
    }



    @CrossOrigin
    @RequestMapping(value = "/changepwd/{newpwd}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasPermission(this, 'CHANGEPWD')")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> ChangePassword(@PathVariable() String newpwd) {
        return new ResponseEntity<>(accountService.changePwd(newpwd), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/changepwdbystaff", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission(this, 'CHANGEPWD_BYSTAFF')")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> ChangePasswordByStaff(@RequestBody ChangePasswordModel changePasswordModel) {
        return new ResponseEntity<>(accountService.changePasswordByStaff(changePasswordModel), HttpStatus.OK);
    }


    @CrossOrigin
    @RequestMapping(value = "/onload", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission(this, 'ACCOUNT_ONLOAD')")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> GetAccountOnload() {
        return new ResponseEntity<>(accountService.getAccountOnload(), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/accountslevel/{roleid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission(this, 'ACCOUNT_LEVEL')")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> GetAccountOnload(@PathVariable() String roleid) {
        return new ResponseEntity<>(accountService.getAccountLevel(roleid), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/unlock/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission(this, 'ACCOUNT_UNLOCK')")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> UnlockAccount(@PathVariable() String id) {
        return new ResponseEntity<>(accountService.unlockAccount(id), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/cotype/{usertype}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> GetCOUserType(@PathVariable() String usertype ) {
        return new ResponseEntity<>(accountService.GetCOUserType(usertype), HttpStatus.OK);
    }

    // Get frmloan by id
    @RequestMapping(value = "/getEdit/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> getAccountById(@PathVariable String id) {
        AccountDataModel _account = accountService.getAccountById(id);
        return new ResponseEntity<Object>(_account, HttpStatus.OK);
    }
    // Update frmloan
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> getAccountUpdate(@PathVariable String id, @RequestBody AccountModel _accountModel) {
        AccountModel flam = accountService.getAccountUpdate(id, _accountModel);
        return new ResponseEntity<Object>(flam, HttpStatus.OK);
    }
}
