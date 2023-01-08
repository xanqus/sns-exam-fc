package com.example.fastcampusmysql.application.controller;

import com.example.fastcampusmysql.application.usacase.CreateFollowMemberUsaCase;
import com.example.fastcampusmysql.application.usacase.GetFollowingMembersUsacase;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/follow")
@RestController
@RequiredArgsConstructor
public class FollowController {
    private final CreateFollowMemberUsaCase createFollowMemberUsaCase;

    private final GetFollowingMembersUsacase getFollowingMembersUsacase;

    @PostMapping("/{fromId}/{toId}")
    public void register(@PathVariable Long fromId, @PathVariable Long toId) {
        createFollowMemberUsaCase.execute(fromId, toId);
    }

    @GetMapping("/members/{fromId}")
    public List<MemberDto> register(@PathVariable Long fromId) {
        return getFollowingMembersUsacase.execute(fromId);
    }

}
