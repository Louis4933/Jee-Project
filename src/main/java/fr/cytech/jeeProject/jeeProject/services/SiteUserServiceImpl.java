package fr.cytech.jeeProject.jeeProject.services;

import fr.cytech.jeeProject.jeeProject.beans.SiteUser;
import fr.cytech.jeeProject.jeeProject.dao.SiteUserDao;
import fr.cytech.jeeProject.jeeProject.services.interfaces.SiteUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteUserServiceImpl implements SiteUserService {
    private final SiteUserDao siteUserDao;

    public SiteUserServiceImpl(SiteUserDao siteUserDao) {
        this.siteUserDao = siteUserDao;
    }

    @Override
    public SiteUser saveSiteUser(SiteUser siteUser) {
        return siteUserDao.save(siteUser);
    }

    @Override
    public SiteUser getSiteUserById(Long siteUserId) {
        return siteUserDao.findById(siteUserId).orElse(null);
    }

    @Override
    public List<SiteUser> getSiteUserList() {
        return (List<SiteUser>) siteUserDao.findAll();
    }

    @Override
    public SiteUser updateSiteUser(SiteUser siteUser, Long siteUserId) {

        SiteUser siteUserDb = siteUserDao.findById(siteUserId).orElse(null);
        if(siteUserDb == null) return siteUser;

        return siteUserDao.save(siteUser);
    }

    @Override
    public void deleteSiteUserById(Long siteUserId) {
        siteUserDao.deleteById(siteUserId);
    }
}
