package com.darapay.loanreferral.security.config;

import com.darapay.loanreferral.models.*;
import com.darapay.loanreferral.repositories.AccountRepository;
import com.darapay.loanreferral.repositories.AccountRoleRepository;
import com.darapay.loanreferral.repositories.RoleRepository;
import com.darapay.loanreferral.services.NavigationService;
import com.darapay.loanreferral.services.SettingService;
import com.darapay.loanreferral.viewmodels.presentation.NavigationDisplayModel;
import com.darapay.loanreferral.viewmodels.presentation.SettingDisplayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SettingService settingService;

    @Autowired
    private NavigationService navigationService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Account account = accountRepository.findByUsername(authentication.getName()).get();
        AccountRole accountRole = accountRoleRepository.findByAccountid(account.getId());
        List<SettingDisplayModel> sdm = settingService.getAllSettingsByRoleIdAndEnable(accountRole.getRoleid(), true);
        List<NavigationDisplayModel> dnm = navigationService.findNavigationByRoleIdAndEnable(accountRole.getRoleid(), true);

        // Add custom file to Access Token
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("firstname", account.getFirstname());
        additionalInfo.put("lastname", account.getLastname());
        additionalInfo.put("username", account.getUsername());
        additionalInfo.put("email", account.getEmail());
        additionalInfo.put("mfiid", account.getMfiid());
        additionalInfo.put("usertype", account.getUsertype());
        additionalInfo.put("accountid", account.getId());
        additionalInfo.put("identityid", account.getIdentityid());
        additionalInfo.put("usersetting", sdm);
        additionalInfo.put("navs", dnm);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(
                additionalInfo);
        return accessToken;
    }
}
