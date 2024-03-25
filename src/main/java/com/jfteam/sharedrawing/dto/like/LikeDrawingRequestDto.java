package com.jfteam.sharedrawing.dto.like;

import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeDrawingRequestDto {
    private Long profileId;
    private Long drawingId;
    private Boolean liked;
}
