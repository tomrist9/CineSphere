package com.ikt.auth.dto.response;

import com.ikt.auth.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    public static UserResponseDto toUserResponseDto(User user){
        return new UserResponseDto(user.getFirstName(), user.getLastName(), user.getEmail());
    }
}
