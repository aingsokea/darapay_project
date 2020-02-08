package com.darapay.loanreferral.controllers;

import com.darapay.loanreferral.models.Role;
import com.darapay.loanreferral.services.BaseService;
import com.darapay.loanreferral.services.RoleService;
import com.darapay.loanreferral.viewmodels.RoleModel;
import com.darapay.loanreferral.viewmodels.export.RoleExportModel;
import com.darapay.loanreferral.viewmodels.presentation.RoleDisplayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/role")
public class RoleController extends EntityApiController<Role, String, RoleModel, RoleExportModel> {

    @Autowired
    private RoleService roleService;

    @Override
    protected BaseService<Role, String, RoleModel, RoleExportModel> getBaseService() {
        return roleService;
    }

    @Override
    protected Role NewEntity() {
        return new Role();
    }

    @Override
    protected RoleModel NewModel() {
        return new RoleModel();
    }

    @Override
    public ResponseEntity<?> Post(@Valid @RequestBody RoleModel roleModel) {
        RoleDisplayModel rdm = roleService.save(roleModel);
        return new ResponseEntity<>(rdm, HttpStatus.OK);
    }
}
