package model;

public class Admin {
    private String login;
    private String mdp;

    public Admin(String login, String mdp) {
        this.login = login;
        this.mdp = mdp;
    }
    
    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }
}
