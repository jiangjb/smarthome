package com.smarthome.imcp.service.system;

import com.smarthome.imcp.dao.vo.system.FileVo;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public abstract interface FileServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract ByteArrayInputStream getFile(String paramString)
    throws IOException;

  public abstract String saveToTemp(File paramFile, String paramString1, String paramString2)
    throws IOException;

  public abstract String saveToDir(File paramFile, String paramString1, String paramString2)
    throws IOException;

  public abstract FileVo copyTempFileToDir(String paramString1, String paramString2, String paramString3);

  public abstract FileVo compressImage(FileVo paramFileVo, int paramInt1, int paramInt2);

  public abstract boolean deleteTempDirectory(String paramString);

  public abstract boolean deleteFile(String paramString);

  public abstract void copyToDir(File paramFile, String paramString1, String paramString2);

  public abstract String readFile(File paramFile, String paramString);

  public abstract void writeFile(String paramString1, String paramString2, String paramString3);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.system.FileServiceIface
 * JD-Core Version:    0.6.2
 */