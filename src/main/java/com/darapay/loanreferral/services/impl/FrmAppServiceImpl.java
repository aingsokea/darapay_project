package com.darapay.loanreferral.services.impl;

import com.darapay.loanreferral.models.FrmApp;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.repositories.FrmAppRepository;
import com.darapay.loanreferral.services.EmailService;
import com.darapay.loanreferral.services.FrmAppService;
import com.darapay.loanreferral.viewmodels.FrmAppModel;
import com.darapay.loanreferral.viewmodels.export.FrmAppExportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class FrmAppServiceImpl extends BaseServiceImpl<FrmApp, String, FrmAppModel, FrmAppExportModel> implements FrmAppService {

    @Autowired
    private FrmAppRepository frmAppRepository;

    @Autowired
    private EmailService emailService;

    @Override
    protected ExtendedRepository<FrmApp, String> getBaseRepository() {
        return frmAppRepository;
    }

    @Override
    public List<FrmApp> findAll() {
        return frmAppRepository.findAll();
    }

    @Override
    Class aliasModel() {
        return FrmAppModel.class;
    }

    @Override
    Class aliasExportModel() {
        return FrmAppExportModel.class;
    }

    @Override
    public FrmApp saveWithEmail(FrmApp entity) {
        FrmApp frmapp = save(entity);
        String[] to = {"kimmeng.soueng@darapay.com.kh"};
        String htmltext = "<h1>Dear DaraPay Team</h1></br><p>I have been upload Auto Loan which Id is " + frmapp.getId() + "</p><p>Please check my request and processed it as soon as possible.</p>";
        emailService.sendMessageWithHtmlTemplate("kimmeng.soueng@outlook.com",to, "Testing Send Mail Using Java Spring", "it@darapay.com.kh" ,htmltext);
        return frmapp;
    }
}
