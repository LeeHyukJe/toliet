package com.wisenut.web.payload;

import com.wisenut.domain.application.commands.CreateBoardCommand;
import com.wisenut.domain.model.team.Team;
import com.wisenut.domain.model.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBoardPayload {
    private String name;
    private String description;
    private long teamId;

    public CreateBoardCommand toCommand (Long userId){
        return new CreateBoardCommand(userId, name, description, teamId);
    }
}
