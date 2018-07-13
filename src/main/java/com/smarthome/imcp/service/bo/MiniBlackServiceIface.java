package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.MiniBlack;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;


public abstract interface MiniBlackServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{

	List<MiniBlack> findByUserId(int userid);


}

