package com.example.moneysave.tools;


import com.example.moneysave.Objects.Account;
import com.example.moneysave.Objects.BankAccount;
import com.example.moneysave.Objects.Goal;
import com.example.moneysave.Objects.MyUser;
import com.example.moneysave.Objects.UserDetails;
import com.example.moneysave.Server.ServerCommunicator;
import com.example.moneysave.Server.boundaries.InstanceBoundary;
import com.example.moneysave.Server.boundaries.UserBoundary;
import com.example.moneysave.call_backs.CreateAndUpdateAccount;
import com.example.moneysave.call_backs.GetAccounts_callback;
import com.example.moneysave.call_backs.LoginCallBack;
import com.example.moneysave.call_backs.ServerCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DataManager {
    private static DataManager _instance = new DataManager();
    public static final String MAIN_DOMAIN = "2022b.Lilach.Laniado";
    public static final String KEY_PASSWORD = "KEY_PASSWORD";
    public static final String KEY_MY_ACCOUNTS = "KEY_MY_ACCOUNTS";
    public static final String KEY_MY_USERS = "KEY_MY_USERS";
    public static final String KEY_MY_CATEGORIES = "KEY_MY_CATEGORIES";
    public static final String KEY_MY_BANKS = "KEY_MY_BANKS";
    private MyUser myUser = null;
    private ServerCallback activeCallBack;

    private DataManager() {
    }


    public void setUser(UserBoundary userBoundary) {
        if (this.activeCallBack == null)
            return;
        if (userBoundary == null) {
            activeCallBack.failed(0);
            return;
        }

        myUser = new MyUser(userBoundary);
        if (activeCallBack instanceof LoginCallBack)
            ((LoginCallBack) activeCallBack).getUser(myUser);
        else
            activeCallBack.failed(0);

    }

    public MyUser getMyUser() {
        return myUser;
    }


    public DataManager setActiveCallBack(ServerCallback activeCallBack) {
        this.activeCallBack = activeCallBack;
        return this;
    }

    public static DataManager getDataManager() {
        return _instance;
    }


    public void getInstancesFromServerByName(InstanceBoundary[] body) {
        if (this.activeCallBack == null)
            return;
        if (body == null || body.length <= 0) {
            this.activeCallBack.empty();
            return;
        }

        List<InstanceBoundary> instanceBoundaries = new ArrayList<>(Arrays.asList(body));
        if (activeCallBack instanceof LoginCallBack)
            ((LoginCallBack) activeCallBack).login(new UserDetails(instanceBoundaries.get(0)));


        //this.instancesCallBack.Instances(instanceBoundaries);
    }

    public void failed(int code) {
        if (this.activeCallBack != null)
            this.activeCallBack.failed(code);
    }

    public void getInstance(InstanceBoundary body) {
        if (this.activeCallBack == null)
            return;
        if (body == null)
            activeCallBack.empty();
        else if (activeCallBack instanceof LoginCallBack)
            ((LoginCallBack) activeCallBack).login(new UserDetails(body));
        else if (activeCallBack instanceof GetAccounts_callback) {
            myUser.getMyAccounts().add(new Account(body));
            ((GetAccounts_callback) activeCallBack).getAccount();
        }
        else if(activeCallBack instanceof CreateAndUpdateAccount){
            serverAddAccount(new Account(body));
        }
    }
    public ArrayList<Account> getMyAccounts() {
        return myUser.getMyAccounts();
    }

    public void addAccount(Account myAccount) {
        ServerCommunicator.getInstance().createInstance(myAccount);
    }
    public void serverAddAccount(Account myAccount) {
        myAccount.add_user(myUser.getUserId());
        myUser.getUserDetails().add_Account(myAccount.getInstanceId());
        myUser.getMyAccounts().add(myAccount);
        ((CreateAndUpdateAccount)activeCallBack).createOkUpdateBegin(myAccount);
    }
    public void removeAccount(Account myAccount) {
        myAccount.remove_user(myUser.getUserId());
        myUser.getUserDetails().remove_Account(myAccount.getInstanceId());
        myUser.getMyAccounts().remove(myAccount);
    }

    public void addAccountCategories(Account myAccount , ArrayList<Goal> categories) {
        myAccount.update_myCategories(categories);
    }
    public void addAccountCategory(Account myAccount , Goal category) {
        myAccount.addCategory(category);
    }
    public ArrayList<Goal> getAccountCategories(Account myAccount) {
        return myAccount.receive_myCategories();
    }

    public void removeAccountCategory(Account myAccount , Goal category) {
        myAccount.removeCategory(category);
    }
    public void addAccountBank(Account myAccount , BankAccount bankAccount) {
        myAccount.addBank(bankAccount);
    }

    public void removeAccountBank(Account myAccount , BankAccount bankAccount) {
        myAccount.removeBank(bankAccount);
    }
    public ArrayList<BankAccount> getAccountBanks(Account myAccount) {
       return myAccount.receive_myBankAccounts();
    }

    public ArrayList<Goal> generateCategories(){
        ArrayList<Goal>categories = new ArrayList<>();

        categories.add(new Goal()
                .setName("FOOD").setMoneyPerMonth(0).setMoneyWested(0).setImage("ic_food"));

        categories.add(new Goal()
                .setName("RECREATIONS").setMoneyPerMonth(0).setMoneyWested(0).setImage("ic_recreations"));

        categories.add(new Goal()
                .setName("CAR").setMoneyPerMonth(0).setMoneyWested(0).setImage("ic_car"));

        categories.add(new Goal()
                .setName("HOME").setMoneyPerMonth(0).setMoneyWested(0).setImage("ic_home"));

        categories.add(new Goal()
                .setName("CLOTHING").setMoneyPerMonth(0).setMoneyWested(0).setImage("ic_clothing"));

        categories.add(new Goal()
                .setName("VARIOUS").setMoneyPerMonth(0).setMoneyWested(0).setImage("ic_various"));
        return categories;
    }
}
