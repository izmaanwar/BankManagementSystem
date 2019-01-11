/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Faisal
 */
public class Account {
    private String ID;
  private String AccountNo ;
    private String AccountTitle;
    private String AccountType;
    private double AccountBalance;
    private String date;
    public Account(){}

    public Account(String ID, String AccountNo, String AccountTitle, String AccountType, double AccountBalance, String date) {
        this.ID = ID;
        this.AccountNo = AccountNo;
        this.AccountTitle = AccountTitle;
        this.AccountType = AccountType;
        this.AccountBalance = AccountBalance;
        this.date = date;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setAccountNo(String AccountNo) {
        this.AccountNo = AccountNo;
    }

    public void setAccountTitle(String AccountTitle) {
        this.AccountTitle = AccountTitle;
    }

    public void setAccountType(String AccountType) {
        this.AccountType = AccountType;
    }

    public void setAccountBalance(double AccountBalance) {
        this.AccountBalance = AccountBalance;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getID() {
        return ID;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public String getAccountTitle() {
        return AccountTitle;
    }

    public String getAccountType() {
        return AccountType;
    }

    public double getAccountBalance() {
        return AccountBalance;
    }

    public String getDate() {
        return date;
    }

}
