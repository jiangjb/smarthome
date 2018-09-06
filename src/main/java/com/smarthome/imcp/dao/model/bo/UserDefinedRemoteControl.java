		package com.smarthome.imcp.dao.model.bo;

		import com.smarthome.imcp.dao.model.AbstractData;
 
		public class UserDefinedRemoteControl extends AbstractData{
			private Integer id;
			private BoUsers boUsers;
			private Integer miniBlackId;
			private BoRoom boRoom;
			private String nickName;
			private String deviceType;
			private String buttonNames;
			private Boolean isAuthorized;

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
			public Integer getMiniBlackId() {
				return miniBlackId;
			}
			public void setMiniBlackId(Integer miniBlackId) {
				this.miniBlackId = miniBlackId;
			}
			public BoRoom getBoRoom() {
				return boRoom;
			}
			public void setBoRoom(BoRoom boRoom) {
				this.boRoom = boRoom;
			}
			public String getNickName() {
				return nickName;
			}
			public void setNickName(String nickName) {
				this.nickName = nickName;
			}
			public String getDeviceType() {
				return deviceType;
			}
			public void setDeviceType(String deviceType) {
				this.deviceType = deviceType;
			}
			public String getButtonNames() {
				return buttonNames;
			}
			public void setButtonNames(String buttonNames) {
				this.buttonNames = buttonNames;
			}
			public Boolean getIsAuthorized() {
				return isAuthorized;
			}
			public void setIsAuthorized(Boolean isAuthorized) {
				this.isAuthorized = isAuthorized;
			}
			
         }

