package com.ikt.auth.service;


import com.ikt.auth.dto.request.UserCreateRequestDto;
import com.ikt.auth.dto.request.UserLoginRequestDto;
import com.ikt.auth.dto.response.UserResponseDto;
import com.ikt.auth.entity.User;
import com.ikt.auth.exception.NotFoundException;
import com.ikt.auth.exception.UserAlreadyExistException;
import com.ikt.auth.jwt.JwtService;
import com.ikt.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public Pair<UserResponseDto, String> create(UserCreateRequestDto userCreateRequestDto) throws UserAlreadyExistException {
        User user = userRepository.getUserByEmail(userCreateRequestDto.getEmail());

        if (user != null) {
            throw new UserAlreadyExistException("This email is already used");
        }
        User savedUser = userRepository.save(userCreateRequestDto.toUser());
        String accessToken = jwtService.generate(userCreateRequestDto.getEmail(), "ACCESS");

        return Pair.of(UserResponseDto.toUserResponseDto(savedUser), accessToken);
    }

    public Pair<UserResponseDto, String> login(UserLoginRequestDto userLoginRequestDto) throws NotFoundException {
        User user = userRepository.getUserByEmailAndPassword(userLoginRequestDto.getEmail(), userLoginRequestDto.getPassword());

        if (user == null) {
            throw new NotFoundException("User not found");
        }

        String accessToken = jwtService.generate(userLoginRequestDto.getEmail(), "ACCESS");
        return Pair.of(UserResponseDto.toUserResponseDto(user), accessToken);
    }

    public void deleteUser(String email) throws NotFoundException {
        User user = getUser(email);
        userRepository.delete(user);
    }

    private User getUser(String email) throws NotFoundException {
        User user = userRepository.getUserByEmail(email);

        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return user;
    }


}
