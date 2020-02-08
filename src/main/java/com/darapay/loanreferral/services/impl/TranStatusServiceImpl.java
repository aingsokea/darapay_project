package com.darapay.loanreferral.services.impl;

import com.darapay.loanreferral.models.Account;
import com.darapay.loanreferral.models.Configure;
import com.darapay.loanreferral.models.TranStatus;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.repositories.TranStatusRepository;
import com.darapay.loanreferral.services.AccountService;
import com.darapay.loanreferral.services.ConfigureService;
import com.darapay.loanreferral.services.TranStatusService;
import com.darapay.loanreferral.viewmodels.TranStatusModel;
import com.darapay.loanreferral.viewmodels.export.TranStatusExportModel;
import com.darapay.loanreferral.viewmodels.presentation.TranStatusDisplayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
@Service
public class TranStatusServiceImpl extends BaseServiceImpl<TranStatus, String, TranStatusModel, TranStatusExportModel> implements TranStatusService {

    @Autowired
    private TranStatusRepository tranStatusRepository;

    @Autowired
    private ConfigureService configureService;

    @Autowired
    private AccountService accountService;

    @Override
    protected ExtendedRepository<TranStatus, String> getBaseRepository() {
        return tranStatusRepository;
    }

    @Override
    public List<TranStatus> findAll() {
        return tranStatusRepository.findAll();
    }

    @Override
    Class aliasModel() {
        return TranStatusModel.class;
    }

    @Override
    Class aliasExportModel() {
        return TranStatusExportModel.class;
    }

    @Override
    public List<TranStatusDisplayModel> getAllByFrmLoanIdOrderByCreatedDate(String frmloanid, Boolean enable) {
        List<TranStatus> ts = tranStatusRepository.findAllByFrmloanidAndEnableOrderByCreateddateDesc(frmloanid, enable);
        List<TranStatusDisplayModel> tsdm = new ArrayList<>();
        ts.forEach(t -> {
            Configure fromstatus = configureService.find(t.getFromst());
            Configure tostatus = configureService.find(t.getTost());
            Account acc = accountService.findAccountByUsernameAndEnable(t.getCreatedby(), true);
            TranStatusDisplayModel tsm = new TranStatusDisplayModel(fromstatus, tostatus, t.getComment(),
                    acc.getPhone1(), acc.getFirstname() + " " + acc.getLastname(), t.getCreateddate());
            tsdm.add(tsm);
        });
        return tsdm;
    }
}
