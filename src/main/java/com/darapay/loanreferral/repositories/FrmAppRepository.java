package com.darapay.loanreferral.repositories;

import com.darapay.loanreferral.models.FrmApp;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin()
public interface FrmAppRepository extends ExtendedRepository<FrmApp, String> {

}
