package com.darapay.loanreferral.services.impl;

import com.darapay.loanreferral.models.LogMasters;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.repositories.LogMastersRepository;
import com.darapay.loanreferral.services.LogMastersService;
import com.darapay.loanreferral.viewmodels.AdvanceModel;
import com.darapay.loanreferral.viewmodels.LogMastersModel;
import com.darapay.loanreferral.viewmodels.export.LogMastersExportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Service
public class LogMastersServiceImpl extends BaseServiceImpl<LogMasters, String, LogMastersModel, LogMastersExportModel> implements LogMastersService {
    @Autowired
    private LogMastersRepository logMastersRepository;

    @Override
    protected ExtendedRepository<LogMasters, String> getBaseRepository() {
        return logMastersRepository;
    }

    @Override
    public List<LogMasters> findAll() {
        return logMastersRepository.findAll();
    }

    @Override
    Class aliasModel() {
        return LogMastersModel.class;
    }

    @Override
    Class aliasExportModel() {
        return LogMastersExportModel.class;
    }
}
