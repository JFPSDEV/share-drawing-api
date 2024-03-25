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
    public LikeDrawing(Profile profile, Boolean liked, Drawing drawing) {
        super(profile, liked);
        this.drawing = drawing;
    }

    @ManyToOne
    @JoinColumn(name = "drawing_id")
    private Drawing drawing;




}
