package dev.mzkhawar.getit.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {

    private int statusCode;
    private String message;
    private String token;
    private String role;
    private String expirationTime;
    private UserDto user;
    private WeightDto weight;
    private List<WeightDto> weightList;
    private List<UserDto> userList;

}
