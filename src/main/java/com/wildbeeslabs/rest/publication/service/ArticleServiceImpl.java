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
package com.wildbeeslabs.rest.publication.service;

import com.wildbeeslabs.rest.publication.model.Article;
import com.wildbeeslabs.rest.publication.repository.ArticleRepository;
import com.wildbeeslabs.rest.publication.service.interfaces.IArticleService;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Article REST Application Service implementation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 */
@Service("articleService")
@Transactional
public class ArticleServiceImpl<T extends Article> extends MongoBaseServiceImpl<T, String, ArticleRepository<T>> implements IArticleService<T> {

    @Override
    public Optional<T> findByNameIgnoreCase(final String name) {
        final CompletableFuture<Optional<T>> futureItem = getRepository().findByNameIgnoreCase(name);//.thenApply(this::doSomethingWithArticles);
        Optional<T> item = Optional.empty();
        try {
            while (!futureItem.isDone()) {
                //LOGGER.info("Waiting for the CompletableFuture < findByNameIgnoreCase > to finish...");
                TimeUnit.MILLISECONDS.sleep(500);
            }
            item = futureItem.get();
        } catch (InterruptedException | ExecutionException ex) {
            //LOGGER.error("ERROR: execution cannot be performed for the CompletableFuture < findByNameIgnoreCase > with message {}", ex);
        }
        return item;
    }

    @Override
    public List<? extends T> findByCategory(final String category) {
        final CompletableFuture<List<? extends T>> futureItem = getRepository().findByCategory(category);
        List<? extends T> items = new ArrayList();
        try {
            while (!futureItem.isDone()) {
                //LOGGER.info("Waiting for the CompletableFuture < findByCategory > to finish...");
                TimeUnit.MILLISECONDS.sleep(500);
            }
            items = futureItem.get();
        } catch (InterruptedException | ExecutionException ex) {
            //LOGGER.error("ERROR: execution cannot be performed for the CompletableFuture < findByCategory > with message {}", ex);
        }
        return items;
    }

    @Override
    public List<? extends T> findByNameLike(final String namePattern) {
        final CompletableFuture<List<? extends T>> futureItem = getRepository().findByNameLike(namePattern);
        List<? extends T> items = new ArrayList();
        try {
            while (!futureItem.isDone()) {
                //LOGGER.info("Waiting for the CompletableFuture < findEntityByNameIgnoreCase > to finish...");
                TimeUnit.MILLISECONDS.sleep(500);
            }
            items = futureItem.get();
        } catch (InterruptedException | ExecutionException ex) {
            //LOGGER.error("ERROR: execution cannot be performed for the CompletableFuture < findEntityByNameIgnoreCase > with message {}", ex);
        }
        return items;
    }

//    @Override
//    public List<? extends T> findByCategoryId(final Long categoryId) {
//        //return getRepository().findByCategoryId(categoryId);
//        return null;
//    }
}
