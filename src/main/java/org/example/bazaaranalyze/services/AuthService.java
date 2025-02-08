package org.example.bazaaranalyze.services;

import lombok.RequiredArgsConstructor;
import org.example.bazaaranalyze.dto.LoginDto;
import org.example.bazaaranalyze.dto.SignupDto;
import org.example.bazaaranalyze.entity.SignupBase;
import org.example.bazaaranalyze.jwt.JwtTokenProvider;
import org.example.bazaaranalyze.payload.ApiResponse;
import org.example.bazaaranalyze.repository.AuthRepos;
import org.example.bazaaranalyze.roles.UserRoles;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepos signupRepos;
    private final JwtTokenProvider jwtTokenProvider;

    public ApiResponse signup(SignupDto signupDto) {

        SignupBase signupBase = new SignupBase();

        if (signupRepos.findByEmail(signupDto.getEmail()).isPresent()) {
            return new ApiResponse("This is email already excist!", false);
        }

        signupBase.setUsername(signupDto.getUsername());
        if (signupDto.getPassword().equals(signupDto.getConf_pass()) && signupDto.getPassword().length() >= 6) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            signupBase.setPassword(encoder.encode(signupDto.getPassword()));
        } else {
            return new ApiResponse("Passwords do not match", false);
        }

        signupBase.setEmail(signupDto.getEmail());
        signupBase.setPhone(signupDto.getPhone());
        signupBase.setRole(
                signupDto.getRole() == null ? UserRoles.USER : signupDto.getRole()
        );

        signupRepos.save(signupBase);
        String token = jwtTokenProvider.generateToken(signupDto.getUsername());
        return new ApiResponse("Create user", true, token);
    }


    public ApiResponse login(LoginDto loginDto) {
        SignupBase signupBase = signupRepos.findByUsernameOrEmail(loginDto.getUsername(), loginDto.getUsername()).orElse(null);

        if (signupBase == null) {
            return new ApiResponse("User not found", false);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(loginDto.getPassword(), signupBase.getPassword())) {
            return new ApiResponse("Wrong password", false);
        }

        String token = jwtTokenProvider.generateToken(signupBase.getUsername());
        return new ApiResponse("Login successful", true, token);
    }
}
