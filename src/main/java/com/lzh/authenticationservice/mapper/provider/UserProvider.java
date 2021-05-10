/**
 * @author 51937
 * @date 2021年4月20日
 * @version V1.0
 */
package com.lzh.authenticationservice.mapper.provider;

import java.util.Map;

import com.github.pagehelper.util.StringUtil;

/**
 * @author 51937
 * @date 2021年4月20日
 */
public class UserProvider {

	private String userInfo = " au.user_id, au.username, au.password, au.name, au.account_non_expired, au.account_non_locked, au.credentials_non_expired, au.enabled, au.phone_num ";

	public String loadUserByUsername(Map map) {
		 StringBuffer sql = new StringBuffer(" SELECT " + userInfo
		 + " FROM auth_user au "
		 + " where 1=1 "
		 );
		 if(StringUtil.isNotEmpty((String) map.get("username"))) {
		 sql.append(String.format(" and au.username = '%s'", (String)
		 map.get("username")));
		 }
		 return sql.toString();
	}

	public String queryUser(Map map) {
		StringBuffer sql = new StringBuffer(" SELECT " + userInfo + " FROM auth_user au " + " where 1=1 ");
		if (StringUtil.isNotEmpty((String) map.get("username"))) {
			sql.append(String.format(" and au.username = '%s'", (String) map.get("username")));
		}
		if (StringUtil.isNotEmpty((String) map.get("name"))) {
			sql.append(String.format(" and au.name like '%s'", "%" + (String) map.get("name") + "%"));
		}
		if (map.get("userId") != null) {
			sql.append(" and au.user_id = " + map.get("userId"));
		}
		return sql.toString();
	}

	// public String updateEquipment(@Param("department")EquipmentEntity department)
	// {
	// SQL sql = new SQL(){{
	// UPDATE("meet_equipment");
	//
	// if (department.getDeleteFlag() != null){
	// SET(" delete_flag = #{department.deleteFlag} ");
	// }
	// if(StringUtil.isNotEmpty(department.getEquipmentName())) {
	// SET(" equipment_name = #{department.equipmentName} ");
	// }
	// if(StringUtil.isNotEmpty(department.getEquipmentAddr())) {
	// SET(" equipment_addr = #{department.equipmentAddr} ");
	// }
	// if(StringUtil.isNotEmpty(department.getIp())) {
	// SET(" ip = #{department.ip} ");
	// }
	// if(StringUtil.isNotEmpty(department.getPort())) {
	// SET(" port = #{department.port} ");
	// }
	// if(department.getEquipmentType() != null ) {
	// SET(" equipment_type = #{department.equipmentType} ");
	// }
	//
	// WHERE("equipment_id = #{department.equipmentId}");
	// }};
	//
	// return sql.toString();
	// }

}
