package dev.mzkhawar.getit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersDto {

    private Long userId;

    private String userName;

    private String password;

    private String email;

    private LocalDateTime createdOn;
}
