package com.darapay.loanreferral.services.impl;

import com.darapay.loanreferral.models.LoanType;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.repositories.LoanTypeRepository;
import com.darapay.loanreferral.services.LoanTypeService;
import com.darapay.loanreferral.viewmodels.LoanTypeModel;
import com.darapay.loanreferral.viewmodels.export.LoanTypeExportModel;
import com.darapay.loanreferral.viewmodels.presentation.LoanTypeDisplayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
@Service
public class LoanTypeServiceImpl extends BaseServiceImpl<LoanType, String, LoanTypeModel, LoanTypeExportModel> implements LoanTypeService {

    @Autowired
    private LoanTypeRepository loanTypeRepository;

    @Override
    protected ExtendedRepository<LoanType, String> getBaseRepository() {
        return loanTypeRepository;
    }

    @Override
    public List<LoanType> findAll() {
        return loanTypeRepository.findAll();
    }

    @Override
    Class aliasModel() {
        return LoanTypeModel.class;
    }

    @Override
    Class aliasExportModel() {
        return LoanTypeExportModel.class;
    }

    @Override
    public List<LoanTypeDisplayModel> getLoanTypeByMfiId(String mfiid, Boolean enable) {
        List<LoanType> lt = loanTypeRepository.getAllByMfiidAndEnableOrderByTaborderAsc(mfiid, enable);
        List<LoanTypeDisplayModel> ltdm = new ArrayList<>();
        lt.forEach(l -> {
            LoanTypeDisplayModel ltdmm = new LoanTypeDisplayModel(l.getId(), l.getName(), l.getNamekh());
            ltdm.add(ltdmm);
        });
        return ltdm;
    }
}
