package cn.ling.service;

import cn.ling.entity.User;

public interface UserService {
	/**
	 * 根据用户名和密码查询用户信息
	 * @param username 用户名
	 * @param password 密码
	 * @return User
	 */
	User findUserByUsernameAndPassword(String username, String password);

	/**
	 * 根据id查询用户信息
	 * @param id id
	 * @return User
	 */
	User findUserById(Long id);
}
