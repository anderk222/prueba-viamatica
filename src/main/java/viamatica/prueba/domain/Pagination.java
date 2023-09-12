package viamatica.prueba.domain;

import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination<T> {

    private int limit;
    private int page;
    private int totalPages;
    private long totalItems;
    private Collection<T> data;

    public Pagination(int page, int limit, Collection<T> data) {

        this.page = page;
        this.limit = limit;
        this.data = data;
    }

}