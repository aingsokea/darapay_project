package com.darapay.loanreferral.controllers;

import com.darapay.loanreferral.models.Branch;
import com.darapay.loanreferral.services.BaseService;
import com.darapay.loanreferral.services.BranchService;
import com.darapay.loanreferral.viewmodels.BranchModel;
import com.darapay.loanreferral.viewmodels.export.BranchExportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/branch")
public class BranchController extends EntityApiController<Branch, String, BranchModel, BranchExportModel> {

    @Autowired
    private BranchService branchService;

    @Override
    protected BaseService<Branch, String, BranchModel, BranchExportModel> getBaseService() {
        return branchService;
    }

    @Override
    protected Branch NewEntity() {
        return new Branch();
    }

    @Override
    protected BranchModel NewModel() {
        return new BranchModel();
    }
}
