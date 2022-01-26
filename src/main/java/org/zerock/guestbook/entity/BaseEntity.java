package org.zerock.guestbook.entity;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass       //이 어노테이션이 붙으면 해당 클래스는 테이블로 생성되지 않는다. **다른 클래스가 여기를 상속받으면 그땐 테이블이 만들어진다.
@EntityListeners(value={AuditingEntityListener.class})      //JPA내부에서 entity 객체가 생성/변경되는 것을 감지하는 역할.
@Getter
public class BaseEntity {

    //JPA는 JPA만의 고유한 메모리공간(Persistence context)을 이용해서 엔티티 객체를 관리

    //JPA에서 엔티티의 생성 시간을 처리
    @CreatedDate
    @Column(name= "regdate", updatable=false)       //updatable은 해당 엔티티 객체를 데이터베이스에 반영할 때 regdate컬럼값은 변경되지 않는다.
    private LocalDateTime regDate;

    //최종 수정시간을 자동으로 처리
    @LastModifiedDate
    @Column(name="moddate")
    private LocalDateTime modDate;
}
