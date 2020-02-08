package com.darapay.loanreferral.repositories;

import com.darapay.loanreferral.models.AccountRoleAuthorize;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin()
public interface AccountRoleAuthorizeRepository extends ExtendedRepository<AccountRoleAuthorize, String>{
    List<AccountRoleAuthorize> findAllByUsernameAndEnable(String username, boolean enable);
}
