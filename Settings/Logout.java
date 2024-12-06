package Settings;

import Database.UserDatabase;
import Management.UserManager;
import Start.Start;
import SystemElements.User;

public class Logout {
    
    User user;
    UserManager userManager = new UserManager();
    
    public Logout(User user) {
        this.user = user;
        userManager.logout(user);
        new Start();
    }
    
}
