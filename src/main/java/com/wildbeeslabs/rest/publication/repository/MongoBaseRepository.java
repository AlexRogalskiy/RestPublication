package com.wildbeeslabs.rest.publication.repository;

import com.wildbeeslabs.api.rest.common.model.IBaseEntity;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.scheduling.annotation.Async;

/**
 *
 * Mongo Base REST Application storage repository
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface MongoBaseRepository<T extends IBaseEntity, ID extends Serializable> extends MongoRepository<T, ID> {

    /**
     * Get list of item entities as stream
     *
     * @return list of item entities as stream
     */
    @Async
    @Query("SELECT e FROM #{#entityName}")
    CompletableFuture<Stream<T>> streamAll();
}
