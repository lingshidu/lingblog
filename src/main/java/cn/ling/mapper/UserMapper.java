package cn.ling.mapper;

import cn.ling.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户持久层接口
 */
@Mapper
@Repository
public interface UserMapper {
	/**
	 * 按用户名查询User
	 * @param username username
	 * @return User
	 */
	User findByUsername(String username);

	/**
	 * 根据id查询用户信息
	 * @param id id
	 * @return User
	 */
	User findById(Long id);
}
