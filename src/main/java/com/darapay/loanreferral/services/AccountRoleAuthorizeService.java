package com.darapay.loanreferral.services;

import com.darapay.loanreferral.models.AccountRoleAuthorize;
import com.darapay.loanreferral.viewmodels.AccountRoleAuthorizeModel;
import com.darapay.loanreferral.viewmodels.export.AccountRoleAuthorizeExportModel;

import java.util.List;

public interface AccountRoleAuthorizeService extends BaseService<AccountRoleAuthorize, String, AccountRoleAuthorizeModel, AccountRoleAuthorizeExportModel> {
    List<AccountRoleAuthorizeModel> getAllAccountRoleAuthorizeByUsername(String username, boolean enable);
}
