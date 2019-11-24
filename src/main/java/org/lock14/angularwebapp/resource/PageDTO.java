package org.lock14.angularwebapp.resource;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageDTO<T> {
    private Page<T> page;

    public PageDTO(Page<T> page) {
        this.page = page;
    }

    public static <T> PageDTO<T> of(Page<T> page) {
        return new PageDTO<>(page);
    }

    public List<T> getContent() {
        return page.getContent();
    }

    public long getTotalElements() {
        return page.getTotalElements();
    }

    public int getTotalPages() {
        return page.getTotalPages();
    }

    public int getSize() {
        return page.getSize();
    }

    public int getNumber() {
        return page.getNumber();
    }

    public int getNumberOfElements() {
        return page.getNumberOfElements();
    }
}
