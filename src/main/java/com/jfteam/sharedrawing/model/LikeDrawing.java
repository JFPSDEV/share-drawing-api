package com.jfteam.sharedrawing.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_like_drawing")
public class LikeDrawing extends LikeEntity {
    @ManyToOne
    @JoinColumn(name = "drawing_id")
    private Drawing drawing;


}
