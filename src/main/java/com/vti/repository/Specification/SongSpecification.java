package com.vti.repository.Specification;

import com.vti.modal.dto.SearchSongRequest;
import com.vti.modal.entity.Singer;
import com.vti.modal.entity.Song;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

public class SongSpecification {
    // Hàm tổng
    public static Specification<Song> songBuildCondition (SearchSongRequest request){
        return Specification.where(findBySongName(request))
                .and(findByAuthor(request))
                .and(findBySinger(request));
    }


    // tìm kiếm theo tên
    public static Specification<Song> findBySongName (SearchSongRequest request){
        if (request.getName() != null){
            return (root, query, cri) -> {
// root: Chọn cột, field, để tìm kiếm (giá trị là thuộc tính trong java)
// cri: CriteriaBuilder Khai báo loại so sánh dữ liệu. ( lớn hơn, nhỏ hơn, equal, like,.... )
                return cri.like(root.get("name") , "%" + request.getName()+ "%");
            };
        }else {
            return null;
        }
    }

    // tìm kiếm theo tác giả
    public static Specification<Song> findByAuthor (SearchSongRequest request){
        if (request.getAuthor() != null){
            return (root, query, cri) -> {
// root: Chọn cột, field, để tìm kiếm (giá trị là thuộc tính trong java)
// cri: CriteriaBuilder Khai báo loại so sánh dữ liệu. ( lớn hơn, nhỏ hơn, equal, like,.... )
                return cri.like(root.get("author") , "%" + request.getAuthor()+ "%");
            };
        }else {
            return null;
        }
    }

    // tìm kiếm theo ca sĩ
    public static Specification<Song> findBySinger (SearchSongRequest request){
        if (request.getSinger() != null){
            return (root, query, cri) -> {
// root: Chọn cột, field, để tìm kiếm (giá trị là thuộc tính trong java)
// cri: CriteriaBuilder Khai báo loại so sánh dữ liệu. ( lớn hơn, nhỏ hơn, equal, like,.... )
            Join<Song,Singer> joiner = root.join("singer");
                return cri.like(joiner.get("name"), "%" + request.getSinger()+ "%");
            };
        }else {
            return null;
        }
    }
}
