		  package com.smarthome.imcp.dao.model.bo;
 
          import com.smarthome.imcp.dao.model.AbstractData;

 		public class InfraredInfo extends AbstractData
 		{//表用于存放定时器里的红外设备执行的码
		    private Integer id;
		    private BoModel boModel;
		    private BoUsers boUsers;
		    private String mac;
			private String infraredCode;
			private String name;
			
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			public BoModel getBoModel() {
				return boModel;
			}
			public void setBoModel(BoModel boModel) {
				this.boModel = boModel;
			}
			public BoUsers getBoUsers() {
				return boUsers;
			}
			public void setBoUsers(BoUsers boUsers) {
				this.boUsers = boUsers;
			}
			public String getMac() {
				return mac;
			}
			public void setMac(String mac) {
				this.mac = mac;
			}
			public String getInfraredCode() {
				return infraredCode;
			}
			public void setInfraredCode(String infraredCode) {
				this.infraredCode = infraredCode;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			
		}
