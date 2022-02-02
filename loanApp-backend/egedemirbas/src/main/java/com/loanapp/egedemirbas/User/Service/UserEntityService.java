package com.loanapp.egedemirbas.User.Service;

import com.loanapp.egedemirbas.User.Dao.UserDao;
import com.loanapp.egedemirbas.User.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserEntityService {

    private final UserDao userDao;

    public List<User> findAllUsers(){
        return userDao.findAllUsers();
    }

    public User findUserById(Long id){
        return userDao.findUserById(id);
    }

    public User findUserByIdentityNumber(Long identityNumber){
        return userDao.findUserByIdentityNumber(identityNumber);
    }

    public User saveUser(User user){
        return userDao.save(user);
    }

    public void deleteUserById(User user){
        userDao.delete(user);
    }
}
