		  package com.smarthome.imcp.dao.model.bo;
 
          import com.smarthome.imcp.dao.model.AbstractData;

 		public class Model extends AbstractData
 		{
		    private Integer id;
		    private Integer device_id;
		    private String m_code;
			private String m_label;
			private String m_search_string;
			private Integer m_format_id;
			private String m_keyfile;
			private Integer m_key_squency;
			private String m_rank;
			private String m_epd;

		   public Model()
		   {
		   }
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			public Integer getDevice_id() {
				return device_id;
			}
			public void setDevice_id(Integer device_id) {
				this.device_id = device_id;
			}
			public String getM_code() {
				return m_code;
			}
			public void setM_code(String m_code) {
				this.m_code = m_code;
			}
			public String getM_label() {
				return m_label;
			}
			public void setM_label(String m_label) {
				this.m_label = m_label;
			}
			public String getM_search_string() {
				return m_search_string;
			}
			public void setM_search_string(String m_search_string) {
				this.m_search_string = m_search_string;
			}
			public Integer getM_format_id() {
				return m_format_id;
			}
			public void setM_format_id(Integer m_format_id) {
				this.m_format_id = m_format_id;
			}
			public String getM_keyfile() {
				return m_keyfile;
			}
			public void setM_keyfile(String m_keyfile) {
				this.m_keyfile = m_keyfile;
			}
			public Integer getM_key_squency() {
				return m_key_squency;
			}
			public void setM_key_squency(Integer m_key_squency) {
				this.m_key_squency = m_key_squency;
			}
			public String getM_rank() {
				return m_rank;
			}
			public void setM_rank(String m_rank) {
				this.m_rank = m_rank;
			}
			public String getM_epd() {
				return m_epd;
			}
			public void setM_epd(String m_epd) {
				this.m_epd = m_epd;
			}
			
		}
