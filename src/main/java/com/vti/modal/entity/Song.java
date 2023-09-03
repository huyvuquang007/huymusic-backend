package com.vti.modal.entity;

import com.vti.modal.dto.SongCreatDto;
import com.vti.modal.dto.SongUpdateDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`song`")
public class Song extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @ManyToOne
    @JoinColumn(name = "singer")
    private Singer singer;

    @Column(name = "path")
    private String path;

    public Song(SongCreatDto songCreatDto) {
        this.name = songCreatDto.getName();
        this.author = songCreatDto.getAuthor();
//        this.singer = songCreatDto.getSinger();
        this.path = songCreatDto.getPath();
    }

    public Song(SongUpdateDto songUpdateDto) {
        this.id = songUpdateDto.getId();
        this.name = songUpdateDto.getName();
        this.author = songUpdateDto.getAuthor();
//        this.singer = songUpdateDto.getSinger();
        this.path = songUpdateDto.getPath();
    }
}
