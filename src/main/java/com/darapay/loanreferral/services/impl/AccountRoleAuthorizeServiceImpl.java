package com.darapay.loanreferral.services.impl;

import com.darapay.loanreferral.models.AccountRoleAuthorize;
import com.darapay.loanreferral.repositories.AccountRoleAuthorizeRepository;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.services.AccountRoleAuthorizeService;
import com.darapay.loanreferral.viewmodels.AccountRoleAuthorizeModel;
import com.darapay.loanreferral.viewmodels.export.AccountRoleAuthorizeExportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class AccountRoleAuthorizeServiceImpl extends BaseServiceImpl<AccountRoleAuthorize, String, AccountRoleAuthorizeModel, AccountRoleAuthorizeExportModel> implements AccountRoleAuthorizeService {

    @Autowired
    private AccountRoleAuthorizeRepository accountRoleAuthorizeRepository;

    @Override
    protected ExtendedRepository<AccountRoleAuthorize, String> getBaseRepository() {
        return accountRoleAuthorizeRepository;
    }

    @Override
    public List<AccountRoleAuthorize> findAll() {
        return accountRoleAuthorizeRepository.findAll();
    }

    @Override
    Class aliasModel() {
        return AccountRoleAuthorizeModel.class;
    }

    @Override
    Class aliasExportModel() {
        return AccountRoleAuthorizeExportModel.class;
    }

    @Override
    public List<AccountRoleAuthorizeModel> getAllAccountRoleAuthorizeByUsername(String username, boolean enable) {
        List<AccountRoleAuthorizeModel> aram = new ArrayList<>();
        List<AccountRoleAuthorize> ara = accountRoleAuthorizeRepository.findAllByUsernameAndEnable(username, enable);
        ara.forEach(a -> {
            AccountRoleAuthorizeModel ar = new AccountRoleAuthorizeModel(a.getAccountid(), a.getUsername(), a.getRoleid());
            aram.add(ar);
        });
        return aram;
    }
}
