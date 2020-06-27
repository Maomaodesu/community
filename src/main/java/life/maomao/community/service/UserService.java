package life.maomao.community.service;

import life.maomao.community.mapper.UserMapper;
import life.maomao.community.model.User;
import life.maomao.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user){
        UserExample userExampleForSelect = new UserExample();
        userExampleForSelect.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> userInDBList = userMapper.selectByExample(userExampleForSelect);

        if(userInDBList.size() == 0){ //拿到的user数据库中没有，新建一个对象
            //创建时间
            user.setGmtCreate(System.currentTimeMillis());
            //修改时间
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else { //拿到数据库中有的user对象userInDB，更新这个对象
            User userInDB = userInDBList.get(0);
            User UpdateUserInDB = new User();
            //修改时间
            UpdateUserInDB.setGmtModified(System.currentTimeMillis());
            //用户头像
            UpdateUserInDB.setAvatarUrl(user.getAvatarUrl());
            //用户名
            UpdateUserInDB.setName(user.getName());
            //用户token
            UpdateUserInDB.setToken(user.getToken());
            UserExample userExampleForUpdate = new UserExample();
            userExampleForUpdate.createCriteria()
                    .andIdEqualTo(userInDB.getId());
            userMapper.updateByExampleSelective(UpdateUserInDB,userExampleForUpdate);
        }
    }
}
