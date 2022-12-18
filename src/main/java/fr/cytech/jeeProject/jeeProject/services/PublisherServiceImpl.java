package fr.cytech.jeeProject.jeeProject.services;

import fr.cytech.jeeProject.jeeProject.beans.Publisher;
import fr.cytech.jeeProject.jeeProject.dao.PublisherDao;
import fr.cytech.jeeProject.jeeProject.services.interfaces.PublisherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherDao publisherDao;

    public PublisherServiceImpl(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @Override
    public Publisher savePublisher(Publisher publisher) {
        return publisherDao.save(publisher);
    }

    @Override
    public Publisher getPublisherById(Long publisherId) {
        return publisherDao.findById(publisherId).orElse(null);
    }

    @Override
    public Publisher getPublisherByName(String publisherName) {
        return publisherDao.getPublisherByName(publisherName);
    }

    @Override
    public List<Publisher> getPublisherList() {
        return (List<Publisher>) publisherDao.findAll();
    }

    @Override
    public Publisher updatePublisher(Publisher publisher, Long publisherId) {

        Publisher publisherDb = publisherDao.findById(publisherId).orElse(null);
        if(publisherDb == null) return publisher;

        return publisherDao.save(publisher);
    }

    @Override
    public void deletePublisherById(Long publisherId) {
        publisherDao.deleteById(publisherId);
    }
}
