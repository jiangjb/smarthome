package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;


public abstract interface FormatsServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{

	List findFsByfid(int m_format_id , int device_id);

	List findFormatsInfo(int device_id);


}

