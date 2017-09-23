package com.wildbeeslabs.rest.publication.repository;

import com.wildbeeslabs.api.rest.common.model.IBaseEntity;
import com.wildbeeslabs.api.rest.common.repository.BaseRepository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * Mongo Base REST Application storage repository
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 */
@NoRepositoryBean
public interface MongoBaseRepository<T extends IBaseEntity> extends MongoRepository<T, String>, BaseRepository<T> {

}
