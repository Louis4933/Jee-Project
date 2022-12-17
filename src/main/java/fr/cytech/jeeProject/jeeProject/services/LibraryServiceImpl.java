package fr.cytech.jeeProject.jeeProject.services;

import fr.cytech.jeeProject.jeeProject.beans.Library;
import fr.cytech.jeeProject.jeeProject.dao.LibraryDao;
import fr.cytech.jeeProject.jeeProject.services.interfaces.LibraryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final LibraryDao libraryDao;

    public LibraryServiceImpl(LibraryDao libraryDao) {
        this.libraryDao = libraryDao;
    }

    @Override
    public Library saveLibrary(Library library) {
        return libraryDao.save(library);
    }

    @Override
    public Library getLibraryById(Long libraryId) {
        return libraryDao.findById(libraryId).orElse(null);
    }

    @Override
    public Library getLibraryByUrlShort(String urlShort) {
        return libraryDao.getLibraryByUrlShort(urlShort);
    }

    @Override
    public List<Library> getLibraries() {
        return (List<Library>) libraryDao.findAll();
    }

    @Override
    public Library updateLibrary(Library library, Long libraryId) {

        Library libraryDb = libraryDao.findById(libraryId).orElse(null);
        if(libraryDb == null) return library;

        return libraryDao.save(library);
    }

    @Override
    public void deleteLibraryById(Long libraryId) {
        libraryDao.deleteById(libraryId);
    }
}
