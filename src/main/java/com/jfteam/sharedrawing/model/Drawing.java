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
@Table(name = "_drawing")
public class Drawing implements Like<LikeDrawing>, IGenericEntity  {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @ElementCollection
    private List<String> sourceUrls;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToMany(mappedBy = "drawing", cascade = CascadeType.ALL)
    private List<Comment> comments;


    @ManyToMany
    @JoinTable(
            name = "drawing_tags",
            joinColumns = @JoinColumn(name = "drawing_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @OneToMany(mappedBy = "drawing", cascade = CascadeType.ALL)
    private List<LikeDrawing> likes;

    @Formula("(SELECT COALESCE(COUNT(*), 0) FROM _like_drawing ld WHERE ld.drawing_id = id AND ld.liked = true)")
    private int countLikes;

    @Formula("(SELECT COALESCE(COUNT(*), 0) FROM _like_drawing ld WHERE ld.drawing_id = id AND ld.liked = false)")
    private int countDislikes;

    @OneToMany(mappedBy = "drawing", cascade = CascadeType.ALL)
    private List<Rating> rates;

    @Formula("(SELECT COALESCE(AVG(r.rate), 0) FROM _rating r WHERE r.drawing_id = id)")
    private double rateAverage;

    @Formula("(SELECT COALESCE(COUNT(*), 0) FROM _rating r WHERE r.drawing_id = id)")
    private int countRates;

    @Override
    public List<LikeDrawing> getLikes() {
        return likes;
    }
}
