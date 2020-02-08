package com.darapay.loanreferral.controllers;

import com.darapay.loanreferral.models.Mfi;
import com.darapay.loanreferral.services.BaseService;
import com.darapay.loanreferral.services.MfiService;
import com.darapay.loanreferral.viewmodels.MfiModel;
import com.darapay.loanreferral.viewmodels.export.MfiExportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mfi")
public class MfiController extends EntityApiController<Mfi, String, MfiModel, MfiExportModel> {

    @Autowired
    private MfiService mfiService;

    @Override
    protected BaseService<Mfi, String, MfiModel, MfiExportModel> getBaseService() {
        return mfiService;
    }

    @Override
    protected Mfi NewEntity() {
        return new Mfi();
    }

    @Override
    protected MfiModel NewModel() {
        return new MfiModel();
    }
}
