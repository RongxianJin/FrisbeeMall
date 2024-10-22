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

    public int addUser(String username,String password,String phone,String location) {


        return userDao.insertUser(username, password, phone, location);

    }

    public List<User> getUsers(Integer productId) {
        List<User> users=userDao.getUserById(productId);

        return users;
    }
    @Transactional
    public int chooseUser(long id) {
        User user=userDao.selectById(id);
        user.setStatus(1);
        return  userDao.updateById(user);
    }

    public List<User> getAllUsers(){
        List<User> users =userDao.selectList(null);
        return users;
    }


}
