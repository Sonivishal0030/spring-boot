package com.learn.domain.projection;

import com.learn.domain.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(types = {User.class})
public interface UserProjection {
    String getName();
    String getEmail();
    Date getCreatedDate();
}
