package org.shruthipattanasetty.foodbankdonation.dtos;

import lombok.Getter;
import lombok.Setter;
import org.shruthipattanasetty.foodbankdonation.models.User;

@Getter
@Setter
public class UserDto {
    private String message;
    private String status;
    private User user;
}
