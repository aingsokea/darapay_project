package com.darapay.loanreferral.services;


import com.darapay.loanreferral.models.Account;
import com.darapay.loanreferral.viewmodels.AccountModel;
import com.darapay.loanreferral.viewmodels.core.ChangePasswordModel;
import com.darapay.loanreferral.viewmodels.export.AccountExportModel;
import com.darapay.loanreferral.viewmodels.presentation.*;

import java.util.List;

public interface AccountService extends BaseService<Account, String, AccountModel, AccountExportModel> {
    Account findAccountByUsernameAndEnable(String username, Boolean enable);
    AccountDisplayModel save(AccountModel accountModel);
    List<AccountDisplayDdlModel> findAccountByMfiProc(String mfiid);
    Boolean changePwd(String newPwd);
    AccountDisplayOnloadModel getAccountOnload();
    List<AccountDisplayCbModel> getAccountLevel(String roleid);
    Boolean unlockAccount(String id);
    Boolean changePasswordByStaff(ChangePasswordModel changePasswordModel);
    List<AccountDisplayCbModel> GetCOUserType(String usertype);
    AccountDataModel getAccountById(String id);
    AccountModel getAccountUpdate(String id,AccountModel model);

}
