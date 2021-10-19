package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;

}
