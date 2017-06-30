package com.xhld.authenticationservice.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xhld.authenticationservice.model.entity.SysUser;
import com.xhld.authenticationservice.model.entity.SysUserRole;
@Repository
public interface SysUserRoleDao extends JpaRepository<SysUserRole, Long> {
    
//	SysUser findByLoginNameLike(String name);
//
//	SysUser readByLoginName(String name);
//
//    List<SysUser> getByCreatedateLessThan(Date star);
    
}