package org.zerock.guestbook.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Guestbook extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)  //기본키 생성을 DB에 일임한다.
    private Long gno;

    @Column(length=100, nullable=false)
    private String title;

    @Column(length =1500, nullable=false)
    private String content;

    @Column(length = 50, nullable=false)
    private String writer;
}
