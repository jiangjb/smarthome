		package com.smarthome.imcp.action.xing;

		import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
		import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

		
		public class Test{
			public static void main(String[] args){
//				String fileName="F:/010222.dat";
//				File file = new File(fileName);
//				long fileSize = file.length();
//				if (fileSize > Integer.MAX_VALUE) {
//					System.out.println("file too big...");
//				}
//				FileInputStream fi;
//				try {
//					fi = new FileInputStream(fileName);
//					byte[] buffer = new byte[(int) fileSize];
//					int offset = 0;
//					int numRead = 0;
//					while (offset < buffer.length
//							&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
//						offset += numRead;
//					}
//					System.out.println(buffer.length);
//					// 确保所有数据均被读取
//					if (offset != buffer.length) {
//						throw new IOException("Could not completely read file "
//								+ file.getName());
//					}
//					System.out.println(buffer[0]);
//					fi.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				String str="learn,sss,234,567,244";
				if(str.contains("learn")) {
					System.out.println("123");
				}

			}
		}
		