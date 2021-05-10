/**
 * @author 51937
 * @date 2021年4月20日
 * @version V1.0
 */
package com.lzh.authenticationservice.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import com.lzh.authenticationservice.mapper.provider.UserProvider;
import com.lzh.authenticationservice.model.dto.UserDto;
import com.lzh.authenticationservice.model.entity.UserEntity;

/**
 * @author 51937
 * @date 2021年4月20日
 */
@Mapper
public interface UserMapper {
	
	// @Delete("DELETE FROM meet_equipment WHERE equipment_id =#{equipmentId}")
	// int delete(EquipmentEntity dept);
	//
	// @Insert("insert into meet_place_equipment_relation(place_id, equipment_id,
	// type) values(#{placeId}, #{equipmentId}, #{type})")
	// int addEquipment(MeetPlaceEquipmentRelationEntity placeEquipment);
	//
	@SelectProvider(type = UserProvider.class, method = "loadUserByUsername")
	public UserEntity loadUserByUsername(Map map);
	
	@SelectProvider(type = UserProvider.class, method = "queryUser")
    public List<UserDto> queryUser(Map map);

	// @UpdateProvider(type = EquipmentInfoProvider.class, method =
	// "updateEquipment")
	// public int updateEquipment(@Param("department")EquipmentEntity department);
}
