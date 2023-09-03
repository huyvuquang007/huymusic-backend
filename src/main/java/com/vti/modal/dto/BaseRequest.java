package com.vti.modal.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Data
public class BaseRequest { // dùng để phân trang
    protected int pageNumber; // đang ở trang mấy
    protected int pageSize; // số đối tượng trong 1 trang
    protected String sortField; //  cột muốn sắp xếp tăng giảm , vd : id
    protected String sortType; // sắp xếp theo ASC hay DESC

    public static void verify(BaseRequest request){
        if (request.getPageNumber() == 0){
            request.setPageNumber(1);
        }

        if (request.getPageSize() == 0){
            request.setPageSize(3);
        }

        if (request.getSortField() == null || "".equals(request.getSortField())){
            request.setSortField("id");
        }

        if (request.getSortType()== null || "".equals(request.getSortType())){
            request.setSortType("DESC");
        }
    }

    public static PageRequest buildPageRequest (BaseRequest request){
        PageRequest pageRequest = null;
        if ("DESC".equals(request.getSortType())){
            pageRequest = PageRequest.of(request.getPageNumber() - 1 , request.getPageSize() ,
                    Sort.by(request.getSortField()).descending());
        }else {
            pageRequest = PageRequest.of(request.getPageNumber() - 1 , request.getPageSize() ,
                    Sort.by(request.getSortField()).ascending());
        }
        return pageRequest;
    }
}
