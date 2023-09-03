package com.vti.modal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongCreatDto {

    @NotNull(message = "ko được để trống")
    private String name;
    private String author;
    private String singer;
    private String path;
}
