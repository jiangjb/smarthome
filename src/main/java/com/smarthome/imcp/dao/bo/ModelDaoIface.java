package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.dao.CommonsDaoIface;
import java.io.Serializable;
import java.util.List;

public abstract interface ModelDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{

	List findAllType(int deviceId);

	List findByType(String label);

	List findTestCode(int id,int device_id);

	int findByLabel(String label);

	String findModelidByLabel(String label);

	List findFormatIdByMcode(int device_id , String m_code);

	int findKSByfid(int fid);

	List findModelidByid(int labelId);

	List findModelInfo(int device_id);

	
}

