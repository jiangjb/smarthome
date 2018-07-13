		  package com.smarthome.imcp.dao.model.bo;
 
          import java.util.Date;
          import com.smarthome.imcp.dao.model.AbstractData;

 		public class MiniBlack extends AbstractData
 		{
		    private Integer id;
		    private Integer userid;
		    private String nickName;
			private String macAddr;
			private String ip;
			private String status;
			private String timestamp;
//			private Date create_date;
			private String create_date;
			
			
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			public String getNickName() {
				return nickName;
			}
			public void setNickName(String nickName) {
				this.nickName = nickName;
			}
			public String getMacAddr() {
				return macAddr;
			}
			public void setMacAddr(String macAddr) {
				this.macAddr = macAddr;
			}
			public String getIp() {
				return ip;
			}
			public void setIp(String ip) {
				this.ip = ip;
			}
			public Integer getUserid() {
				return userid;
			}
			public void setUserid(Integer userid) {
				this.userid = userid;
			}
			public String getStatus() {
				return status;
			}
			public void setStatus(String status) {
				this.status = status;
			}
			public String getTimestamp() {
				return timestamp;
			}
			public void setTimestamp(String timestamp) {
				this.timestamp = timestamp;
			}
			public String getCreate_date() {
				return create_date;
			}
			public void setCreate_date(String create_date) {
				this.create_date = create_date;
			}
			
			
		}
