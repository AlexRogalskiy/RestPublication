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
package com.wildbeeslabs.rest.publication.controller.proxy;

import com.wildbeeslabs.api.rest.common.controller.proxy.ABaseProxyController;
import com.wildbeeslabs.api.rest.common.exception.EmptyContentException;
import com.wildbeeslabs.api.rest.common.exception.ResourceNotFoundException;
import com.wildbeeslabs.api.rest.common.model.dto.wrapper.IBaseDTOListWrapper;
import com.wildbeeslabs.rest.publication.model.Article;
import com.wildbeeslabs.rest.publication.model.dto.ArticleDTO;
import com.wildbeeslabs.rest.publication.model.dto.wrapper.ArticleDTOListWrapper;
import com.wildbeeslabs.rest.publication.service.interfaces.IArticleService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

/**
 *
 * Article Proxy Controller implementation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 * @param <E>
 */
@Component
public class ArticleProxyController<T extends Article, E extends ArticleDTO> extends ABaseProxyController<T, E, String, IArticleService<T>> {

    public E findByNameIgnoreCase(final String name) {
        final T item = this.findEntityByNameIgnoreCase(name);
        return getDTOConverter().convertToDTO(item, getDtoClass());
    }

    public T findEntityByNameIgnoreCase(final String name) {
        LOGGER.info("Fetching article by name {}", name);
        final Optional<T> item = getService().findByNameIgnoreCase(name);
        if (!item.isPresent()) {
            throw new ResourceNotFoundException(getResource().formatMessage("error.no.article.item.by.name", name));
        }
        return item.get();
    }

//    public IBaseDTOListWrapper<? extends E> findByCategoryId(final Long categoryId) throws EmptyContentException {
//        final List<? extends T> items = this.findEntityByCategoryId(categoryId);
//        return getDTOConverter().convertToDTOAndWrap(items, getDtoClass(), getDtoListClass());
//    }
//
//    public List<? extends T> findEntityByCategoryId(final Long categoryId) throws EmptyContentException {
//        LOGGER.info("Fetching all articles by category id {}", categoryId);
//        final List<? extends T> items = getService().findByCategoryId(categoryId);
//        if (items.isEmpty()) {
//            throw new EmptyContentException(getResource().formatMessage("error.no.content"));
//        }
//        return items;
//    }
    public IBaseDTOListWrapper<? extends E> findByCategory(final String category) throws EmptyContentException {
        final List<? extends T> items = this.findEntityByCategory(category);
        return getDTOConverter().convertToDTOAndWrap(items, getDtoClass(), getDtoListClass());
    }

    public List<? extends T> findEntityByCategory(final String category) throws EmptyContentException {
        LOGGER.info("Fetching all articles by category {}", category);
        final List<? extends T> items = getService().findByCategory(category);
        if (items.isEmpty()) {
            throw new EmptyContentException(getResource().formatMessage("error.no.content"));
        }
        return items;
    }

    public IBaseDTOListWrapper<? extends E> findAll(final String name) throws EmptyContentException {
        final List<? extends T> items = this.findAllEntity(name);
        return getDTOConverter().convertToDTOAndWrap(items, getDtoClass(), getDtoListClass());
    }

    public List<? extends T> findAllEntity(final String name) throws EmptyContentException {
        LOGGER.info("Fetching all articles by name {}", name);
        final List<? extends T> items = Objects.isNull(name) ? getService().findAll() : getService().findByNameLike(name);
        if (items.isEmpty()) {
            throw new EmptyContentException(getResource().formatMessage("error.no.content"));
        }
        return items;
    }

    /**
     * Get default entity class
     *
     * @return entity class instance
     */
    @Override
    protected Class<? extends T> getEntityClass() {
        return (Class<? extends T>) Article.class;
    }

    /**
     * Get default DTO entity class
     *
     * @return DTO entity class instance
     */
    @Override
    protected Class<? extends E> getDtoClass() {
        return (Class<? extends E>) ArticleDTO.class;
    }

    /**
     * Get default DTO Wrapper entity class
     *
     * @return DTO wrapper entity class instance
     */
    @Override
    protected Class<? extends IBaseDTOListWrapper> getDtoListClass() {
        return ArticleDTOListWrapper.class;
    }
}
