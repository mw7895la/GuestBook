package org.zerock.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.guestbook.entity.Guestbook;
import org.zerock.guestbook.entity.QGuestbook;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.zerock.guestbook.entity.QGuestbook.guestbook;

@SpringBootTest
public class GuestbookRepositoryTests {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1,300).forEach(i ->{

            Guestbook guestbook = Guestbook.builder()
                    .title("Title .... "+i)
                    .content("Content .... "+i)
                    .writer("User .... "+(i % 10))
                    .build();
            System.out.println(guestbookRepository.save(guestbook));
        });
    }

    @Test
    public void updateTest(){
        Optional<Guestbook> result = guestbookRepository.findById(300L);

        if(result.isPresent()){
            Guestbook guestbook = result.get();     //위에서 300번째 얻어온것을 guestbook에 넣어

            guestbook.changeTitle("Changed Title.....");
            guestbook.changeContent("Changed Content.....");

            guestbookRepository.save(guestbook);
        }
    }

    //제목+작성자+내용 ,  제목+작성자/제목+내용/작성자+내용 , 제목/작성자/내용
    @Test
    public void testQuery1(){
        Pageable pageable = PageRequest.of(0,50, Sort.by("gno").descending());

        //1. Q도메인 클래스를 얻어온다.
        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";

        //BooleanBuilder는 where문에 들어가는 조건들을 넣어주는 컨테이너.
        BooleanBuilder builder = new BooleanBuilder();

        //원하는 조건은 필드 값과 같이 결합해서 생성.
        BooleanExpression expression = qGuestbook.title.contains(keyword);


        //만들어진 조건은 where문에 and나 or같은 키워드와 결합시킨다.
        builder.and(expression);

        //BooleanBuilder는 GuestbookRepository에 추가된 QueryDslPredicateExcutor 인터페이스의 findAll 사용가능.
        Page<Guestbook> result = guestbookRepository.findAll(builder,pageable);

        result.stream().forEach(guestbook ->{
            System.out.println(guestbook);
        });
    }

    @Test
    public void testQuery2(){
        Pageable pageable = PageRequest.of(0,30,Sort.by("gno").descending());

        //Q도메인 클래스를 얻어온다
        QGuestbook qGuestbook = guestbook;

        String keyword="1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exTitle = qGuestbook.title.contains(keyword);

        BooleanExpression exContent = qGuestbook.content.contains(keyword);

        BooleanExpression exWriter = qGuestbook.writer.contains(keyword);

        BooleanExpression exAll = exTitle.and(exContent).and(exWriter);

        //BooleanBuilder에 exAll과 gno가 greater than 즉 0보다 큰거
        builder.and(exAll);

        builder.and(qGuestbook.gno.gt(0L));

        //Predicate는 Type T 인자를 받고 boolean을 리턴하는 함수형 인터페이스입니다.
        Page<Guestbook> result = guestbookRepository.findAll(builder,pageable);

        result.stream().forEach(guestbook->{
            System.out.println(guestbook);
        });



    }




}
