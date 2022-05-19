package com.example.moneysave.Objects;

import com.example.moneysave.Server.boundaries.CreatedBy;
import com.example.moneysave.Server.boundaries.InstanceBoundary;
import com.example.moneysave.Server.boundaries.InstanceId;
import com.example.moneysave.Server.boundaries.UserId;
import com.example.moneysave.tools.DataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDetails extends InstanceBoundary {

    public UserDetails(String password) {
        this.setActive(true);
        this.setCreatedBy(new CreatedBy(DataManager.getDataManager().getMyUser().getUserId()));
        this.setType(UserDetails.class.getSimpleName());
        this.setName(UserDetails.class.getSimpleName() + DataManager.getDataManager().getMyUser().getUserId().getEmail());
        this.setInstanceAttributes(new HashMap<>());
        this.getInstanceAttributes().put(DataManager.KEY_PASSWORD , password);
        this.getInstanceAttributes().put(DataManager.KEY_MY_ACCOUNTS , new ArrayList<InstanceId>());
    }


    public UserDetails(InstanceBoundary instanceBoundary) {
        super(instanceBoundary.getInstanceId() ,
                instanceBoundary.getType() ,
                instanceBoundary.getName() ,
                instanceBoundary.getActive(),
                instanceBoundary.getCreatedTimestamp(),
                instanceBoundary.getCreatedBy(),
                instanceBoundary.getLocation(),
                instanceBoundary.getInstanceAttributes());

    }
    public ArrayList<InstanceId> receive_myAccounts() {
        return (ArrayList<InstanceId>) this.getInstanceAttributes().get(DataManager.KEY_MY_ACCOUNTS);
    }

    public UserDetails update_myAccounts(ArrayList<InstanceId> myAccounts) {
        this.getInstanceAttributes().put(DataManager.KEY_MY_ACCOUNTS , myAccounts);
        return this;
    }
}
