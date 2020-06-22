package life.maomao.community.mapper;

import life.maomao.community.model.User;
import org.apache.ibatis.annotations.*;

/**
 * created by Maomao on 2020/6/16
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User getUserToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User getUserByUserId(@Param("id")Integer id);

//    @Select("select count(1) from user where account_id = #{accountId}")
//    Integer isUserExist(User user);

    @Update("update user set  gmt_modified = #{gmtModified}, avatar_url = #{avatarUrl}, name = #{name},token = #{token} WHERE account_id = #{accountId}")
    void update(User user);

    @Select("select * from user where account_id = #{accountId}")
    User getUserByAccountId(@Param("accountId")String accountId);
}
