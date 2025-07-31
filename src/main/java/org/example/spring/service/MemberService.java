package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.dto.MemberRequestDto;
import org.example.spring.dto.MemberResponseDto;
import org.example.spring.entity.Member;
import org.example.spring.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional      // 작업 단위
    // CRUD의 C
    public MemberResponseDto createMember(MemberRequestDto memberRequestDto) {
        Member member = new Member(memberRequestDto.getName());
        Member savedMember = memberRepository.save(member);    // 멤버를 저장

        return new MemberResponseDto(
                savedMember.getId(),
                savedMember.getName()
        );
    }

    // CRUD의 R -> 단건 조회
    @Transactional(readOnly = true)
    public MemberResponseDto getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id의 멤버는 존재하지 않습니다.")
        );      // 없을수도 있기 때문에 처리해줘야 함

        return new MemberResponseDto(
                member.getId(),
                member.getName()
        );
    }

    // CRUD의 R -> 전체 조회
    @Transactional(readOnly = true)
    public List<MemberResponseDto> getMember() {
        List<Member> members = memberRepository.findAll();

        List<MemberResponseDto> dtoList = new ArrayList<>();

        for (Member member : members) {
            MemberResponseDto memberResponseDto = new MemberResponseDto(
                    member.getId(),
                    member.getName()
            );
            dtoList.add(memberResponseDto);
        }
        return dtoList;
    }

    // CRUD의 U -> 전체 조회
    // 찾아야 하니까 Long id도 받아야 함
    @Transactional
    public MemberResponseDto updateMember(Long id, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id의 멤버는 존재하지 않습니다.")
        );      // 없을수도 있기 때문에 처리해줘야 함
        member.updateName(memberRequestDto.getName());
        return new MemberResponseDto(member.getId(), member.getName());
    }

    // CRUD의 D
    @Transactional
    public void deleteMember(Long id) {
        memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id의 멤버는 존재하지 않습니다.")
        );
        memberRepository.deleteById(id);
    }
}
