package com.alby.userservice.serviceimpl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.alby.userservice.security.BCrypt;
import com.alby.userservice.util.UserUtil;
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
        validationService.validate(request);

        Page<Users> users = userRepository.findAll(
            PageRequest.of(
                request.getPage(),
                request.getPageSize(),
                Sort.by("firstName")
                    .ascending()
            ));

        List<UserResponse> userResponses = users
            .stream()
            .map(UserUtil::mapUserToUserResponse)
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
            .data(userRepository.findById(request.getUserId())
                .map(UserUtil::mapUserToUserResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
            .build();
    }

    @Override
    @Transactional
    public WebResponse<UserResponse> add(UserAddRequest request) {
        validationService.validate(request);

        if (userRepository.existsByUsername(request.getUsername()))
            throw new ResponseStatusException(HttpStatus.CONFLICT);

        Users user = UserUtil.mapAddRequestToUsers(request);
        userRepository.save(user);

        return WebResponse.<UserResponse> builder()
                .message("OK")
                .data(UserUtil.mapUserToUserResponse(user))
                .build();
    }

    @Override
    @Transactional
    public WebResponse<UserResponse> update(UserUpdateRequest request) {
        validationService.validate(request);
        
        Users userFromDb = userRepository.findById(request.getUserId())
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

        if (Objects.nonNull(request.getManagerId())) {
            userFromDb.setManagerId(request.getManagerId());
        }
    
        userRepository.save(userFromDb);

        return WebResponse.<UserResponse> builder()
            .message("OK")
            .data(UserUtil.mapUserToUserResponse(userFromDb))
            .build();
    }

    @Override
    @Transactional
    public WebResponse<String> delete(UserDeleteRequest request) {
        validationService.validate(request);

        Users user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        user.setStatus("N");
        user.setModifiedBy(request.getModifiedBy());
        userRepository.save(user);

        return WebResponse.<String> builder()
                .data("OK")
                .build();
    }
    
}
