package usedmarket.usedmarket.domain.member.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import usedmarket.usedmarket.domain.comment.domain.Comment;
import usedmarket.usedmarket.domain.member.domain.Member;

import java.util.List;

@Getter
public class MemberResponseDto {

    private String nickname;
    @Builder
    public MemberResponseDto(Member member) {
        this.nickname = member.getNickname();
    }
}
