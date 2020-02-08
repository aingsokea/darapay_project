package com.darapay.loanreferral.controllers;

import com.darapay.loanreferral.models.FrmApp;
import com.darapay.loanreferral.services.BaseService;
import com.darapay.loanreferral.services.EmailService;
import com.darapay.loanreferral.services.FrmAppService;
import com.darapay.loanreferral.viewmodels.FrmAppModel;
import com.darapay.loanreferral.viewmodels.export.FrmAppExportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/frmapp")
public class FrmAppController extends EntityApiController<FrmApp, String, FrmAppModel, FrmAppExportModel> {

    @Autowired
    private FrmAppService frmAppService;

    @Override
    protected BaseService<FrmApp, String, FrmAppModel, FrmAppExportModel> getBaseService() {
        return frmAppService;
    }

    @Override
    protected FrmApp NewEntity() {
        return new FrmApp();
    }

    @Override
    protected FrmAppModel NewModel() {
        return new FrmAppModel();
    }

    @Override
    public ResponseEntity<?> Post(@Valid @RequestBody FrmAppModel frmAppModel) {
        return new ResponseEntity<Object>(frmAppService.saveWithEmail(New(frmAppModel)), HttpStatus.OK);
    }
}
