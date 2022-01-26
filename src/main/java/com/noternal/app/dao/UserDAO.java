package com.noternal.app.dao;

import com.noternal.app.model.User;
import java.util.List;

public interface UserDAO {

    public void save(User user);

    public List<User> list();

}
