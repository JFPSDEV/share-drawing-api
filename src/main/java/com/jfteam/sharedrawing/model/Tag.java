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
@Table(name = "_tag")
public class Tag implements IGenericEntity  {
    @Id
    @GeneratedValue
    private long id;
    @Override
    public Long getId() {
        return id;
    }
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Drawing> drawings;
}
