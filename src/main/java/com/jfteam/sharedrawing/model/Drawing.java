package com.jfteam.sharedrawing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_drawing")
public class Drawing implements Like<LikeDrawing>  {

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

    @Override
    public List<LikeDrawing> getLikes() {
        return likes;
    }

}
