		  package com.smarthome.imcp.dao.model.bo;
 
          import com.smarthome.imcp.dao.model.AbstractData;

 		public class RemoteControl extends AbstractData
 		{
		    private Integer id;
		    private Integer userId;
		    private Integer miniBlackId;
		    private String nickName;
			private String type;
			private int m_key_squency;//7-20
			private String modelid;//7-20
			private String label;//7-23
			private Boolean isAuthorized;//8-13
			
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			public Integer getUserId() {
				return userId;
			}
			public void setUserId(Integer userId) {
				this.userId = userId;
			}
			public Integer getMiniBlackId() {
				return miniBlackId;
			}
			public void setMiniBlackId(Integer miniBlackId) {
				this.miniBlackId = miniBlackId;
			}
			public String getNickName() {
				return nickName;
			}
			public void setNickName(String nickName) {
				this.nickName = nickName;
			}
			public String getType() {
				return type;
			}
			public void setType(String type) {
				this.type = type;
			}
			public int getM_key_squency() {
				return m_key_squency;
			}
			public void setM_key_squency(int m_key_squency) {
				this.m_key_squency = m_key_squency;
			}
			public String getModelid() {
				return modelid;
			}
			public void setModelid(String modelid) {
				this.modelid = modelid;
			}
			public String getLabel() {
				return label;
			}
			public void setLabel(String label) {
				this.label = label;
			}
			public Boolean getIsAuthorized() {
				return isAuthorized;
			}
			public void setIsAuthorized(Boolean isAuthorized) {
				this.isAuthorized = isAuthorized;
			}
			
		}
