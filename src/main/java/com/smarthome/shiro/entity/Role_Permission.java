package com.smarthome.shiro.entity;

		public class Role_Permission
		{
			private Integer id;
			private Integer permission_id;
			private Integer role_id;
			
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			public Integer getPermission_id() {
				return permission_id;
			}
			public void setPermission_id(Integer permission_id) {
				this.permission_id = permission_id;
			}
			public Integer getRole_id() {
				return role_id;
			}
			public void setRole_id(Integer role_id) {
				this.role_id = role_id;
			}
		}
