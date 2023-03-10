package com.example.fastcampusmysql.util;

import com.example.fastcampusmysql.domain.post.entity.Post;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.time.LocalDate;

import static org.jeasy.random.FieldPredicates.*;

public class PostFixtureFactory {

    static public EasyRandom get(Long memberId, LocalDate firstDate, LocalDate lastDate, Long tmpNumber) {
        var idPredicate = named("id")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var memberPredicate = named("memberId")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var contentsPredicate = named("contents")
                .and(ofType(String.class))
                .and(inClass(Post.class));

        var param = new EasyRandomParameters()
                .excludeField(idPredicate)
                .dateRange(firstDate, lastDate)
                .randomize(memberPredicate, () -> memberId)
                .randomize(contentsPredicate, () -> "캘린더 내용 테스트 " + tmpNumber)
                .seed(tmpNumber);

        return new EasyRandom(param);
    }
}
