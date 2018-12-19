package cz.senslog.provider.repository;

import cz.senslog.model.db.Observation;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by OK on 02-Nov-18.
 */
@RepositoryRestResource(collectionResourceRel = "observation", path = "observation")
public interface ObservationRepository extends MongoRepository<Observation, ObjectId> {

    List<Observation> findAllBySensorId(ObjectId sensorId, Pageable pageable);
    /* --- Collaborates --- */
}


