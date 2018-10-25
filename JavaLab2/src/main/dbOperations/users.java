package main.dbOperations;

public class users {
    private int cardN;
    private String owner;
    private String available;
    private int money;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getAvailable() {

        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getOwner() {

        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getCardN() {

        return cardN;
    }

    public void setCardN(int cardN) {
        this.cardN = cardN;
    }
}
