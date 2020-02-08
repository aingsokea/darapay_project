package com.darapay.loanreferral.repositories;

import com.darapay.loanreferral.models.AccountRole;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin()
public interface AccountRoleRepository extends ExtendedRepository<AccountRole, String> {
    AccountRole findByAccountid(String accountid);
}
