package com.jfteam.sharedrawing.dto.drawing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.SortDirection;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterDrawingRequestDto {
    private Integer page;
    private Integer size;
    private List<Long> tagIds;
    private SortDirection sortDirection;
}
