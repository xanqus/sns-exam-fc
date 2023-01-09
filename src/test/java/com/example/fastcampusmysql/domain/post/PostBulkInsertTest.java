package com.example.fastcampusmysql.domain.post;

import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import com.example.fastcampusmysql.util.PostFixtureFactory;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SpringBootTest
public class PostBulkInsertTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void bulkInsert() {

//        var easyRandom = PostFixtureFactory
//                .get(
//                        3L,
//                        LocalDate.of(2022, 12, 1),
//                        LocalDate.of(2023, 1, 10)
//                );
        var stopWatch = new StopWatch();
        stopWatch.start();


        var posts = LongStream.range(0, 10000 * 300)
                .parallel()
                .mapToObj(i -> getEasyRandom(i).nextObject(Post.class))
                .toList();

        stopWatch.stop();

        var queryStopWatch = new StopWatch();

        queryStopWatch.start();


        postRepository.bulkInsert(posts);
        queryStopWatch.stop();
        System.out.println("--------------------------------------------------------------------------객체 생성 시간: " + stopWatch.getTotalTimeSeconds());
        System.out.println("--------------------------------------------------------------------------DB 인서트 시간: " + queryStopWatch.getTotalTimeSeconds());


    }

    private static EasyRandom getEasyRandom(Long l) {
        return PostFixtureFactory
                .get(
                        3L,
                        LocalDate.of(1978, 1, 1),
                        LocalDate.of(2023, 1, 10),
                        l
                );
    }
}
