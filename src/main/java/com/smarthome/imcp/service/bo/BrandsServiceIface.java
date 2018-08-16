package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.Brands;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;


public abstract interface BrandsServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{

	String findModelByid(int id);

	List findAllBrands(int deviceId);

}

