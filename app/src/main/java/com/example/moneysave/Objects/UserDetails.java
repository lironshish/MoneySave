package com.example.moneysave.Objects;

import com.example.moneysave.Server.ServerCommunicator;
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

    public UserDetails add_Account(InstanceId myAccount) {
        ((ArrayList<InstanceId>) this.getInstanceAttributes().get(DataManager.KEY_MY_ACCOUNTS)).add(myAccount);
        updateOnServer();
        return this;
    }

    public void remove_Account(InstanceId myAccount) {
        ((ArrayList<InstanceId>) this.getInstanceAttributes().get(DataManager.KEY_MY_ACCOUNTS)).remove(myAccount);
        updateOnServer();
    }
    public void updateOnServer(){
        ServerCommunicator.getInstance().updateInstanceDetails(
                this.getInstanceId().getDomain(),
                this.getInstanceId().getId(),
                DataManager.getDataManager().getMyUser().getUserId().getDomain(),
                DataManager.getDataManager().getMyUser().getUserId().getEmail(),
                this);
    }

}
