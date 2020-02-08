package com.darapay.loanreferral.controllers;

import com.darapay.loanreferral.models.Privilege;
import com.darapay.loanreferral.services.BaseService;
import com.darapay.loanreferral.services.PrivilegeService;
import com.darapay.loanreferral.viewmodels.PrivilegeModel;
import com.darapay.loanreferral.viewmodels.export.PrivilegeExportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/privilege")
public class PrivilegeController extends EntityApiController<Privilege, String, PrivilegeModel, PrivilegeExportModel> {
    @Autowired
    private PrivilegeService privilegeService;

    @Override
    protected BaseService<Privilege, String, PrivilegeModel, PrivilegeExportModel> getBaseService() {
        return privilegeService;
    }

    @Override
    protected Privilege NewEntity() {
        return new Privilege();
    }

    @Override
    protected PrivilegeModel NewModel() {
        return new PrivilegeModel();
    }
}
