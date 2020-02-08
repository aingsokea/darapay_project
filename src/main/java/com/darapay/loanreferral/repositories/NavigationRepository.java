package com.darapay.loanreferral.repositories;

import com.darapay.loanreferral.models.Navigation;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin()
public interface NavigationRepository extends ExtendedRepository<Navigation, String> {
    List<Navigation> getAllByRoleidAndEnableOrderByTaborderAsc(String roleid, Boolean enable);
}
