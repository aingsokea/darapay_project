package com.darapay.loanreferral.viewmodels;

import com.darapay.loanreferral.annotations.ModelBinding;
import com.darapay.loanreferral.models.Navigation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NavigationModel extends ModelFactory<Navigation, String> implements IModelFactory<Navigation, String> {

    @ModelBinding()
    @NotNull(message = "Role id cannot be null.")
    private String roleid;

    @ModelBinding()
    private String key;

    @ModelBinding()
    private String title;

    @ModelBinding()
    private String translate;

    @ModelBinding()
    private String type;

    @ModelBinding()
    private String icon;

    @ModelBinding()
    private String url;

    @ModelBinding()
    private String taborder;
}
