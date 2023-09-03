package com.vti.modal.dto;

import lombok.Data;

@Data
public class SongUpdateDto {
    private int id;
    private String name;
    private String author;
    private String singer;
    private String path;
}
