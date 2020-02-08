package com.darapay.loanreferral.services;


import com.darapay.loanreferral.models.Branch;
import com.darapay.loanreferral.viewmodels.BranchModel;
import com.darapay.loanreferral.viewmodels.export.BranchExportModel;
import com.darapay.loanreferral.viewmodels.presentation.BranchDisPlayDdkModel;

import java.util.List;

public interface BranchService extends BaseService<Branch, String, BranchModel, BranchExportModel> {
    List<BranchDisPlayDdkModel> getAllByMfiidOrderByCode(String mfiid, Boolean enable);
    List<BranchDisPlayDdkModel> getAllByMfiidOrderByindex(String mfiid, Boolean enable);
}
