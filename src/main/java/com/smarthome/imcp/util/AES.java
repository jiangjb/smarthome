/*     */ package com.smarthome.imcp.util;
/*     */ 
/*     */ import java.net.URLEncoder;
/*     */ import java.security.InvalidAlgorithmParameterException;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.Security;
/*     */ import java.security.spec.InvalidKeySpecException;
/*     */ import java.util.Iterator;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.SecretKeyFactory;
/*     */ import javax.crypto.spec.IvParameterSpec;
/*     */ import javax.crypto.spec.PBEKeySpec;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.bouncycastle.jce.provider.BouncyCastleProvider;
/*     */ 
/*     */ public class AES
/*     */ {
/*     */   private static final int HASH_ITERATIONS = 110;
/*     */   private static final int KEY_LENGTH = 128;
/*     */   private static final String KEY_GENERATION_ALG = "PBKDF2WithHmacSHA1";
/*  99 */   private static char[] humanPassphrase = { 'a', 'n', ' ', 'u', 'n', 'b', 
/* 100 */     'r', 'e', 'a', 'k', 'a', 'b', 'l', 'e', ' ', 'c', 'r', 'y', 'p', 
/* 101 */     't', 'o', 'g', 'r', 'a', 'p', 'h', 'i', 'c', ' ', 'k', 'e', 'y' };
/*     */ 
/* 106 */   private static byte[] salt = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 
/* 107 */     13, 14, 15 };
/*     */ 
/* 109 */   private static PBEKeySpec myKeyspec = new PBEKeySpec(humanPassphrase, salt, 
/* 110 */     110, 128);
/*     */   private static final String CIPHERMODEPADDING = "AES/CBC/PKCS7Padding";
/* 113 */   private static SecretKeyFactory keyfactory = null;
/* 114 */   private static SecretKey sk = null;
/*     */   private static byte[] skAsByteArray = sk.getEncoded();
/* 143 */   private static SecretKeySpec skforAES = new SecretKeySpec(skAsByteArray, "AES");
/*     */ 
/* 116 */   private static byte[] iv = { 10, 1, 11, 5, 4, 15, 7, 9, 23, 3, 1, 6, 
/* 117 */     8, 12, 13, 91 };
/*     */ 
/* 145 */   private static IvParameterSpec IV = new IvParameterSpec(iv);
/*     */   
/*     */   static
/*     */   {
/*     */     try
/*     */     {
/* 124 */       Security.addProvider(new BouncyCastleProvider());
/*     */ 
/* 126 */       keyfactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
/* 127 */       sk = keyfactory.generateSecret(myKeyspec);
/*     */     } catch (NoSuchAlgorithmException nsae) {
/* 129 */       System.out.println("no key factory support for PBEWITHSHAANDTWOFISH-CBC");
/*     */     } catch (InvalidKeySpecException ikse) {
/* 131 */       System.out.println("invalid key spec for PBEWITHSHAANDTWOFISH-CBC");
/*     */     }
/*     */ 
///* 139 */     byte[] skAsByteArray = sk.getEncoded();
/*     */   }
/*     */ 
/*     */   public static String encrypt(String plaintext)
/*     */   {
/*     */     try
/*     */     {
/* 153 */       if (StringUtils.isEmpty(plaintext)) {
/* 154 */         return "";
/*     */       }
/* 156 */       byte[] ciphertext = encrypt("AES/CBC/PKCS7Padding", skforAES, IV, 
/* 157 */         plaintext.getBytes("UTF-8"));
/* 158 */       return Base64Encoder.encode(ciphertext);
/*     */     }
/*     */     catch (Exception e) {
/* 161 */       e.printStackTrace();
/*     */     }
/* 163 */     return "";
/*     */   }
/*     */ 
/*     */   public static String decrypt(String ciphertext_base64)
/*     */   {
/*     */     try
/*     */     {
/* 171 */       if (StringUtils.isEmpty(ciphertext_base64))
/* 172 */         return "";
/* 173 */       byte[] s = Base64Decoder.decodeToBytes(ciphertext_base64);
/* 174 */       return new String(decrypt("AES/CBC/PKCS7Padding", skforAES, 
/* 175 */         IV, s));
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 179 */       e.printStackTrace();
/*     */     }
/* 181 */     return "";
/*     */   }
/*     */ 
/*     */   private byte[] addPadding(byte[] plain)
/*     */   {
/* 191 */     byte[] plainpad = null;
/* 192 */     int shortage = 16 - plain.length % 16;
/*     */ 
/* 195 */     if (shortage == 0) {
/* 196 */       shortage = 16;
/*     */     }
/*     */ 
/* 199 */     plainpad = new byte[plain.length + shortage];
/* 200 */     for (int i = 0; i < plain.length; i++) {
/* 201 */       plainpad[i] = plain[i];
/*     */     }
/* 203 */     for (int i = plain.length; i < plain.length + shortage; i++) {
/* 204 */       plainpad[i] = ((byte)shortage);
/*     */     }
/* 206 */     return plainpad;
/*     */   }
/*     */ 
/*     */   private byte[] dropPadding(byte[] plainpad)
/*     */   {
/* 212 */     byte[] plain = null;
/* 213 */     int drop = plainpad[(plainpad.length - 1)];
/*     */ 
/* 217 */     plain = new byte[plainpad.length - drop];
/* 218 */     for (int i = 0; i < plain.length; i++) {
/* 219 */       plain[i] = plainpad[i];
/* 220 */       plainpad[i] = 0;
/*     */     }
/* 222 */     return plain;
/*     */   }
/*     */ 
/*     */   private static byte[] encrypt(String cmp, SecretKey sk, IvParameterSpec IV, byte[] msg)
/*     */   {
/*     */     try {
/* 228 */       Cipher c = Cipher.getInstance(cmp);
/* 229 */       c.init(1, sk, IV);
/* 230 */       return c.doFinal(msg);
/*     */     } catch (NoSuchAlgorithmException nsae) {
/* 232 */       nsae.printStackTrace();
/* 233 */       System.out.println("no cipher getinstance support for " + cmp);
/*     */     } catch (NoSuchPaddingException nspe) {
/* 235 */       System.out.println("no cipher getinstance support for padding " + 
/* 236 */         cmp);
/*     */     } catch (InvalidKeyException e) {
/* 238 */       e.printStackTrace();
/* 239 */       System.out.println("invalid key exception");
/*     */     } catch (InvalidAlgorithmParameterException e) {
/* 241 */       System.out.println("invalid algorithm parameter exception");
/*     */     } catch (IllegalBlockSizeException e) {
/* 243 */       System.out.println("illegal block size exception");
/*     */     } catch (BadPaddingException e) {
/* 245 */       System.out.println("bad padding exception");
/*     */     }
/* 247 */     return null;
/*     */   }
/*     */ 
/*     */   private static byte[] decrypt(String cmp, SecretKey sk, IvParameterSpec IV, byte[] ciphertext)
/*     */   {
/*     */     try {
/* 253 */       Cipher c = Cipher.getInstance(cmp);
/* 254 */       c.init(2, sk, IV);
/* 255 */       return c.doFinal(ciphertext);
/*     */     } catch (NoSuchAlgorithmException nsae) {
/* 257 */       System.out.println("no cipher getinstance support for " + cmp);
/*     */     } catch (NoSuchPaddingException nspe) {
/* 259 */       System.out.println("no cipher getinstance support for padding " + 
/* 260 */         cmp);
/*     */     } catch (InvalidKeyException e) {
/* 262 */       System.out.println("invalid key exception");
/*     */     } catch (InvalidAlgorithmParameterException e) {
/* 264 */       System.out.println("invalid algorithm parameter exception");
/*     */     } catch (IllegalBlockSizeException e) {
/* 266 */       e.printStackTrace();
/* 267 */       System.out.println("illegal block size exception");
/*     */     } catch (BadPaddingException e) {
/* 269 */       System.out.println("bad padding exception");
/* 270 */       e.printStackTrace();
/*     */     }
/* 272 */     return null;
/*     */   }
/*     */ 
/*     */   public static String AESJson(JSONObject json)
/*     */   {
/* 279 */     Iterator iterator = json.keys();
/*     */ 
/* 281 */     if ((json.get("code") != null) && 
/* 282 */       (json.getInt("code") == -1)) {
/* 283 */       return json.toString();
/*     */     }
/*     */ 
/* 286 */     while (iterator.hasNext()) {
/* 287 */       String key = (String)iterator.next();
/* 288 */       if ((!key.equals("code")) && (!key.equals("msg")) && (!key.equals("page")) && 
/* 289 */         (!key.equals("pagecount")) && (!key.equals("pagesize")) && 
/* 290 */         (!key.equals("total")))
/*     */       {
/* 292 */         if ((json.get(key) instanceof JSONArray)) {
/* 293 */           for (int i = 0; i < json.getJSONArray(key).size(); i++) {
/* 294 */             JSONObject obj = json.getJSONArray(key).getJSONObject(i);
/* 295 */             if (!obj.isNullObject())
/* 296 */               AESJson(obj);
/*     */           }
/* 298 */         } else if ((json.get(key) instanceof JSONObject)) {
/* 299 */           JSONObject obj = json.getJSONObject(key);
/* 300 */           if (!obj.isNullObject())
/* 301 */             AESJson(obj);
/* 302 */         } else if (!json.getString(key).equals("null")) {
/* 303 */           json.put(key, encrypt(json.getString(key)));
/*     */         }
/*     */       }
/*     */     }
/* 306 */     return json.toString();
/*     */   }
/*     */ 
/*     */   public static String getStr(String[] p) {
/* 310 */     String raw = "";
/* 311 */     String urlRaw = "";
/* 312 */     for (int i = 0; i < p.length; i++) {
/* 313 */       String[] str = p[i].split("@");
/* 314 */       if ((str[0].equals("page")) || (str[0].equals("pagesize"))) {
/* 315 */         raw = raw + str[0] + "=" + str[1] + "&";
/* 316 */         urlRaw = urlRaw + str[0] + "=" + str[1] + "&";
/*     */       } else {
/* 318 */         raw = raw + str[0] + "=" + encrypt(str[1]) + "&";
/* 319 */         urlRaw = urlRaw + str[0] + "=" + URLEncoder.encode(encrypt(str[1])) + 
/* 320 */           "&";
/*     */       }
/*     */     }
/*     */ 
/* 324 */     raw = raw.substring(0, raw.length() - 1);
/* 325 */     urlRaw = urlRaw.substring(0, urlRaw.length() - 1);
/*     */ 
/* 327 */     System.out.println("RAW:" + urlRaw);
/* 328 */     return "";
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 335 */     System.err.println(humanPassphrase);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.AES
 * JD-Core Version:    0.6.2
 */