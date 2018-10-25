package main;

import main.dbOperations.users;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import java.sql.SQLException;

import static main.dbOperations.Operations.GetUser;
import static main.dbOperations.Operations.reduceMoney;

@ManagedBean
@SessionScoped

public class bean
{
    private users tracked = new users();
    private String errmsg;
    private int cost = 1000;
    private boolean tran = false;

    public boolean isTran() {
        return tran;
    }

    public void setTran(boolean tran) {
        this.tran = tran;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public users getTracked() {
        return tracked;
    }

    public void setTracked(users tracked) {
        this.tracked = tracked;
    }

    public void isValid() throws SQLException {
        tracked = GetUser(tracked.getOwner(),tracked.getPassword());
    }

    public void makeTransaction()
    {
        tran =reduceMoney(tracked.getOwner(),cost);
    }
}
