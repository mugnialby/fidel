package com.alby.userservice.serviceimpl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.alby.userservice.dto.request.UserAddRequest;
import com.alby.userservice.dto.request.UserDeleteRequest;
import com.alby.userservice.dto.request.UserGetRequest;
import com.alby.userservice.dto.request.UserPagingRequest;
import com.alby.userservice.dto.request.UserUpdateRequest;
import com.alby.userservice.dto.response.UserResponse;
import com.alby.userservice.dto.response.WebResponse;
import com.alby.userservice.entity.Users;
import com.alby.userservice.repository.UserRepository;
import com.alby.userservice.service.UserService;
import com.alby.userservice.service.ValidationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ValidationService validationService;

    @Override
    public WebResponse<List<UserResponse>> getAll(UserPagingRequest request) {
        Page<Users> users = userRepository.findAll(
            PageRequest.of(
                page.orElse(0), 
                size.orElse(10), 
                Sort.by("firstName")
                    .ascending()
            ));

        List<UserResponse> userResponses = users
            .stream()
            .map(data -> new UsersUtil().mapUserToUserResponse(data))
            .collect(Collectors.toList());

        return WebResponse.<List<UserResponse>> builder()
            .message("OK")
            .data(userResponses)
            .build();
    }

    @Override
    public WebResponse<UserResponse> get(UserGetRequest request) {
        validationService.validate(request);

        return WebResponse.<UserResponse> builder()
            .message("OK")
            .data(userRepository.findById(request)   
                .map(data -> new UsersUtil().mapUserToUserResponse(data))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
            .build();
    }

    @Override
    @Transactional
    public WebResponse<UserResponse> add(UserAddRequest request) {
        validationService.validate(request);

        if (userRepository.existsByUsername(request.getUsername()))
        throw new ResponseStatusException(HttpStatus.CONFLICT);

        userRepository.save(new UsersUtil().mapAddRequestToUsers(request));
    }

    @Override
    @Transactional
    public WebResponse<UserResponse> update(UserUpdateRequest request) {
        validationService.validate(request);
        
        User userFromDb = userRepository.findById(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            
        if (Objects.nonNull(request.getUsername())) {
            if (!request.getUsername().equalsIgnoreCase(userFromDb.getUsername())
                && userRepository.existsByUsername(request.getUsername())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }

            userFromDb.setUsername(request.getUsername());
        }
    
        if (Objects.nonNull(request.getPassword())) {
            userFromDb.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }
    
        if (Objects.nonNull(request.getFirstName())) {
            userFromDb.setFirstName(request.getFirstName());
        }
    
        if (Objects.nonNull(request.getLastName())) {
            userFromDb.setLastName(request.getLastName());
        }
    
        if (Objects.nonNull(request.getStatus())) {
            userFromDb.setStatus(request.getStatus());
        }
    
        userRepository.save(userFromDb);

        return WebResponse.<UserResponse> builder()
            .message("OK")
            .data(new UsersUtil().mapUserToUserResponse(userFromDb))
            .build();
    }

    @Override
    @Transactional
    public WebResponse<String> delete(UserDeleteRequest request) {
        validationService.validate(userId);

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        user.setStatus("N");
        userRepository.save(user);
    }
    
}
