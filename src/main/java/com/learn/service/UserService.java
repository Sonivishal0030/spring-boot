package com.learn.service;

import com.learn.domain.User;
import com.learn.domain.projection.UserProjection;
import com.learn.repository.UserRepository;
import com.learn.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectionFactory projectionFactory;

    public UserProjection persistUser(UserRequest userRequest) {
        if(userRequest.getName() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"please enter user name");
        }
        if(userRequest.getEmail() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"please enter user email");
        }

        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setCreatedDate(new Date());

        user = userRepository.save(user);

        return projectionFactory.createProjection(UserProjection.class, user);
    }

    public Page<UserProjection> getAllUsers() {
        return new PageImpl(userRepository.findAll().stream().
                map(p -> projectionFactory.createProjection(UserProjection.class, p)).
                collect(Collectors.toList()));

    }
}
