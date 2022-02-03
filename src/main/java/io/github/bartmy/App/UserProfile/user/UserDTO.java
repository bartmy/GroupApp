package io.github.bartmy.App.UserProfile.user;
import lombok.*;

@Getter
@Setter
class UserDTO {
    private Integer id;
    private String username;

    public UserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
    }

}
