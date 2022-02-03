package org.zerock.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    private int page;
    private int size;

    public PageRequestDTO(){
        this.page=1;
        this.size=15;
    }

    public Pageable getPageable(Sort sort){
        //JPA를 이용하는 경우 페이지 번호가 0부터 시작하기 때문에 page-1 을 한다.
        return PageRequest.of(page-1,size,sort);
    }
}
