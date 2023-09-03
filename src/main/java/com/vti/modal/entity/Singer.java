package com.vti.modal.entity;

import com.vti.modal.dto.CreateSingerDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`singer`")
public class Singer extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "`name`")
    private String name;

    @Column(name = "image")
    private String image;

    public Singer(CreateSingerDto createSingerDto) {
        this.name = createSingerDto.getName();
        this.image = createSingerDto.getImage();
    }
}
