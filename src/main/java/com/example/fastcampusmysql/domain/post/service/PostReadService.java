package com.example.fastcampusmysql.domain.post.service;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostReadService {
    final private PostRepository postRepository;

    public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request) {
        /*
            반환 값 -> 리스트 [작성일자, 작성회원, 작성 게시물 개수]

            select createDate, memberId, count(id)
            from Post
            where memberId = :memberId
            and createDate between firstDate and lastDate
            group by createDate memberId
         */
        return postRepository.groupByCreatedDate(request);
    }
}