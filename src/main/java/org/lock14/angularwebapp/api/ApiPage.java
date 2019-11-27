package org.lock14.angularwebapp.api;

import org.springframework.data.domain.Page;

import java.util.List;

public class ApiPage<T> {
    private Page<T> page;

    public ApiPage(Page<T> page) {
        this.page = page;
    }

    public static <T> ApiPage<T> of(Page<T> page) {
        return new ApiPage<>(page);
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
