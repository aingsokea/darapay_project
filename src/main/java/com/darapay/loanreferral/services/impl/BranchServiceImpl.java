package com.darapay.loanreferral.services.impl;

import com.darapay.loanreferral.models.Branch;
import com.darapay.loanreferral.repositories.BranchRepository;
import com.darapay.loanreferral.repositories.ExtendedRepository;
import com.darapay.loanreferral.services.BranchService;
import com.darapay.loanreferral.viewmodels.BranchModel;
import com.darapay.loanreferral.viewmodels.export.BranchExportModel;
import com.darapay.loanreferral.viewmodels.presentation.BranchDisPlayDdkModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
@Service
public class BranchServiceImpl extends BaseServiceImpl<Branch, String, BranchModel, BranchExportModel> implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    protected ExtendedRepository<Branch, String> getBaseRepository() {
        return branchRepository;
    }

    @Override
    public List<Branch> findAll() {
        return branchRepository.findAll();
    }

    @Override
    Class aliasModel() {
        return BranchModel.class;
    }

    @Override
    Class aliasExportModel() {
        return BranchExportModel.class;
    }

    @Override
    public List<BranchDisPlayDdkModel> getAllByMfiidOrderByCode(String mfiid, Boolean enable) {
        List<Branch> brns = branchRepository.getAllByMfiidAndEnableOrderByCodeAsc(mfiid, enable);
        List<BranchDisPlayDdkModel> bdpdm = new ArrayList<>();
        brns.forEach(b -> {
            BranchDisPlayDdkModel bdp = new BranchDisPlayDdkModel(b.getId(), b.getName(), b.getCode(),b.getIndex());
            bdpdm.add(bdp);
        });
        return bdpdm;
    }

    @Override
    public List<BranchDisPlayDdkModel> getAllByMfiidOrderByindex(String mfiid, Boolean enable) {
        List<Branch> brns = branchRepository.getAllByMfiidAndEnableOrderByCodeAsc(mfiid, enable);
        List<BranchDisPlayDdkModel> bdpdm = new ArrayList<>();
        brns.forEach(b -> {
            BranchDisPlayDdkModel bdp = new BranchDisPlayDdkModel(b.getId(), b.getName(), b.getCode(),b.getIndex());
            bdpdm.add(bdp);
        });
        return bdpdm;
    }
}
