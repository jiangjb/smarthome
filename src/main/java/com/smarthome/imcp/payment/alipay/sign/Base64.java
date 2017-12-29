/*     */ package com.smarthome.imcp.payment.alipay.sign;
/*     */ 
/*     */ public final class Base64
/*     */ {
/*     */   private static final int BASELENGTH = 128;
/*     */   private static final int LOOKUPLENGTH = 64;
/*     */   private static final int TWENTYFOURBITGROUP = 24;
/*     */   private static final int EIGHTBIT = 8;
/*     */   private static final int SIXTEENBIT = 16;
/*     */   private static final int FOURBYTE = 4;
/*     */   private static final int SIGN = -128;
/*     */   private static final char PAD = '=';
/*     */   private static final boolean fDebug = false;
/*  20 */   private static final byte[] base64Alphabet = new byte[''];
/*  21 */   private static final char[] lookUpBase64Alphabet = new char[64];
/*     */ 
/*     */   static {
/*  24 */     for (int i = 0; i < 128; i++) {
/*  25 */       base64Alphabet[i] = -1;
/*     */     }
/*  27 */     for (int i = 90; i >= 65; i--) {
/*  28 */       base64Alphabet[i] = ((byte)(i - 65));
/*     */     }
/*  30 */     for (int i = 122; i >= 97; i--) {
/*  31 */       base64Alphabet[i] = ((byte)(i - 97 + 26));
/*     */     }
/*     */ 
/*  34 */     for (int i = 57; i >= 48; i--) {
/*  35 */       base64Alphabet[i] = ((byte)(i - 48 + 52));
/*     */     }
/*     */ 
/*  38 */     base64Alphabet[43] = 62;
/*  39 */     base64Alphabet[47] = 63;
/*     */ 
/*  41 */     for (int i = 0; i <= 25; i++) {
/*  42 */       lookUpBase64Alphabet[i] = ((char)(65 + i));
/*     */     }
/*     */ 
/*  45 */     int i = 26; for (int j = 0; i <= 51; j++) {
/*  46 */       lookUpBase64Alphabet[i] = ((char)(97 + j));
/*     */ 
/*  45 */       i++;
/*     */     }
/*     */ 
/*  49 */     i = 52; 
			  for (int j = 0; i <= 61; j++) {
/*  50 */       lookUpBase64Alphabet[i] = ((char)(48 + j));
/*     */ 
/*  49 */       i++;
/*     */     }
/*     */ 
/*  52 */     lookUpBase64Alphabet[62] = '+';
/*  53 */     lookUpBase64Alphabet[63] = '/';
/*     */   }
/*     */ 
/*     */   private static boolean isWhiteSpace(char octect)
/*     */   {
/*  58 */     return (octect == ' ') || (octect == '\r') || (octect == '\n') || (octect == '\t');
/*     */   }
/*     */ 
/*     */   private static boolean isPad(char octect) {
/*  62 */     return octect == '=';
/*     */   }
/*     */ 
/*     */   private static boolean isData(char octect) {
/*  66 */     return (octect < '') && (base64Alphabet[octect] != -1);
/*     */   }
/*     */ 
/*     */   public static String encode(byte[] binaryData)
/*     */   {
/*  77 */     if (binaryData == null) {
/*  78 */       return null;
/*     */     }
/*     */ 
/*  81 */     int lengthDataBits = binaryData.length * 8;
/*  82 */     if (lengthDataBits == 0) {
/*  83 */       return "";
/*     */     }
/*     */ 
/*  86 */     int fewerThan24bits = lengthDataBits % 24;
/*  87 */     int numberTriplets = lengthDataBits / 24;
/*  88 */     int numberQuartet = fewerThan24bits != 0 ? numberTriplets + 1 : numberTriplets;
/*  89 */     char[] encodedData = null;
/*     */ 
/*  91 */     encodedData = new char[numberQuartet * 4];
/*     */ 
/*  93 */     byte k = 0; byte l = 0; byte b1 = 0; byte b2 = 0; byte b3 = 0;
/*     */ 
/*  95 */     int encodedIndex = 0;
/*  96 */     int dataIndex = 0;
/*     */ 
/* 101 */     for (int i = 0; i < numberTriplets; i++) {
/* 102 */       b1 = binaryData[(dataIndex++)];
/* 103 */       b2 = binaryData[(dataIndex++)];
/* 104 */       b3 = binaryData[(dataIndex++)];
/*     */ 
/* 110 */       l = (byte)(b2 & 0xF);
/* 111 */       k = (byte)(b1 & 0x3);
/*     */ 
/* 113 */       byte val1 = (b1 & 0xFFFFFF80) == 0 ? (byte)(b1 >> 2) : (byte)(b1 >> 2 ^ 0xC0);
/* 114 */       byte val2 = (b2 & 0xFFFFFF80) == 0 ? (byte)(b2 >> 4) : (byte)(b2 >> 4 ^ 0xF0);
/* 115 */       byte val3 = (b3 & 0xFFFFFF80) == 0 ? (byte)(b3 >> 6) : (byte)(b3 >> 6 ^ 0xFC);
/*     */ 
/* 123 */       encodedData[(encodedIndex++)] = lookUpBase64Alphabet[val1];
/* 124 */       encodedData[(encodedIndex++)] = lookUpBase64Alphabet[(val2 | k << 4)];
/* 125 */       encodedData[(encodedIndex++)] = lookUpBase64Alphabet[(l << 2 | val3)];
/* 126 */       encodedData[(encodedIndex++)] = lookUpBase64Alphabet[(b3 & 0x3F)];
/*     */     }
/*     */ 
/* 130 */     if (fewerThan24bits == 8) {
/* 131 */       b1 = binaryData[dataIndex];
/* 132 */       k = (byte)(b1 & 0x3);
/*     */ 
/* 137 */       byte val1 = (b1 & 0xFFFFFF80) == 0 ? (byte)(b1 >> 2) : (byte)(b1 >> 2 ^ 0xC0);
/* 138 */       encodedData[(encodedIndex++)] = lookUpBase64Alphabet[val1];
/* 139 */       encodedData[(encodedIndex++)] = lookUpBase64Alphabet[(k << 4)];
/* 140 */       encodedData[(encodedIndex++)] = '=';
/* 141 */       encodedData[(encodedIndex++)] = '=';
/* 142 */     } else if (fewerThan24bits == 16) {
/* 143 */       b1 = binaryData[dataIndex];
/* 144 */       b2 = binaryData[(dataIndex + 1)];
/* 145 */       l = (byte)(b2 & 0xF);
/* 146 */       k = (byte)(b1 & 0x3);
/*     */ 
/* 148 */       byte val1 = (b1 & 0xFFFFFF80) == 0 ? (byte)(b1 >> 2) : (byte)(b1 >> 2 ^ 0xC0);
/* 149 */       byte val2 = (b2 & 0xFFFFFF80) == 0 ? (byte)(b2 >> 4) : (byte)(b2 >> 4 ^ 0xF0);
/*     */ 
/* 151 */       encodedData[(encodedIndex++)] = lookUpBase64Alphabet[val1];
/* 152 */       encodedData[(encodedIndex++)] = lookUpBase64Alphabet[(val2 | k << 4)];
/* 153 */       encodedData[(encodedIndex++)] = lookUpBase64Alphabet[(l << 2)];
/* 154 */       encodedData[(encodedIndex++)] = '=';
/*     */     }
/*     */ 
/* 157 */     return new String(encodedData);
/*     */   }
/*     */ 
/*     */   public static byte[] decode(String encoded)
/*     */   {
/* 168 */     if (encoded == null) {
/* 169 */       return null;
/*     */     }
/*     */ 
/* 172 */     char[] base64Data = encoded.toCharArray();
/*     */ 
/* 174 */     int len = removeWhiteSpace(base64Data);
/*     */ 
/* 176 */     if (len % 4 != 0) {
/* 177 */       return null;
/*     */     }
/*     */ 
/* 180 */     int numberQuadruple = len / 4;
/*     */ 
/* 182 */     if (numberQuadruple == 0) {
/* 183 */       return new byte[0];
/*     */     }
/*     */ 
/* 186 */     byte[] decodedData = null;
/* 187 */     byte b1 = 0; byte b2 = 0; byte b3 = 0; byte b4 = 0;
/* 188 */     char d1 = '\000'; char d2 = '\000'; char d3 = '\000'; char d4 = '\000';
/*     */ 
/* 190 */     int i = 0;
/* 191 */     int encodedIndex = 0;
/* 192 */     int dataIndex = 0;
/* 193 */     decodedData = new byte[numberQuadruple * 3];
/*     */ 
/* 195 */     for (; i < numberQuadruple - 1; i++)
/*     */     {
/* 197 */       if ((!isData(d1 = base64Data[(dataIndex++)])) || (!isData(d2 = base64Data[(dataIndex++)])) || 
/* 198 */         (!isData(d3 = base64Data[(dataIndex++)])) || 
/* 199 */         (!isData(d4 = base64Data[(dataIndex++)]))) {
/* 200 */         return null;
/*     */       }
/*     */ 
/* 203 */       b1 = base64Alphabet[d1];
/* 204 */       b2 = base64Alphabet[d2];
/* 205 */       b3 = base64Alphabet[d3];
/* 206 */       b4 = base64Alphabet[d4];
/*     */ 
/* 208 */       decodedData[(encodedIndex++)] = ((byte)(b1 << 2 | b2 >> 4));
/* 209 */       decodedData[(encodedIndex++)] = ((byte)((b2 & 0xF) << 4 | b3 >> 2 & 0xF));
/* 210 */       decodedData[(encodedIndex++)] = ((byte)(b3 << 6 | b4));
/*     */     }
/*     */ 
/* 213 */     if ((!isData(d1 = base64Data[(dataIndex++)])) || (!isData(d2 = base64Data[(dataIndex++)]))) {
/* 214 */       return null;
/*     */     }
/*     */ 
/* 217 */     b1 = base64Alphabet[d1];
/* 218 */     b2 = base64Alphabet[d2];
/*     */ 
/* 220 */     d3 = base64Data[(dataIndex++)];
/* 221 */     d4 = base64Data[(dataIndex++)];
/* 222 */     if ((!isData(d3)) || (!isData(d4))) {
/* 223 */       if ((isPad(d3)) && (isPad(d4))) {
/* 224 */         if ((b2 & 0xF) != 0)
/*     */         {
/* 226 */           return null;
/*     */         }
/* 228 */         byte[] tmp = new byte[i * 3 + 1];
/* 229 */         System.arraycopy(decodedData, 0, tmp, 0, i * 3);
/* 230 */         tmp[encodedIndex] = ((byte)(b1 << 2 | b2 >> 4));
/* 231 */         return tmp;
/* 232 */       }if ((!isPad(d3)) && (isPad(d4))) {
/* 233 */         b3 = base64Alphabet[d3];
/* 234 */         if ((b3 & 0x3) != 0)
/*     */         {
/* 236 */           return null;
/*     */         }
/* 238 */         byte[] tmp = new byte[i * 3 + 2];
/* 239 */         System.arraycopy(decodedData, 0, tmp, 0, i * 3);
/* 240 */         tmp[(encodedIndex++)] = ((byte)(b1 << 2 | b2 >> 4));
/* 241 */         tmp[encodedIndex] = ((byte)((b2 & 0xF) << 4 | b3 >> 2 & 0xF));
/* 242 */         return tmp;
/*     */       }
/* 244 */       return null;
/*     */     }
/*     */ 
/* 247 */     b3 = base64Alphabet[d3];
/* 248 */     b4 = base64Alphabet[d4];
/* 249 */     decodedData[(encodedIndex++)] = ((byte)(b1 << 2 | b2 >> 4));
/* 250 */     decodedData[(encodedIndex++)] = ((byte)((b2 & 0xF) << 4 | b3 >> 2 & 0xF));
/* 251 */     decodedData[(encodedIndex++)] = ((byte)(b3 << 6 | b4));
/*     */ 
/* 255 */     return decodedData;
/*     */   }
/*     */ 
/*     */   private static int removeWhiteSpace(char[] data)
/*     */   {
/* 265 */     if (data == null) {
/* 266 */       return 0;
/*     */     }
/*     */ 
/* 270 */     int newSize = 0;
/* 271 */     int len = data.length;
/* 272 */     for (int i = 0; i < len; i++) {
/* 273 */       if (!isWhiteSpace(data[i])) {
/* 274 */         data[(newSize++)] = data[i];
/*     */       }
/*     */     }
/* 277 */     return newSize;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.payment.alipay.sign.Base64
 * JD-Core Version:    0.6.2
 */