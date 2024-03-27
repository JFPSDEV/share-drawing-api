package com.jfteam.sharedrawing.dto.favorite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddFavoriteRequestDto {
    private Long profileId;
    private Long drawingId;
}
