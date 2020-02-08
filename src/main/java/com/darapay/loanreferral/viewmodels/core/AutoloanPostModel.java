package com.darapay.loanreferral.viewmodels.core;

import com.darapay.loanreferral.viewmodels.AutoloanDetailModel;
import com.darapay.loanreferral.viewmodels.AutoloanModel;
import com.darapay.loanreferral.viewmodels.presentation.MainAddressDisplayDdlModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AutoloanPostModel {

    public AutoloanPostModel() {}

    public AutoloanPostModel(AutoloanModel autoloanModel, List<AutoloanDetailModel> autoloanDetailModelsList, List<MainAddressDisplayDdlModel> provincesModelList, List<MainAddressDisplayDdlModel> districtModelList, List<MainAddressDisplayDdlModel> communeModelList, List<MainAddressDisplayDdlModel> villageModelList, boolean tranStatus) {
        this.autoloanModel = autoloanModel;
        this.autoloanDetailModelsList = autoloanDetailModelsList;
        this.provincesModelList = provincesModelList;
        this.districtModelList = districtModelList;
        this.communeModelList = communeModelList;
        this.villageModelList = villageModelList;
        this.tranStatus=tranStatus;
    }

    private AutoloanModel autoloanModel;
    private List<AutoloanDetailModel> autoloanDetailModelsList;
    private List<MainAddressDisplayDdlModel> provincesModelList;
    private List<MainAddressDisplayDdlModel> districtModelList;
    private List<MainAddressDisplayDdlModel> communeModelList;
    private List<MainAddressDisplayDdlModel> villageModelList;
    private boolean tranStatus;
}
