package coffeshop.service;

import coffeshop.model.User;

import java.util.List;

public interface IUserService {

    User adminlogin(String username, String password);

    List<User> getUser();

    User getUserByID(int id);
}
