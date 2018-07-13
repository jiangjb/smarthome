		  package com.smarthome.imcp.dao.model.bo;
 
          import com.smarthome.imcp.dao.model.AbstractData;

 		public class Brands extends AbstractData
 		{
		    private Integer id;
		    private Integer device_id;
		    private String brandname;
			private String ebrandname;
			private Integer model_q;
			private String model_list;

		   public Brands()
		   {
		   }
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			public Integer getDevice_id() {
				return device_id;
			}
			public void setDevice_id(Integer device_id) {
				this.device_id = device_id;
			}
			public String getBrandname() {
				return brandname;
			}
			public void setBrandname(String brandname) {
				this.brandname = brandname;
			}
			public String getEbrandname() {
				return ebrandname;
			}
			public void setEbrandname(String ebrandname) {
				this.ebrandname = ebrandname;
			}
			public Integer getModel_q() {
				return model_q;
			}
			public void setModel_q(Integer model_q) {
				this.model_q = model_q;
			}
			public String getModel_list() {
				return model_list;
			}
			public void setModel_list(String model_list) {
				this.model_list = model_list;
			}
		}
