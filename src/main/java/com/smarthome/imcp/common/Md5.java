/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ public class Md5
/*     */ {
/*     */   private long[] state;
/*     */   private long[] count;
/*     */   private byte[] buffer;
/*     */   public String digestHexStr;
/*     */   private byte[] digest;
/*     */   static final int S11 = 7;
/*     */   static final int S12 = 12;
/*     */   static final int S13 = 17;
/*     */   static final int S14 = 22;
/*     */   static final int S21 = 5;
/*     */   static final int S22 = 9;
/*     */   static final int S23 = 14;
/*     */   static final int S24 = 20;
/*     */   static final int S31 = 4;
/*     */   static final int S32 = 11;
/*     */   static final int S33 = 16;
/*     */   static final int S34 = 23;
/*     */   static final int S41 = 6;
/*     */   static final int S42 = 10;
/*     */   static final int S43 = 15;
/*     */   static final int S44 = 21;
/* 252 */   static final byte[] PADDING = { -128,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
/*     */ 
/*     */   public String getMD5ofStr(String inbuf)
/*     */   {
/*  16 */     md5Init();
/*  17 */     md5Update(inbuf.getBytes(), inbuf.length());
/*  18 */     md5Final();
/*  19 */     this.digestHexStr = "";
/*  20 */     for (int i = 0; i < 16; i++) {
/*  21 */       this.digestHexStr += byteHEX(this.digest[i]);
/*     */     }
/*  23 */     return this.digestHexStr;
/*     */   }
/*     */ 
/*     */   public Md5() {
/*  27 */     this.state = new long[4];
/*  28 */     this.count = new long[2];
/*  29 */     this.buffer = new byte[64];
/*  30 */     this.digest = new byte[16];
/*  31 */     md5Init();
/*     */   }
/*     */ 
/*     */   private void md5Init() {
/*  35 */     this.count[0] = 0L;
/*  36 */     this.count[1] = 0L;
/*  37 */     this.state[0] = 1732584193L;
/*  38 */     this.state[1] = 4023233417L;
/*  39 */     this.state[2] = 2562383102L;
/*  40 */     this.state[3] = 271733878L;
/*     */   }
/*     */ 
/*     */   private long F(long x, long y, long z) {
/*  44 */     return x & y | (x ^ 0xFFFFFFFF) & z;
/*     */   }
/*     */ 
/*     */   private long G(long x, long y, long z) {
/*  48 */     return x & z | y & (z ^ 0xFFFFFFFF);
/*     */   }
/*     */ 
/*     */   private long H(long x, long y, long z) {
/*  52 */     return x ^ y ^ z;
/*     */   }
/*     */ 
/*     */   private long I(long x, long y, long z) {
/*  56 */     return y ^ (x | z ^ 0xFFFFFFFF);
/*     */   }
/*     */ 
/*     */   private long FF(long a, long b, long c, long d, long x, long s, long ac) {
/*  60 */     a += F(b, c, d) + x + ac;
/*  61 */     a = (int)a << (int)s | (int)a >>> (int)(32L - s);
/*  62 */     a += b;
/*  63 */     return a;
/*     */   }
/*     */ 
/*     */   private long GG(long a, long b, long c, long d, long x, long s, long ac) {
/*  67 */     a += G(b, c, d) + x + ac;
/*  68 */     a = (int)a << (int)s | (int)a >>> (int)(32L - s);
/*  69 */     a += b;
/*  70 */     return a;
/*     */   }
/*     */ 
/*     */   private long HH(long a, long b, long c, long d, long x, long s, long ac) {
/*  74 */     a += H(b, c, d) + x + ac;
/*  75 */     a = (int)a << (int)s | (int)a >>> (int)(32L - s);
/*  76 */     a += b;
/*  77 */     return a;
/*     */   }
/*     */ 
/*     */   private long II(long a, long b, long c, long d, long x, long s, long ac) {
/*  81 */     a += I(b, c, d) + x + ac;
/*  82 */     a = (int)a << (int)s | (int)a >>> (int)(32L - s);
/*  83 */     a += b;
/*  84 */     return a;
/*     */   }
/*     */ 
/*     */   private void md5Update(byte[] inbuf, int inputLen) {//index:; i:; inputLen:输入字符串的长度;
/*  88 */     byte[] block = new byte[64];
/*  89 */     int index = (int)(this.count[0] >>> 3) & 0x3F;	
//			  System.out.println("inbuf===="+inbuf+",index==========="+index+",inputLen========"+inputLen);
			  
/*  90 */     if (( this.count[0] += (inputLen << 3)) < inputLen << 3 )
/*  91 */       this.count[1] += 1L;
/*  92 */     this.count[1] += (inputLen >>> 29);
/*  93 */     int partLen = 64 - index;
/*     */     int i;
/*  95 */     if (inputLen >= partLen) {
//	           System.out.println("进来了。。。。");
/*  96 */       md5Memcpy(this.buffer, inbuf, index, 0, partLen);
/*  97 */       md5Transform(this.buffer);
///*  98 */       for (int i = partLen; i + 63 < inputLen; i += 64) {
				for (i = partLen; i + 63 < inputLen; i += 64) {
/*  99 */         md5Memcpy(block, inbuf, 0, i, 64);
/* 100 */         md5Transform(block);
/*     */       }
/* 102 */       index = 0;
//				System.out.println("index(120)======="+index);
/*     */     } else {
//				index=0;
/* 104 */       i = 0;
/*     */     }
//				System.out.println("index(123)==========="+index+",i(123)========"+i+",inbuf====="+inbuf);
/* 106 */       md5Memcpy(this.buffer, inbuf, index, i, inputLen - i);
/*     */   }
/*     */ 
/*     */   private void md5Final() {
/* 110 */     byte[] bits = new byte[8];
/* 111 */     Encode(bits, this.count, 8);
/* 112 */     int index = (int)(this.count[0] >>> 3) & 0x3F;
/* 113 */     int padLen = index >= 56 ? 120 - index : 56 - index;
//			  System.out.println("padLen======"+padLen);
//            System.out.println("PADDING====="+PADDING);  
/* 114 */     md5Update(PADDING, padLen);
//              System.out.println("md5Final test1...");
/* 115 */     md5Update(bits, 8);
//			  System.out.println("md5Final test2...");
/* 116 */     Encode(this.digest, this.state, 16);
/*     */   }
/*     */ 
/*     */   private void md5Memcpy(byte[] output, byte[] input, int outpos, int inpos, int len)
/*     */   {
//			  System.out.println("input===="+input+",outpos===="+outpos+",inpos====="+inpos);
/* 121 */     for (int i = 0; i < len; i++)
	            {
//					System.out.println("i====="+i);
/* 122 */       	output[(outpos + i)] = input[(inpos + i)];
//					System.out.println("output[i]====="+output[(outpos + i)]+",input[(inpos + i)======"+input[(inpos + i)]);//对应的ASCII码                      
	            }
/*     */   }
/*     */ 
/*     */   private void md5Transform(byte[] block) {
/* 126 */     long a = this.state[0];
/* 127 */     long b = this.state[1];
/* 128 */     long c = this.state[2];
/* 129 */     long d = this.state[3];
/* 130 */     long[] x = new long[16];
/* 131 */     Decode(x, block, 64);
/* 132 */     a = FF(a, b, c, d, x[0], 7L, 3614090360L);
/* 133 */     d = FF(d, a, b, c, x[1], 12L, 3905402710L);
/* 134 */     c = FF(c, d, a, b, x[2], 17L, 606105819L);
/* 135 */     b = FF(b, c, d, a, x[3], 22L, 3250441966L);
/* 136 */     a = FF(a, b, c, d, x[4], 7L, 4118548399L);
/* 137 */     d = FF(d, a, b, c, x[5], 12L, 1200080426L);
/* 138 */     c = FF(c, d, a, b, x[6], 17L, 2821735955L);
/* 139 */     b = FF(b, c, d, a, x[7], 22L, 4249261313L);
/* 140 */     a = FF(a, b, c, d, x[8], 7L, 1770035416L);
/* 141 */     d = FF(d, a, b, c, x[9], 12L, 2336552879L);
/* 142 */     c = FF(c, d, a, b, x[10], 17L, 4294925233L);
/* 143 */     b = FF(b, c, d, a, x[11], 22L, 2304563134L);
/* 144 */     a = FF(a, b, c, d, x[12], 7L, 1804603682L);
/* 145 */     d = FF(d, a, b, c, x[13], 12L, 4254626195L);
/* 146 */     c = FF(c, d, a, b, x[14], 17L, 2792965006L);
/* 147 */     b = FF(b, c, d, a, x[15], 22L, 1236535329L);
/* 148 */     a = GG(a, b, c, d, x[1], 5L, 4129170786L);
/* 149 */     d = GG(d, a, b, c, x[6], 9L, 3225465664L);
/* 150 */     c = GG(c, d, a, b, x[11], 14L, 643717713L);
/* 151 */     b = GG(b, c, d, a, x[0], 20L, 3921069994L);
/* 152 */     a = GG(a, b, c, d, x[5], 5L, 3593408605L);
/* 153 */     d = GG(d, a, b, c, x[10], 9L, 38016083L);
/* 154 */     c = GG(c, d, a, b, x[15], 14L, 3634488961L);
/* 155 */     b = GG(b, c, d, a, x[4], 20L, 3889429448L);
/* 156 */     a = GG(a, b, c, d, x[9], 5L, 568446438L);
/* 157 */     d = GG(d, a, b, c, x[14], 9L, 3275163606L);
/* 158 */     c = GG(c, d, a, b, x[3], 14L, 4107603335L);
/* 159 */     b = GG(b, c, d, a, x[8], 20L, 1163531501L);
/* 160 */     a = GG(a, b, c, d, x[13], 5L, 2850285829L);
/* 161 */     d = GG(d, a, b, c, x[2], 9L, 4243563512L);
/* 162 */     c = GG(c, d, a, b, x[7], 14L, 1735328473L);
/* 163 */     b = GG(b, c, d, a, x[12], 20L, 2368359562L);
/* 164 */     a = HH(a, b, c, d, x[5], 4L, 4294588738L);
/* 165 */     d = HH(d, a, b, c, x[8], 11L, 2272392833L);
/* 166 */     c = HH(c, d, a, b, x[11], 16L, 1839030562L);
/* 167 */     b = HH(b, c, d, a, x[14], 23L, 4259657740L);
/* 168 */     a = HH(a, b, c, d, x[1], 4L, 2763975236L);
/* 169 */     d = HH(d, a, b, c, x[4], 11L, 1272893353L);
/* 170 */     c = HH(c, d, a, b, x[7], 16L, 4139469664L);
/* 171 */     b = HH(b, c, d, a, x[10], 23L, 3200236656L);
/* 172 */     a = HH(a, b, c, d, x[13], 4L, 681279174L);
/* 173 */     d = HH(d, a, b, c, x[0], 11L, 3936430074L);
/* 174 */     c = HH(c, d, a, b, x[3], 16L, 3572445317L);
/* 175 */     b = HH(b, c, d, a, x[6], 23L, 76029189L);
/* 176 */     a = HH(a, b, c, d, x[9], 4L, 3654602809L);
/* 177 */     d = HH(d, a, b, c, x[12], 11L, 3873151461L);
/* 178 */     c = HH(c, d, a, b, x[15], 16L, 530742520L);
/* 179 */     b = HH(b, c, d, a, x[2], 23L, 3299628645L);
/* 180 */     a = II(a, b, c, d, x[0], 6L, 4096336452L);
/* 181 */     d = II(d, a, b, c, x[7], 10L, 1126891415L);
/* 182 */     c = II(c, d, a, b, x[14], 15L, 2878612391L);
/* 183 */     b = II(b, c, d, a, x[5], 21L, 4237533241L);
/* 184 */     a = II(a, b, c, d, x[12], 6L, 1700485571L);
/* 185 */     d = II(d, a, b, c, x[3], 10L, 2399980690L);
/* 186 */     c = II(c, d, a, b, x[10], 15L, 4293915773L);
/* 187 */     b = II(b, c, d, a, x[1], 21L, 2240044497L);
/* 188 */     a = II(a, b, c, d, x[8], 6L, 1873313359L);
/* 189 */     d = II(d, a, b, c, x[15], 10L, 4264355552L);
/* 190 */     c = II(c, d, a, b, x[6], 15L, 2734768916L);
/* 191 */     b = II(b, c, d, a, x[13], 21L, 1309151649L);
/* 192 */     a = II(a, b, c, d, x[4], 6L, 4149444226L);
/* 193 */     d = II(d, a, b, c, x[11], 10L, 3174756917L);
/* 194 */     c = II(c, d, a, b, x[2], 15L, 718787259L);
/* 195 */     b = II(b, c, d, a, x[9], 21L, 3951481745L);
/* 196 */     this.state[0] += a;
/* 197 */     this.state[1] += b;
/* 198 */     this.state[2] += c;
/* 199 */     this.state[3] += d;
/*     */   }
/*     */ 
/*     */   private void Encode(byte[] output, long[] input, int len) {
/* 203 */     int i = 0;
/* 204 */     for (int j = 0; j < len; j += 4) {
/* 205 */       output[j] = ((byte)(int)(input[i] & 0xFF));
/* 206 */       output[(j + 1)] = ((byte)(int)(input[i] >>> 8 & 0xFF));
/* 207 */       output[(j + 2)] = ((byte)(int)(input[i] >>> 16 & 0xFF));
/* 208 */       output[(j + 3)] = ((byte)(int)(input[i] >>> 24 & 0xFF));
/* 209 */       i++;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void Decode(long[] output, byte[] input, int len) {
/* 214 */     int i = 0;
/* 215 */     for (int j = 0; j < len; j += 4) {
/* 216 */       output[i] = 
/* 217 */         (b2iu(input[j]) | b2iu(input[(j + 1)]) << 8 | 
/* 217 */         b2iu(input[(j + 2)]) << 16 | b2iu(input[(j + 3)]) << 24);
/* 218 */       i++;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static long b2iu(byte b) {
/* 223 */     return b >= 0 ? b : b & 0xFF;
/*     */   }
/*     */ 
/*     */   public static String byteHEX(byte ib) {
/* 227 */     char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 
/* 228 */       'B', 'C', 'D', 'E', 'F' };
/* 229 */     char[] ob = new char[2];
/* 230 */     ob[0] = Digit[(ib >>> 4 & 0xF)];
/* 231 */     ob[1] = Digit[(ib & 0xF)];
/* 232 */     String s = new String(ob);
/* 233 */     return s;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.Md5
 * JD-Core Version:    0.6.2
 */