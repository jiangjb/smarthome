		  package com.smarthome.imcp.dao.model.bo;
 
          import com.smarthome.imcp.dao.model.AbstractData;

 		public class Acuser extends AbstractData
 		{
		    private Integer id;
		    private String mac;
		    private String kfid;
			private String conoff;
			private String cmode;
			private String ctemp;
			private String cwind;
			private String cwinddir;

		   public Acuser()
		   {
		   }
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			public String getMac() {
				return mac;
			}
			public void setMac(String mac) {
				this.mac = mac;
			}
			public String getKfid() {
				return kfid;
			}
			public void setKfid(String kfid) {
				this.kfid = kfid;
			}
			public String getConoff() {
				return conoff;
			}
			public void setConoff(String conoff) {
				this.conoff = conoff;
			}
			public String getCmode() {
				return cmode;
			}
			public void setCmode(String cmode) {
				this.cmode = cmode;
			}
			public String getCtemp() {
				return ctemp;
			}
			public void setCtemp(String ctemp) {
				this.ctemp = ctemp;
			}
			public String getCwind() {
				return cwind;
			}
			public void setCwind(String cwind) {
				this.cwind = cwind;
			}
			public String getCwinddir() {
				return cwinddir;
			}
			public void setCwinddir(String cwinddir) {
				this.cwinddir = cwinddir;
			}
			
		}
