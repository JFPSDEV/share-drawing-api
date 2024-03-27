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
@Table(name = "_favorite")
public class Favorite implements IGenericEntity {
    @Id
    @GeneratedValue
    private long id;

    @Override
    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "drawing_id")
    private Drawing drawing;
}
