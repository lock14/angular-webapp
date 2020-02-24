package org.lock14.angularwebapp.api;

import java.util.List;

public class Page<T> {
    private org.springframework.data.domain.Page page;

    public Page(org.springframework.data.domain.Page page) {
        this.page = page;
    }

    public static <T> Page<T> of(org.springframework.data.domain.Page page) {
        return new Page<>(page);
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
