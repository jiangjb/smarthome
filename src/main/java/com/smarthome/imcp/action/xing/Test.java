/*    */ package com.smarthome.imcp.action.xing;
/*    */ 
/*    */ import java.io.PrintStream;
		import java.util.ArrayList;
		import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
		import java.util.Map;
import java.util.Set;

import com.smarthome.imcp.dao.model.bo.BoDevice;
/*    */ 
/*    */ public class Test
/*    */ {
		   private static int a=1;
		   public static void test() {System.out.println("test");};
/*    */   public static void main(String[] args)
/*    */   {
///* 17 */     String s = "15105873889";
///* 18 */     String replaceAll = s.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
///* 19 */     System.err.println(replaceAll);
//			 String str="1";
//			 int a = Integer.parseInt(str);
//			 Integer i=new Integer(a);
//			 System.out.println(Integer.parseInt(str));
			Set set=new HashSet();//不能存放重复的值
			set.add(1);
			set.add(1);
			set.add(2);
//			System.out.println(set);
			Iterator it = set.iterator(); 
			while (it.hasNext()) {  
				  int str = (int) it.next();  
				  System.out.println("set---"+str);
				  for(int i=0;i<10;i++) {
					  System.out.println(i);
					  if(i==5) {
						  break;//跳出for循环
					  }
				  }
//				  System.out.println(str);  
				}
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.action.xing.Test
 * JD-Core Version:    0.6.2
 */