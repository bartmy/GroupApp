package io.github.bartmy.App.UserProfile.group;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class GroupDTO {
    private Integer id;
    private String groupName;

    public GroupDTO(Group group){
        this.id = group.getId();
        this.groupName = group.getGroupName();
    }

}
