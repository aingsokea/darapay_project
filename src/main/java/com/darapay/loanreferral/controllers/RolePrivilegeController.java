package com.darapay.loanreferral.controllers;

import com.darapay.loanreferral.models.RolePrivilege;
import com.darapay.loanreferral.services.BaseService;
import com.darapay.loanreferral.services.RolePrivilegeService;
import com.darapay.loanreferral.viewmodels.RolePrivilegeModel;
import com.darapay.loanreferral.viewmodels.export.RolePrivilegeExportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roleprivilege")
public class RolePrivilegeController extends EntityApiController<RolePrivilege, String, RolePrivilegeModel, RolePrivilegeExportModel> {

    @Autowired
    private RolePrivilegeService rolePrivilegeService;

    @Override
    protected BaseService<RolePrivilege, String, RolePrivilegeModel, RolePrivilegeExportModel> getBaseService() {
        return rolePrivilegeService;
    }

    @Override
    protected RolePrivilege NewEntity() {
        return new RolePrivilege();
    }

    @Override
    protected RolePrivilegeModel NewModel() {
        return new RolePrivilegeModel();
    }
}
