package org.frisbeemall.service;

import org.frisbeemall.dao.ProductDao;
import org.frisbeemall.dao.UserDao;
import org.frisbeemall.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public int addUser(User user) {
        user.setStatus(0);
        return userDao.insert(user);

    }

    public List<User> getUsers() {
        List<User> users=userDao.selectList(null);
        return users;
    }
    @Transactional
    public int chooseUser(long id) {
        User user=userDao.selectById(id);
        user.setStatus(1);
        return  userDao.updateById(user);
    }
}
