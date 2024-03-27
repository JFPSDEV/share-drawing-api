package com.jfteam.sharedrawing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.util.List;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_comment")
public class Comment implements Like<LikeComment>,  IGenericEntity {

    @Id
    @GeneratedValue
    private Long id;


    private String message;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "drawing_id")
    private Drawing drawing;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<Comment> replies;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<LikeComment> likes;

    @Formula("(SELECT COALESCE(COUNT(*), 0) FROM _like_comment ld WHERE ld.comment_id = id AND ld.liked = true)")
    private int countLikes;

    @Formula("(SELECT COALESCE(COUNT(*), 0) FROM _like_comment ld WHERE ld.comment_id = id AND ld.liked = false)")
    private int countDislikes;

    @Override
    public List<LikeComment> getLikes() {
        return likes;
    }
}
