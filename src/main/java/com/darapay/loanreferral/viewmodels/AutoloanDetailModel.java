package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.models.AutoloanDetail;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class AutoloanDetailModel extends ModelFactory<AutoloanDetail, String> implements IModelFactory<AutoloanDetail, String> {

    public AutoloanDetailModel() {}

    public AutoloanDetailModel(String id, String autoloanid, @NotNull(message = "Image Url can not be null.") String image_url, @NotNull(message = "Image Type can not be null.") String image_type, @NotNull(message = "Created By can not be null.") String createdby, @NotNull(message = "Created Date can not be null.") Date createddate) {
        this.id = id;
        this.autoloanid = autoloanid;
        this.image_url = image_url;
        this.image_type = image_type;
        this.createdby = createdby;
        this.createddate = createddate;
    }

    @ModelBinding()
    private String autoloanid;

    @ModelBinding()
    @NotNull(message = "Image Url can not be null.")
    private String image_url;

    @ModelBinding()
    @NotNull(message = "Image Type can not be null.")
    private String image_type;

    @ModelBinding()
    @NotNull(message = "Created By can not be null.")
    private String createdby;

    @ModelBinding()
    @NotNull(message = "Created Date can not be null.")
    private Date createddate;
}
