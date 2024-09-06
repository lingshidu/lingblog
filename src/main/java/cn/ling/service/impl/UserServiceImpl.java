package cn.ling.service.impl;

import cn.ling.entity.User;
import cn.ling.exception.NotFoundException;
import cn.ling.mapper.UserMapper;
import cn.ling.service.UserService;
import cn.ling.util.HashUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户业务层接口实现类
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserMapper userMapper;

	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		return user;
	}

	@Override
	public User findUserByUsernameAndPassword(String username, String password) {
		User user = userMapper.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		if (!HashUtils.matchBc(password, user.getPassword())) {
			throw new UsernameNotFoundException("密码错误");
		}
		return user;
	}

	@Override
	public User findUserById(Long id) {
		User user = userMapper.findById(id);
		if (user == null) {
			throw new NotFoundException("用户不存在");
		}
		return user;
	}
}
