		  package com.smarthome.imcp.dao.model.bo;
 
          import com.smarthome.imcp.dao.model.AbstractData;

 		public class InfraredTimer extends AbstractData
 		{
		    private Integer id;
		    private BoUsers boUsers;
		    private BoModel boModel;
		    private String mac;
			private String infraredCode;
			private int control_command;
			private int deviceType;
			private String name;//8-14  用于存放功能的名字或按键
			
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			public BoUsers getBoUsers() {
				return boUsers;
			}
			public void setBoUsers(BoUsers boUsers) {
				this.boUsers = boUsers;
			}
			public BoModel getBoModel() {
				return boModel;
			}
			public void setBoModel(BoModel boModel) {
				this.boModel = boModel;
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
			public int getControl_command() {
				return control_command;
			}
			public void setControl_command(int control_command) {
				this.control_command = control_command;
			}
			public int getDeviceType() {
				return deviceType;
			}
			public void setDeviceType(int deviceType) {
				this.deviceType = deviceType;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			
			
		}
