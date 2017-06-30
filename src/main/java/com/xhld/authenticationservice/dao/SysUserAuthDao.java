package com.xhld.authenticationservice.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xhld.authenticationservice.model.entity.SysUser;
import com.xhld.authenticationservice.model.entity.SysUserAuth;
@Repository
public interface SysUserAuthDao extends JpaRepository<SysUserAuth, Long> {
    
//	SysUserAuth findByLoginNameLike(String name);
//
//	SysUserAuth readByLoginName(String name);
//
//    List<SysUserAuth> getByCreatedateLessThan(Date star);
    
}