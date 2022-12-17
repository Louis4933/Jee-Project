package fr.cytech.jeeProject.jeeProject.dao;

import fr.cytech.jeeProject.jeeProject.beans.Library;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LibraryDao extends CrudRepository<Library, Long> {

    @Query(value = "SELECT u FROM Library u WHERE u.urlShort = ?1")
    Library getLibraryByUrlShort(String urlShort);

}
