		  package com.smarthome.imcp.dao.model.bo;
 
          import com.smarthome.imcp.dao.model.AbstractData;

 		public class AirconditionSleep extends AbstractData
 		{
		    private Integer id;
		    private RemoteControl rcontrol;
		    private Integer temp_id;
		    private Integer mode_id;
		    private Integer windspeed_id;
		    private String week;//9-18
		    private String time;
		    private String infraredCode;
		    private String mac;
		    private String times;//9-18 睡眠执行的次数，分一次和n次（重复和只执行一次）
		    private String status;//9-18 单个睡眠的开关状态
		    private String acStatus;
		    
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
			public Integer getTemp_id() {
				return temp_id;
			}
			public void setTemp_id(Integer temp_id) {
				this.temp_id = temp_id;
			}
			public Integer getMode_id() {
				return mode_id;
			}
			public void setMode_id(Integer mode_id) {
				this.mode_id = mode_id;
			}
			public Integer getWindspeed_id() {
				return windspeed_id;
			}
			public void setWindspeed_id(Integer windspeed_id) {
				this.windspeed_id = windspeed_id;
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
			public String getInfraredCode() {
				return infraredCode;
			}
			public void setInfraredCode(String infraredCode) {
				this.infraredCode = infraredCode;
			}
			public String getMac() {
				return mac;
			}
			public void setMac(String mac) {
				this.mac = mac;
			}
			public String getTimes() {
				return times;
			}
			public void setTimes(String times) {
				this.times = times;
			}
			public String getStatus() {
				return status;
			}
			public void setStatus(String status) {
				this.status = status;
			}
			public String getAcStatus() {
				return acStatus;
			}
			public void setAcStatus(String acStatus) {
				this.acStatus = acStatus;
			}
			
		}
