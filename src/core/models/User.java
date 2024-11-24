
package core.models;

import java.util.ArrayList;

public class User {
    
    private int id;
    private String firstname;
    private String lastname;
    private int age;
    private ArrayList<Account> accounts;

    public User(int id, String firstname, String lastname, int age) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.accounts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }
    
    public int getNumAccounts() {
        return this.accounts.size();
    }
    
    public void addAccount(Account account) {
        this.accounts.add(account);
    }
    
}
