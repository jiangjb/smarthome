/*    */ package com.smarthome.imcp.action.xing;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/*    */ 
/*    */ import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
		import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.smarthome.imcp.common.MailUtil;
import com.smarthome.imcp.dao.model.bo.BoDevice;
/*    */ 
/*    */ public class Test
/*    */ {
		   private static int a=1;
		   public static void test() {System.out.println("test");};
/*    */   public static void main(String[] args) throws IOException
/*    */   {
			//写文件
//			File file = new File("c:\\\\a.txt");
//		    FileWriter writer = new FileWriter(file, true);
//		    writer.write("aaaaaa");
//		    writer.close();
		    //时间格式转换
	        Date currentTime = new Date();
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String dateString = formatter.format(currentTime); 
	        System.out.println(dateString);
	        
	        System.out.println(currentTime.getTime());
			}
		}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.action.xing.Test
 * JD-Core Version:    0.6.2
 */