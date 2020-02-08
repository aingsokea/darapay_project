package com.darapay.loanreferral.services.impl;

import com.darapay.loanreferral.models.AutoloanDetail;
import com.darapay.loanreferral.repositories.AutoloanDetailRepository;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.services.AutoloanDetailService;
import com.darapay.loanreferral.viewmodels.AutoloanDetailModel;
import com.darapay.loanreferral.viewmodels.export.AutoloanDetailExportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class AutoloanDetailServiceImpl extends BaseServiceImpl<AutoloanDetail, String, AutoloanDetailModel, AutoloanDetailExportModel> implements AutoloanDetailService {

    @Autowired
    private AutoloanDetailRepository autoloanDetailRepository;

    @Override
    protected ExtendedRepository<AutoloanDetail, String> getBaseRepository() {
        return autoloanDetailRepository;
    }

    @Override
    public List<AutoloanDetail> findAll() {
        return autoloanDetailRepository.findAll();
    }

    @Override
    Class aliasModel() {
        return AutoloanDetailModel.class;
    }

    @Override
    Class aliasExportModel() {
        return AutoloanDetailExportModel.class;
    }

    @Override
    public List<AutoloanDetailModel> findAllByAutoloanId(String autoloanid, Boolean enable) {
        List<AutoloanDetail> ald = autoloanDetailRepository.findAllByAutoloanidAndEnable(autoloanid, enable);
        List<AutoloanDetailModel> aldm = new ArrayList<>();
        ald.forEach(a -> {
            AutoloanDetailModel adm = new AutoloanDetailModel(a.getId(), a.getAutoloanid(), a.getImage_url(), a.getImage_type(),
                    a.getCreatedby(), a.getCreateddate());
            aldm.add(adm);
        });
        return aldm;
    }
}
