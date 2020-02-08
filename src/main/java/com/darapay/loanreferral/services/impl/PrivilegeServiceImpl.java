package com.darapay.loanreferral.services.impl;

import com.darapay.loanreferral.models.Privilege;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.repositories.PrivilegeRepository;
import com.darapay.loanreferral.services.PrivilegeService;
import com.darapay.loanreferral.viewmodels.PrivilegeModel;
import com.darapay.loanreferral.viewmodels.export.PrivilegeExportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class PrivilegeServiceImpl extends BaseServiceImpl<Privilege, String, PrivilegeModel, PrivilegeExportModel> implements PrivilegeService {
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    protected ExtendedRepository<Privilege, String> getBaseRepository() {
        return privilegeRepository;
    }

    @Override
    public List<Privilege> findAll() {
        return privilegeRepository.findAll();
    }

    @Override
    Class aliasModel() {
        return PrivilegeModel.class;
    }

    @Override
    Class aliasExportModel() {
        return PrivilegeExportModel.class;
    }
}
