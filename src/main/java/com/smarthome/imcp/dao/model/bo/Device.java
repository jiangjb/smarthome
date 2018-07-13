		  package com.smarthome.imcp.dao.model.bo;
 
          import com.smarthome.imcp.dao.model.AbstractData;

 		public class Device extends AbstractData
 		{
		    private Integer id;
		    private String device_name;
			private String description;

		   public Device()
		   {
		   }
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			public String getDevice_name() {
				return device_name;
			}
			public void setDevice_name(String device_name) {
				this.device_name = device_name;
			}
			public String getDescription() {
				return description;
			}
			public void setDescription(String description) {
				this.description = description;
			}
			
		}
