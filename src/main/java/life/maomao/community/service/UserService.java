package life.maomao.community.service;

import life.maomao.community.mapper.UserMapper;
import life.maomao.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user){
        User userInDB = userMapper.getUserByAccountId(user.getAccountId());
        if(userInDB == null){ //拿到的user数据库中没有，新建一个对象
            //创建时间
            user.setGmtCreate(System.currentTimeMillis());
            //修改时间
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else { //拿到的user数据库中有，更新这个对象
            //修改时间
            userInDB.setGmtModified(System.currentTimeMillis());
            //用户头像
            userInDB.setAvatarUrl(user.getAvatarUrl());
            //用户名
            userInDB.setName(user.getName());
            //用户token
            userInDB.setToken(user.getToken());
            userMapper.update(userInDB);
        }
    }
}
