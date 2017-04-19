package hotelReception.domain;

/**
 * Created by suman on 4/18/2017.
 */
public class SystemUsers {
    String userName, password;

    public SystemUsers(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
