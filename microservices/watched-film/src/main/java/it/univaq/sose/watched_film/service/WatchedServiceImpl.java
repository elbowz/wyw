package it.univaq.sose.watched_film.service;


import io.swagger.annotations.Api;
import it.univaq.sose.watched_film.business.WatchedBusiness;
import it.univaq.sose.watched_film.model.Watched;
import org.apache.cxf.ext.logging.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Logging
@Service
@Api("/watched")
public class WatchedServiceImpl implements WatchedService {
    @Autowired
    WatchedBusiness watchedBusiness;

    @Override
    public List<Watched> getWatchedById(Long id) {
        return watchedBusiness.getWatchedFilmByUser(id);
    }

    @Override
    public List<Watched> getAllWatched() {
        return watchedBusiness.getAll();
    }

    @Override
    public String ping() {
        return "Pong";
    }
}
