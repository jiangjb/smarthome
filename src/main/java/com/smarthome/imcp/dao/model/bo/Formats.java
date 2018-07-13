		  package com.smarthome.imcp.dao.model.bo;
 
          import com.smarthome.imcp.dao.model.AbstractData;

 		public class Formats extends AbstractData
 		{
		    private Integer id;
		    private Integer fid;
		    private Integer device_id;
			private String format_name;
			private String format_string;
			private String c3rv;
			private String matchs;

		   public Formats()
		   {
		   }
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			public Integer getFid() {
				return fid;
			}
			public void setFid(Integer fid) {
				this.fid = fid;
			}
			public Integer getDevice_id() {
				return device_id;
			}
			public void setDevice_id(Integer device_id) {
				this.device_id = device_id;
			}
			public String getFormat_name() {
				return format_name;
			}
			public void setFormat_name(String format_name) {
				this.format_name = format_name;
			}
			public String getFormat_string() {
				return format_string;
			}
			public void setFormat_string(String format_string) {
				this.format_string = format_string;
			}
			public String getC3rv() {
				return c3rv;
			}
			public void setC3rv(String c3rv) {
				this.c3rv = c3rv;
			}
			public String getMatchs() {
				return matchs;
			}
			public void setMatchs(String matchs) {
				this.matchs = matchs;
			}
			
		}
