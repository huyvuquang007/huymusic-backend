package com.vti.repository.Specification;

import com.vti.modal.dto.SearchSingerRequest;
import com.vti.modal.dto.SearchSongRequest;
import com.vti.modal.entity.Singer;
import com.vti.modal.entity.Song;
import org.springframework.data.jpa.domain.Specification;

public class SingerSpecification {
    // Hàm tổng
    public static Specification<Singer> singerBuildCondition (SearchSingerRequest request){
        return Specification.where(findBySingerName(request));
    }


    public static Specification<Singer> findBySingerName (SearchSingerRequest request){
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
}
