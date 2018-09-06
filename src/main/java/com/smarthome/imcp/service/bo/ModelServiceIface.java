package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;


public abstract interface ModelServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{

	List findAllType(int deviceId);

	List findByType(String label);

	List findTestCode(int id,int device_id);

	int findByLabel(String label);

	String findModelidByLabel(String label);

	List findFormatIdByMcode(int device_id , String string);

	int findKSByfid(int fid);

	List findModelidByid(int labelId);

	List findModelInfo(int device_id);

}

