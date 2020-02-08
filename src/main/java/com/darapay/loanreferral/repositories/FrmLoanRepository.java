package com.darapay.loanreferral.repositories;

import com.darapay.loanreferral.models.FrmLoan;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin()
public interface FrmLoanRepository extends ExtendedRepository<FrmLoan, String> {
    Integer countAllByLoanstAndEnable(String loanst, Boolean enable);
    Integer countAllByEnable(Boolean enable);
}
