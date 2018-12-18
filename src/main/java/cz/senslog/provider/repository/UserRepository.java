package cz.senslog.provider.repository;

import cz.senslog.model.db.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByNameEquals(String name);

    /* --- Collaborates --- */
}


