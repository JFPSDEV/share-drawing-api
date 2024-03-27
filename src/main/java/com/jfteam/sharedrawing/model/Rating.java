package com.jfteam.sharedrawing.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_rating")
public class Rating implements IGenericEntity  {
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


    @Min(1)
    @Max(5)
    private Integer rate;
}
