package fr.cytech.jeeProject.jeeProject.services.interfaces;

import fr.cytech.jeeProject.jeeProject.beans.Library;

import java.util.List;

public interface LibraryService {

    Library saveLibrary(Library library);
    Library getLibraryById(Long libraryId);
    Library getLibraryByUrlShort(String urlShort);
    List<Library> getLibraries();
    Library updateLibrary(Library library, Long libraryId);
    void deleteLibraryById(Long libraryId);

}
