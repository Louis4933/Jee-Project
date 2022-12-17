package fr.cytech.jeeProject.jeeProject.dao;

import fr.cytech.jeeProject.jeeProject.beans.SiteUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SiteUserDao extends CrudRepository<SiteUser, Long> {

    @Query(value = "SELECT u FROM SiteUser u WHERE u.cookieCode = ?1")
    SiteUser getSiteUserByCookie(String cookieId);

    @Query(value = "SELECT u FROM SiteUser u WHERE u.email = ?1 AND u.password = ?2")
    SiteUser validateUser(String email, String password);

}
