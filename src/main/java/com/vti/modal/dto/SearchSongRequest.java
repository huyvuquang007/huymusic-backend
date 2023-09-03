package com.vti.modal.dto;

import lombok.Data;

@Data
public class SearchSongRequest extends BaseRequest {
    private String name;
    private String author;
    private String singer;
}
