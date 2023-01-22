package mallsmall.mallsmall.apiController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import mallsmall.mallsmall.domain.Address;
import mallsmall.mallsmall.domain.Member;
import mallsmall.mallsmall.service.MemberService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    // 회원 등록 api
    @PostMapping("/members")
    public CreateMemberResponse createMember(
            @RequestBody @Valid CreateMemberRequest request
    ) {
        Member member = new Member(request.getName(), request.getCity(), request.getStreet());
        Long memberId = service.joinMember(member);
        return new CreateMemberResponse(memberId);
    }

    @Data
    @AllArgsConstructor
    static class CreateMemberResponse {
        private Long id;
    }

    @Data
    static class CreateMemberRequest {
        private String name;
        private String city;
        private String street;
    }

    // 회원 수정 api
    @PutMapping("/members/{id}")
    public UpdateMemberResponse updateMember (
            @PathVariable ("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request
    ) {
        service.updateMember(id, request.getName());
        Member findMember = service.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    @Data @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @Data
    static class UpdateMemberRequest {
        private String name;
    }

    // 회원 조회 api
    @GetMapping("/members")
    public Result AllMemberList() {
        List<Member> findMembers = service.findAll();
        List<Object> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getId(), m.getName(), m.getAddress()))
                .collect(Collectors.toList());
        return new Result(collect.size(), collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private int total;
        private List<T> data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private Long id;
        private String name;
        private Address address;
    }
}
