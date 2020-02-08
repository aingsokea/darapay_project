package com.darapay.loanreferral.services.impl;

import com.darapay.loanreferral.models.Mfi;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.repositories.MfiRepository;
import com.darapay.loanreferral.services.BranchService;
import com.darapay.loanreferral.services.LoanTypeService;
import com.darapay.loanreferral.services.MfiService;
import com.darapay.loanreferral.viewmodels.MfiModel;
import com.darapay.loanreferral.viewmodels.export.MfiExportModel;
import com.darapay.loanreferral.viewmodels.presentation.BranchDisPlayDdkModel;
import com.darapay.loanreferral.viewmodels.presentation.LoanTypeDisplayModel;
import com.darapay.loanreferral.viewmodels.presentation.MfiDisplayModel;
import com.darapay.loanreferral.viewmodels.presentation.MfiDisplayOnloadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
@Service
public class MfiServiceImpl extends BaseServiceImpl<Mfi, String, MfiModel, MfiExportModel> implements MfiService {

    @Autowired
    private MfiRepository mfiRepository;

    @Autowired
    private LoanTypeService loanTypeService;

    @Override
    protected ExtendedRepository<Mfi, String> getBaseRepository() {
        return mfiRepository;
    }

    @Override
    public List<Mfi> findAll() {
        return mfiRepository.findAll();
    }

    @Override
    Class aliasModel() {
        return MfiModel.class;
    }

    @Override
    Class aliasExportModel() {
        return MfiExportModel.class;
    }

    @Autowired
    private BranchService branchService;
    @Override
    public List<MfiDisplayOnloadModel> getAllMfiByEnableOrderByTabOrder(Boolean enable) {
        List<Mfi> mfis = mfiRepository.getAllByEnableOrderByTaborderAsc(enable);
        List<MfiDisplayOnloadModel> mdms = new ArrayList<>();
        mfis.forEach(m -> {
            List<LoanTypeDisplayModel> ltl = loanTypeService.getLoanTypeByMfiId(m.getId(), true);
            List<BranchDisPlayDdkModel> branches = branchService.getAllByMfiidOrderByCode(m.getId(), true);
            MfiDisplayOnloadModel md = new MfiDisplayOnloadModel(m.getId(), m.getName(), m.getPic(), m.getKey(), ltl,branches);
            mdms.add(md);
        });
        return mdms;
    }

    @Override
    public List<MfiDisplayModel> getAllMfiToAssignByEnableOrderByTabOrder(Boolean enable) {
        List<Mfi> mfis = mfiRepository.getAllByEnableOrderByTaborderAsc(enable);
        return getListMfi(mfis);
    }

    @Override
    public List<MfiDisplayModel> getAllMfiToAssignByEnableAndNameIsNotInOrderByTaborder(Boolean enable, String[] name) {
        List<Mfi> mfis = mfiRepository.getAllByEnableAndNameIsNotInOrderByTaborderAsc(enable, name);
        return getListMfi(mfis);
    }

    private List<MfiDisplayModel> getListMfi(List<Mfi> mfis) {
        List<MfiDisplayModel> mdms = new ArrayList<>();
        mfis.forEach(m -> {
            MfiDisplayModel md = new MfiDisplayModel(m.getId(), m.getName(), m.getPic(), m.getKey());
            mdms.add(md);
        });
        return mdms;
    }
}
