/*
 * The MIT License
 *
 * Copyright 2017 Pivotal Software, Inc..
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.wildbeeslabs.rest.publication.repository;

import com.wildbeeslabs.api.rest.common.repository.BaseRepository;
import com.wildbeeslabs.rest.publication.model.Article;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

/**
 *
 * Article REST Application storage repository
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 */
@Repository
public interface ArticleRepository<T extends Article> extends MongoBaseRepository<T, String>, BaseRepository<T> {

    /**
     * Get article entity by name (case insensitive)
     *
     * @param name - article name
     * @return article entity
     */
    @Async
    CompletableFuture<Optional<T>> findByNameIgnoreCase(final String name);

    /**
     * Get list of article entities by name pattern
     *
     * @param name - article name pattern
     * @return list of article entities
     */
    @Async
    CompletableFuture<List<? extends T>> findByNameLike(final String name);

    /**
     * Get list of article entities by category
     *
     * @param category - article category
     * @return list of article entities
     */
    @Async
    CompletableFuture<List<? extends T>> findByCategory(final String category);
}
