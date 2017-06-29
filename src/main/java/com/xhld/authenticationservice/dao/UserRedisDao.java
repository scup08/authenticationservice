package com.xhld.authenticationservice.dao;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.xhld.authenticationservice.model.entity.SysUser;

@Repository
public class UserRedisDao {

	@Autowired
	RedisTemplate<Object,Object> redisTemplate;
	
	@Resource(name="redisTemplate")
	ValueOperations<Object,Object> valOps;
	
	public void save(String token ,SysUser user){
		valOps.set(token, user);
	}
	
	public SysUser getUser(String token){
		return (SysUser) valOps.get(token);
	}	
}