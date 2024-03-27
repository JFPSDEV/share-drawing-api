package com.jfteam.sharedrawing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class LikeEntity implements IGenericEntity {
    @Id
    @GeneratedValue
    public long id;

    @Override
    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    private Boolean liked;

}
