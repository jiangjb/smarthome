		package com.smarthome.imcp.dao.model.bo;
 
        import com.smarthome.imcp.dao.model.AbstractData;

 		public class Dat extends AbstractData
 		{
		    private Integer id;
		    private String modelid;
		    private String lineno;
			private String dat;

		   public Dat()
		   {
		   }

			public Integer getId() {
				return id;
			}
	
			public void setId(Integer id) {
				this.id = id;
			}
	
			public String getModelid() {
				return modelid;
			}
	
			public void setModelid(String modelid) {
				this.modelid = modelid;
			}
	
			public String getLineno() {
				return lineno;
			}
	
			public void setLineno(String lineno) {
				this.lineno = lineno;
			}
	
			public String getDat() {
				return dat;
			}
	
			public void setDat(String dat) {
				this.dat = dat;
			}
			
		}
