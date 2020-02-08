package com.darapay.loanreferral.services;


import com.darapay.loanreferral.models.AccountRole;
import com.darapay.loanreferral.viewmodels.AccountRoleModel;
import com.darapay.loanreferral.viewmodels.export.AccountRoleExportModel;

public interface AccountRoleService extends BaseService<AccountRole, String, AccountRoleModel, AccountRoleExportModel> {
    AccountRole findByAccountId(String accountid);
}
