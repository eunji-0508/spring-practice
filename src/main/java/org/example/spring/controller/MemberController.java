package org.example.spring.controller;

import lombok.RequiredArgsConstructor;
import org.example.spring.dto.MemberRequestDto;
import org.example.spring.dto.MemberResponseDto;
import org.example.spring.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 생성
    @PostMapping("/members")
    public MemberResponseDto createMember(
            @RequestBody MemberRequestDto memberRequestDto
    ) {
        return memberService.createMember(memberRequestDto);
    }

    // 전체 조회
    @GetMapping("/members")
    public List<MemberResponseDto> getMember() {
        return memberService.getMember();
    }

    // 단일 조회
    @GetMapping("/members/{memberId}")
    public MemberResponseDto getMember(
            @PathVariable Long memberId
    ) {
        return memberService.getMember(memberId);
    }

    // 수정
    @PutMapping("/members/{memberId}")
    public MemberResponseDto updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberRequestDto memberRequestDto
    ) {
        return memberService.updateMember(memberId, memberRequestDto);
    }

    // 삭제
    @DeleteMapping("/members/{memberId}")
    public void deleteMember(
            @PathVariable Long memberId
    ) {
        memberService.deleteMember(memberId);
    }
}
