package it.univaq.sose.watched_film.service;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.univaq.sose.watched_film.business.WatchedBusiness;
import it.univaq.sose.watched_film.model.Watched;
import org.apache.cxf.ext.logging.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Logging
@Service
@Api("/watched")
public class WatchedServiceImpl implements WatchedService {
    @Autowired
    WatchedBusiness watchedBusiness;

    @Override
    public List<Watched> getWatchedByUserId(Long userId, int deep) {
        return watchedBusiness.getWatchedFilmByUser(userId, deep);
    }

    @Override
    public String ping() {
        return "Pong";
    }

    @Override
    public Watched addWatched(@Valid Watched watched) {
        // TODO Do we need duplicate watched logic?
        return watchedBusiness.save(watched);
    }

    @Override
    public void deleteWatchedById(Long id) {
        watchedBusiness.deleteById(id);
    }
}
