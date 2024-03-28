package com.jfteam.sharedrawing.dto.rating;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpsertRatingReqDto {
    Long drawingId;
    Integer rate;
}
