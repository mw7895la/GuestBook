package org.zerock.guestbook.service;

import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.Guestbook;

public interface GuestbookService {
    Long register(GuestbookDTO dto);

    //163p PageRequestDTO를 파라미터로, PageResultDTO를 리턴 타입으로 사용하는 getList() 설계
    //엔티티 객체를 DTO객체로 변환하는 entityToDto()를 정의

    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    //GuestbookService 인터페이스 내에서 default기능을 활용해서 구현클래스에서 동작할 수 있는 dtoToEntity()를 구성한다.
    default Guestbook dtoToEntity(GuestbookDTO dto){
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        return entity;
    }

    //Entity 객체를 DTO로 변환.
    default GuestbookDTO entityToDto(Guestbook entity){
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }
}
