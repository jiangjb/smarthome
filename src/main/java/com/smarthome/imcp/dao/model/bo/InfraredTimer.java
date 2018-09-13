		  package com.smarthome.imcp.dao.model.bo;
 
          import com.smarthome.imcp.dao.model.AbstractData;

 		public class InfraredTimer extends AbstractData
 		{//表用于存放 空调遥控器的定时开关的信息
		    private Integer id;
		    private RemoteControl rcontrol;
		    private String mac;
			private String infraredCode;
			private String name;//开  或   关
			private String week;
			private String time;
			
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			public RemoteControl getRcontrol() {
				return rcontrol;
			}
			public void setRcontrol(RemoteControl rcontrol) {
				this.rcontrol = rcontrol;
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
			public String getWeek() {
				return week;
			}
			public void setWeek(String week) {
				this.week = week;
			}
			public String getTime() {
				return time;
			}
			public void setTime(String time) {
				this.time = time;
			}
			
		}
