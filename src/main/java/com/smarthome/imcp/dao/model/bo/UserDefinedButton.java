		package com.smarthome.imcp.dao.model.bo;

		import com.smarthome.imcp.dao.model.AbstractData;
 
		public class UserDefinedButton extends AbstractData{
			private Integer id;
			private UserDefinedRemoteControl userDefinedRemoteControl;
			private Integer buttonId;
			private String buttonName;
			private String infraredCode;
			
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			public UserDefinedRemoteControl getUserDefinedRemoteControl() {
				return userDefinedRemoteControl;
			}
			public void setUserDefinedRemoteControl(UserDefinedRemoteControl userDefinedRemoteControl) {
				this.userDefinedRemoteControl = userDefinedRemoteControl;
			}
			public Integer getButtonId() {
				return buttonId;
			}
			public void setButtonId(Integer buttonId) {
				this.buttonId = buttonId;
			}
			public String getButtonName() {
				return buttonName;
			}
			public void setButtonName(String buttonName) {
				this.buttonName = buttonName;
			}
			public String getInfraredCode() {
				return infraredCode;
			}
			public void setInfraredCode(String infraredCode) {
				this.infraredCode = infraredCode;
			}
			
         }

