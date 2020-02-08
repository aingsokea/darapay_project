package com.darapay.loanreferral.services.impl;

import com.darapay.loanreferral.models.RolePrivilege;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.repositories.RolePrivilegeRepository;
import com.darapay.loanreferral.services.RolePrivilegeService;
import com.darapay.loanreferral.viewmodels.RolePrivilegeModel;
import com.darapay.loanreferral.viewmodels.export.RolePrivilegeExportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Service
public class RolePrivilegeServiceImpl extends BaseServiceImpl<RolePrivilege, String, RolePrivilegeModel, RolePrivilegeExportModel> implements RolePrivilegeService {

    @Autowired
    private RolePrivilegeRepository rolePrivilegeRepository;

    @Override
    protected ExtendedRepository<RolePrivilege, String> getBaseRepository() {
        return rolePrivilegeRepository;
    }

    @Override
    public List<RolePrivilege> findAll() {
        return rolePrivilegeRepository.findAll();
    }

    @Override
    Class aliasModel() {
        return RolePrivilegeModel.class;
    }

    @Override
    Class aliasExportModel() {
        return RolePrivilegeExportModel.class;
    }
}
