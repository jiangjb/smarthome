package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.dao.CommonsDaoIface;
import java.io.Serializable;
import java.util.List;

public abstract interface FormatsDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{

	List findFsByfid(int m_format_id , int device_id);

	List findFormatsInfo(int device_id);

}

