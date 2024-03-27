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
@Table(name = "_profile")
public class Profile implements IGenericEntity {
    @Id
    @GeneratedValue
    private long id;

    @Override
    public Long getId() {
        return id;
    }

    @Column(unique = true)
    private String pseudo;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Drawing> drawings;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Favorite> favorites;
}
