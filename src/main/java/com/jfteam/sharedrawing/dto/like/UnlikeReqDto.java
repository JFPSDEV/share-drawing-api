package com.jfteam.sharedrawing.dto.like;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnlikeReqDto {
    Long profileId;
    Long entityId;
}
