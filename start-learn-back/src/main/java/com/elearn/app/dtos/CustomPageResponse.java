package com.elearn.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomPageResponse<T> {
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean last;
    private List<T> content;

}
