package com.darapay.loanreferral.repositories;

import com.darapay.loanreferral.models.Role;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin()
public interface RoleRepository extends ExtendedRepository<Role, String> {
    List<Role> getAllByEnable(boolean enable);
    List<Role> findAllByIdAndEnable(String id, boolean enable);
}
