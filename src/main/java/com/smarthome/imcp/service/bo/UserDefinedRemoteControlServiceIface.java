package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.UserDefinedRemoteControl;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;


public abstract interface UserDefinedRemoteControlServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{

	UserDefinedRemoteControl findBy(int userId, int miniBlackId, String nickName, int roomId, String buttonNames);

	List<UserDefinedRemoteControl> findByUserId(int userid);

	List<UserDefinedRemoteControl> findByMiniId(Integer id);

	List<UserDefinedRemoteControl> getByRoomUser(int userId, String rmCode);

   
}

