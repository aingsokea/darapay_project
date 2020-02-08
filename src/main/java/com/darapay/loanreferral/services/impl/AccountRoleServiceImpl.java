package com.darapay.loanreferral.services.impl;


import com.darapay.loanreferral.models.AccountRole;
import com.darapay.loanreferral.repositories.AccountRoleRepository;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.services.AccountRoleService;
import com.darapay.loanreferral.viewmodels.AccountModel;
import com.darapay.loanreferral.viewmodels.AccountRoleModel;
import com.darapay.loanreferral.viewmodels.export.AccountRoleExportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Repository
public class AccountRoleServiceImpl extends BaseServiceImpl<AccountRole, String, AccountRoleModel, AccountRoleExportModel> implements AccountRoleService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Override
    protected ExtendedRepository<AccountRole, String> getBaseRepository() {
        return accountRoleRepository;
    }

    @Override
    public List<AccountRole> findAll() {
        return accountRoleRepository.findAll();
    }

    @Override
    Class aliasModel() {
        return AccountModel.class;
    }

    @Override
    Class aliasExportModel() {
        return AccountRoleExportModel.class;
    }

    @Override
    public AccountRole findByAccountId(String accountid) {
        return accountRoleRepository.findByAccountid(accountid);
    }
}
