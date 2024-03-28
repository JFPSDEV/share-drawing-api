package com.jfteam.sharedrawing.dto;


import com.jfteam.sharedrawing.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReqDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String pseudo;
}
