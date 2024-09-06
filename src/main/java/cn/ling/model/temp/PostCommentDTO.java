package cn.ling.model.temp;

import cn.ling.entity.User;
import cn.ling.enums.CommentOpenStateEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Component
public class PostCommentDTO {
    /**
     * 是否访客的评论
     */
    private Boolean isVisitorComment;

    String subject;

    User admin;

    /**
     * 文章状态
     */
    CommentOpenStateEnum judgeResult;
}
