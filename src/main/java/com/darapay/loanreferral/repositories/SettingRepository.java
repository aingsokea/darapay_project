package com.darapay.loanreferral.repositories;

import com.darapay.loanreferral.models.Setting;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin()
public interface SettingRepository extends ExtendedRepository<Setting, String> {
    List<Setting> findAllByRoleidAndEnable(String roleid, Boolean enable);
}
