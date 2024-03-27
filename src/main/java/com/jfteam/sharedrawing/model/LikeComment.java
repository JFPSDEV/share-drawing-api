package com.jfteam.sharedrawing.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_like_comment")
public class LikeComment extends LikeEntity  {
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;
}
