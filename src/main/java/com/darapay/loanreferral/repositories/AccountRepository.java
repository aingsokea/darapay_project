package com.darapay.loanreferral.repositories;

import com.darapay.loanreferral.models.Account;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;

@RepositoryRestResource
@CrossOrigin()
public interface AccountRepository extends ExtendedRepository<Account, String> {
    Optional<Account> findByUsername(final String username);
    Optional<Account> findAccountByUsernameAndEnable(final String username, Boolean enable);
    Boolean existsByUsername(String username);
    Boolean existsByPhone1(String phone1);
    List<Account> findByIdentityid(String identityif);
    List<Account> findByIdAndEnable(String id,boolean enable);
    List<Account> findByIdentityidAndMfiid(String identityif,String mfi);
}
