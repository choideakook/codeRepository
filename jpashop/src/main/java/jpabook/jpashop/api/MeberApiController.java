package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MeberApiController {

    private final MemberService memberService;

    // 모든 회원 조회 - Entity를 바로 꺼내는 방식
    @GetMapping ("/api/v1/members")
    public List<Member> membersV1 () {
        return memberService.findMembers();
    }

    // 모든 회원 조회 - DTO 를 통하는 방식
    @GetMapping ("/api/v2/members")
    public Result memberV2() {
        List<Member> findMember = memberService.findMembers();
        List<MemberDto> collect = findMember.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());
        return new Result(collect.size(), collect);
    }

    @Data @AllArgsConstructor
    static class Result<T> {
        private int count;
        private List<T> data;
    }

    @Data @AllArgsConstructor
    static class MemberDto{
        private String name;
    }


    // Entity 를 Parameter 값으로 직접 호출하는 방식
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    // DTO 를 생성해 값만 받아오는 방식
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    // 회원 정보 수정
    // 첫번째 Param : put Mapping 으로 받아온 id 값을 res DTO 에 넘겨 수정할 id 를 찾아옴
    // 두번째 Param : req DTO 에서 수정을 원하는 필드를 가져옴
    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request){
        memberService.update(id, request.getName());
        // return 값을 충족시키기 위한 로직
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    // 회원 수정 req DTO
    @Data
    static class UpdateMemberRequest {
        private String name;
    }

    // 회원 수정 res DTO
    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }

    // 회원 등록 req DTO
    @Data
    static class CreateMemberRequest{
        @NotEmpty
        private String name;
    }

    // 회원 등록 res DTO
    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

}
