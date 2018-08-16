package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.model.bo.Brands;

import java.io.Serializable;
import java.util.List;

public abstract interface BrandsDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{

	String findModelByid(int id);

	List findAllBrands(int deviceId);

	
}

