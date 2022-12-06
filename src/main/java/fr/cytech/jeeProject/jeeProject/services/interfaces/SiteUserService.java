package fr.cytech.jeeProject.jeeProject.services.interfaces;

import fr.cytech.jeeProject.jeeProject.beans.SiteUser;

import java.util.List;

public interface SiteUserService {

    SiteUser saveSiteUser(SiteUser siteUser);
    SiteUser getSiteUserById(Long siteUserId);
    List<SiteUser> getSiteUserList();
    SiteUser updateSiteUser(SiteUser siteUser, Long siteUserId);
    void deleteSiteUserById(Long siteUserId);

}
