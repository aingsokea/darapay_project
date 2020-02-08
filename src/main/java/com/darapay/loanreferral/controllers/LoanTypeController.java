package com.darapay.loanreferral.controllers;

import com.darapay.loanreferral.models.LoanType;
import com.darapay.loanreferral.services.BaseService;
import com.darapay.loanreferral.services.LoanTypeService;
import com.darapay.loanreferral.viewmodels.LoanTypeModel;
import com.darapay.loanreferral.viewmodels.export.LoanTypeExportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loantype")
public class LoanTypeController extends EntityApiController<LoanType, String, LoanTypeModel, LoanTypeExportModel> {

    @Autowired
    private LoanTypeService loanTypeService;

    @Override
    protected BaseService<LoanType, String, LoanTypeModel, LoanTypeExportModel> getBaseService() {
        return loanTypeService;
    }

    @Override
    protected LoanType NewEntity() {
        return new LoanType();
    }

    @Override
    protected LoanTypeModel NewModel() {
        return new LoanTypeModel();
    }
}
