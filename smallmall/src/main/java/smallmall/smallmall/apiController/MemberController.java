package smallmall.smallmall.apiController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import smallmall.smallmall.domain.Member;
import smallmall.smallmall.service.MemberService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    // 회원 정보 등록
    @PostMapping("/members")
    public CreateMemberResponse saveMember (
            @RequestBody @Valid CreateMemberRequest request
    ){
        Member member = new Member(request.getName());
        member.setAddress(request.getPhone(), request.getMyAddress());
        Long memberId = service.join(member);
        return new CreateMemberResponse(memberId);
    }

    @Data
    @AllArgsConstructor
    static class CreateMemberResponse{
        private Long id;
    }
    @Data
    static class CreateMemberRequest{
        private String name;
        private String phone;
        private String myAddress;

    }

    //회원 정보 수정
    @PutMapping("/members/{id}")
    public UpdateMemberResponse updateMember(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request
    ){
        service.update(id, request.getName());
        Member findMember = service.findOne(id);
        return new UpdateMemberResponse(findMember.getId(),findMember.getName());
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }
    @Data
    static class UpdateMemberRequest{
        private String name;
    }

    // DB 모든 회원 조회
    @GetMapping("/members")
    public Result findAllMember() {
        List<Member> findMembers = service.findMembers();
        List<Object> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName(), m.getAddress().getPhone()))
                .collect(Collectors.toList());
        return new Result("모든 Member 리스트 입니다.",collect.size(), collect);
    }

    @Data
    @AllArgsConstructor
    static class Result <T>{
        private String info;
        private  int size;
        private List<T> data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String name;
        private String phone;
    }
}
