package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.viewmodels.core.AutoloanPostModel;
import com.darapay.loanreferral.viewmodels.presentation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AutoloanChangeStatusModel extends AutoloanPostModel {
    public AutoloanChangeStatusModel() {}

    public AutoloanChangeStatusModel(AutoloanModel autoloanModel,
                                     List<AutoloanDetailModel> autoloanDetailModelsList,
                                     List<MainAddressDisplayDdlModel> provinceModelList,
                                     List<MainAddressDisplayDdlModel> districtModelList,
                                     List<MainAddressDisplayDdlModel> communeModelList,
                                     List<MainAddressDisplayDdlModel> villageModelList,
                                     AccountAutoloanChangeStatusDisplayModel accountAutoloanChangeStatusDisplayModel,
                                     List<AutoloanStatusDisplayModel> autoloanStatusDisplayModelList,
                                     List<CurrencyDisplayModel> currencyDisplayModelList,
                                     List<TranStatusDisplayModel> tranStatusDisplayModels) {
        super(autoloanModel, autoloanDetailModelsList, provinceModelList, districtModelList, communeModelList, villageModelList,true);
        this.accountAutoloanChangeStatusDisplayModel = accountAutoloanChangeStatusDisplayModel;
        this.autoloanStatusDisplayModelList = autoloanStatusDisplayModelList;
        this.currencyDisplayModelList = currencyDisplayModelList;
        this.tranStatusDisplayModels=tranStatusDisplayModels;
    }



    private AccountAutoloanChangeStatusDisplayModel accountAutoloanChangeStatusDisplayModel;
    private List<AutoloanStatusDisplayModel> autoloanStatusDisplayModelList;
    private List<CurrencyDisplayModel> currencyDisplayModelList;
    private List<TranStatusDisplayModel> tranStatusDisplayModels;
}
