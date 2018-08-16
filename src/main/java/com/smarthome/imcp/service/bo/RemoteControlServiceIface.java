package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.RemoteControl;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;


public abstract interface RemoteControlServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{

	List<RemoteControl> findByUserId(int userid);

	List<RemoteControl> findByMiniId(Integer valueOf);

	RemoteControl findByUML(Integer userId, Integer miniBlackId, String m_label);

	List<RemoteControl> getBy(int userId, String modelId);

}

