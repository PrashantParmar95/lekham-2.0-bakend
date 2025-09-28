package com.lekham.services;

import com.lekham.api.RestApiResponse;
import com.lekham.dto.UserDto;
import com.lekham.entities.Users;
import com.lekham.entities.audit.UsersAudit;
import com.lekham.jwt.JwtService;
import com.lekham.repositories.UserAuditRepository;
import com.lekham.repositories.UserRepository;
import com.lekham.util.MessageUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAuditRepository userAuditRepository;

    public RestApiResponse<Users> addUser(Users user) {


        int userCount  = userRepository.isUserExistsByEmail(user.getEmail());

        if(userCount == 0){
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            Users savedUser = userRepository.save(user);
            return RestApiResponse.successResponseBuilder(messageUtil.get("user.added.successfully"),savedUser);
        }else{
            return RestApiResponse.badResponseBuilder(messageUtil.get("user.already.registered"));
        }

    }

    public RestApiResponse<List<UserDto>> getAllUsers() {
        List<Users> list = (List<Users>) userRepository.findAll();

        List<UserDto> userDtos = new ArrayList<>();
        for(Users user : list){
            userDtos.add(new UserDto(user.getUsername(),user.getEmail(),user.getUserBio(),user.getPhoneNumber()));
        }

        return RestApiResponse.successResponseBuilder("User Received",userDtos);
    }

    public RestApiResponse<Users> updateUsers(Users user) {

        Optional<Users> fetchUser = userRepository.findById(user.getId());

        if(fetchUser.isPresent()){
            userAuditRepository.save(UsersAudit.fromUser(user));
            userRepository.save(user);
            return RestApiResponse.successResponseBuilder(messageUtil.get("user.updated.successfully"),user);
        }else{
            return RestApiResponse.badResponseBuilder(messageUtil.get("user.update.failed"));
        }
    }
    @Autowired
    private JwtService jwtService;
    public Users getCurrentUser() {
        String token = getTokenFromRequest();
        String username = jwtService.extractUsername(token);
        Optional<Users> userOpt = userRepository.findByUsername(username);

        if(userOpt.isPresent()){
            return (Users)userOpt.get();
        }else{
            return null;
        }

    }

    private String getTokenFromRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String authHeader = request.getHeader("Authorization");
        return (authHeader != null && authHeader.startsWith("Bearer "))
                ? authHeader.substring(7)
                : null;
    }

}
