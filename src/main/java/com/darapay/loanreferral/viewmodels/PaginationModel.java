package com.darapay.loanreferral.viewmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationModel {
    private String limit;
    private String offset;
    private String orderBy;
    private String orderDir;
}
