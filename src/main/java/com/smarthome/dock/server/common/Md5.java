/*     */ package com.smarthome.dock.server.common;
/*     */ 
/*     */ import java.io.PrintStream;
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
/* 276 */   static final byte[] PADDING = { 
/* 277 */     -128 };
/*     */ 
/*     */   public String getMD5ofStr(String inbuf)
/*     */   {
/*  13 */     md5Init();
/*  14 */     md5Update(inbuf.getBytes(), inbuf.length());
/*  15 */     md5Final();
/*  16 */     this.digestHexStr = "";
/*  17 */     for (int i = 0; i < 16; i++) {
/*  18 */       this.digestHexStr += byteHEX(this.digest[i]);
/*     */     }
/*  20 */     return this.digestHexStr;
/*     */   }
/*     */ 
/*     */   public Md5()
/*     */   {
/*  25 */     this.state = new long[4];
/*  26 */     this.count = new long[2];
/*  27 */     this.buffer = new byte[64];
/*  28 */     this.digest = new byte[16];
/*  29 */     md5Init();
/*     */   }
/*     */ 
/*     */   private void md5Init()
/*     */   {
/*  34 */     this.count[0] = 0L;
/*  35 */     this.count[1] = 0L;
/*  36 */     this.state[0] = 1732584193L;
/*  37 */     this.state[1] = 4023233417L;
/*  38 */     this.state[2] = 2562383102L;
/*  39 */     this.state[3] = 271733878L;
/*     */   }
/*     */ 
/*     */   private long F(long x, long y, long z)
/*     */   {
/*  44 */     return x & y | (x ^ 0xFFFFFFFF) & z;
/*     */   }
/*     */ 
/*     */   private long G(long x, long y, long z)
/*     */   {
/*  49 */     return x & z | y & (z ^ 0xFFFFFFFF);
/*     */   }
/*     */ 
/*     */   private long H(long x, long y, long z)
/*     */   {
/*  54 */     return x ^ y ^ z;
/*     */   }
/*     */ 
/*     */   private long I(long x, long y, long z)
/*     */   {
/*  59 */     return y ^ (x | z ^ 0xFFFFFFFF);
/*     */   }
/*     */ 
/*     */   private long FF(long a, long b, long c, long d, long x, long s, long ac)
/*     */   {
/*  64 */     a += F(b, c, d) + x + ac;
/*  65 */     a = (int)a << (int)s | (int)a >>> (int)(32L - s);
/*  66 */     a += b;
/*  67 */     return a;
/*     */   }
/*     */ 
/*     */   private long GG(long a, long b, long c, long d, long x, long s, long ac)
/*     */   {
/*  72 */     a += G(b, c, d) + x + ac;
/*  73 */     a = (int)a << (int)s | (int)a >>> (int)(32L - s);
/*  74 */     a += b;
/*  75 */     return a;
/*     */   }
/*     */ 
/*     */   private long HH(long a, long b, long c, long d, long x, long s, long ac)
/*     */   {
/*  80 */     a += H(b, c, d) + x + ac;
/*  81 */     a = (int)a << (int)s | (int)a >>> (int)(32L - s);
/*  82 */     a += b;
/*  83 */     return a;
/*     */   }
/*     */ 
/*     */   private long II(long a, long b, long c, long d, long x, long s, long ac)
/*     */   {
/*  88 */     a += I(b, c, d) + x + ac;
/*  89 */     a = (int)a << (int)s | (int)a >>> (int)(32L - s);
/*  90 */     a += b;
/*  91 */     return a;
/*     */   }
/*     */ 
/*     */   private void md5Update(byte[] inbuf, int inputLen)
/*     */   {
/*  96 */     byte[] block = new byte[64];
/*  97 */     int index = (int)(this.count[0] >>> 3) & 0x3F;
/*  98 */     if ((this.count[0] += (inputLen << 3)) < inputLen << 3)
/*  99 */       this.count[1] += 1L;
/* 100 */     this.count[1] += (inputLen >>> 29);
/* 101 */     int partLen = 64 - index;
/*     */     int i;
/* 103 */     if (inputLen >= partLen)
/*     */     {
/* 105 */       md5Memcpy(this.buffer, inbuf, index, 0, partLen);
/* 106 */       md5Transform(this.buffer);
/* 107 */       for (i = partLen; i + 63 < inputLen; i += 64)
/*     */       {
/* 109 */         md5Memcpy(block, inbuf, 0, i, 64);
/* 110 */         md5Transform(block);
/*     */       }
/*     */ 
/* 113 */       index = 0;
/*     */     }
/*     */     else {
/* 116 */       i = 0;
/*     */     }
/* 118 */     md5Memcpy(this.buffer, inbuf, index, i, inputLen - i);
/*     */   }
/*     */ 
/*     */   private void md5Final()
/*     */   {
/* 123 */     byte[] bits = new byte[8];
/* 124 */     Encode(bits, this.count, 8);
/* 125 */     int index = (int)(this.count[0] >>> 3) & 0x3F;
/* 126 */     int padLen = index >= 56 ? 120 - index : 56 - index;
/* 127 */     md5Update(PADDING, padLen);
/* 128 */     md5Update(bits, 8);
/* 129 */     Encode(this.digest, this.state, 16);
/*     */   }
/*     */ 
/*     */   private void md5Memcpy(byte[] output, byte[] input, int outpos, int inpos, int len)
/*     */   {
/* 134 */     for (int i = 0; i < len; i++)
/* 135 */       output[(outpos + i)] = input[(inpos + i)];
/*     */   }
/*     */ 
/*     */   private void md5Transform(byte[] block)
/*     */   {
/* 141 */     long a = this.state[0];
/* 142 */     long b = this.state[1];
/* 143 */     long c = this.state[2];
/* 144 */     long d = this.state[3];
/* 145 */     long[] x = new long[16];
/* 146 */     Decode(x, block, 64);
/* 147 */     a = FF(a, b, c, d, x[0], 7L, 3614090360L);
/* 148 */     d = FF(d, a, b, c, x[1], 12L, 3905402710L);
/* 149 */     c = FF(c, d, a, b, x[2], 17L, 606105819L);
/* 150 */     b = FF(b, c, d, a, x[3], 22L, 3250441966L);
/* 151 */     a = FF(a, b, c, d, x[4], 7L, 4118548399L);
/* 152 */     d = FF(d, a, b, c, x[5], 12L, 1200080426L);
/* 153 */     c = FF(c, d, a, b, x[6], 17L, 2821735955L);
/* 154 */     b = FF(b, c, d, a, x[7], 22L, 4249261313L);
/* 155 */     a = FF(a, b, c, d, x[8], 7L, 1770035416L);
/* 156 */     d = FF(d, a, b, c, x[9], 12L, 2336552879L);
/* 157 */     c = FF(c, d, a, b, x[10], 17L, 4294925233L);
/* 158 */     b = FF(b, c, d, a, x[11], 22L, 2304563134L);
/* 159 */     a = FF(a, b, c, d, x[12], 7L, 1804603682L);
/* 160 */     d = FF(d, a, b, c, x[13], 12L, 4254626195L);
/* 161 */     c = FF(c, d, a, b, x[14], 17L, 2792965006L);
/* 162 */     b = FF(b, c, d, a, x[15], 22L, 1236535329L);
/* 163 */     a = GG(a, b, c, d, x[1], 5L, 4129170786L);
/* 164 */     d = GG(d, a, b, c, x[6], 9L, 3225465664L);
/* 165 */     c = GG(c, d, a, b, x[11], 14L, 643717713L);
/* 166 */     b = GG(b, c, d, a, x[0], 20L, 3921069994L);
/* 167 */     a = GG(a, b, c, d, x[5], 5L, 3593408605L);
/* 168 */     d = GG(d, a, b, c, x[10], 9L, 38016083L);
/* 169 */     c = GG(c, d, a, b, x[15], 14L, 3634488961L);
/* 170 */     b = GG(b, c, d, a, x[4], 20L, 3889429448L);
/* 171 */     a = GG(a, b, c, d, x[9], 5L, 568446438L);
/* 172 */     d = GG(d, a, b, c, x[14], 9L, 3275163606L);
/* 173 */     c = GG(c, d, a, b, x[3], 14L, 4107603335L);
/* 174 */     b = GG(b, c, d, a, x[8], 20L, 1163531501L);
/* 175 */     a = GG(a, b, c, d, x[13], 5L, 2850285829L);
/* 176 */     d = GG(d, a, b, c, x[2], 9L, 4243563512L);
/* 177 */     c = GG(c, d, a, b, x[7], 14L, 1735328473L);
/* 178 */     b = GG(b, c, d, a, x[12], 20L, 2368359562L);
/* 179 */     a = HH(a, b, c, d, x[5], 4L, 4294588738L);
/* 180 */     d = HH(d, a, b, c, x[8], 11L, 2272392833L);
/* 181 */     c = HH(c, d, a, b, x[11], 16L, 1839030562L);
/* 182 */     b = HH(b, c, d, a, x[14], 23L, 4259657740L);
/* 183 */     a = HH(a, b, c, d, x[1], 4L, 2763975236L);
/* 184 */     d = HH(d, a, b, c, x[4], 11L, 1272893353L);
/* 185 */     c = HH(c, d, a, b, x[7], 16L, 4139469664L);
/* 186 */     b = HH(b, c, d, a, x[10], 23L, 3200236656L);
/* 187 */     a = HH(a, b, c, d, x[13], 4L, 681279174L);
/* 188 */     d = HH(d, a, b, c, x[0], 11L, 3936430074L);
/* 189 */     c = HH(c, d, a, b, x[3], 16L, 3572445317L);
/* 190 */     b = HH(b, c, d, a, x[6], 23L, 76029189L);
/* 191 */     a = HH(a, b, c, d, x[9], 4L, 3654602809L);
/* 192 */     d = HH(d, a, b, c, x[12], 11L, 3873151461L);
/* 193 */     c = HH(c, d, a, b, x[15], 16L, 530742520L);
/* 194 */     b = HH(b, c, d, a, x[2], 23L, 3299628645L);
/* 195 */     a = II(a, b, c, d, x[0], 6L, 4096336452L);
/* 196 */     d = II(d, a, b, c, x[7], 10L, 1126891415L);
/* 197 */     c = II(c, d, a, b, x[14], 15L, 2878612391L);
/* 198 */     b = II(b, c, d, a, x[5], 21L, 4237533241L);
/* 199 */     a = II(a, b, c, d, x[12], 6L, 1700485571L);
/* 200 */     d = II(d, a, b, c, x[3], 10L, 2399980690L);
/* 201 */     c = II(c, d, a, b, x[10], 15L, 4293915773L);
/* 202 */     b = II(b, c, d, a, x[1], 21L, 2240044497L);
/* 203 */     a = II(a, b, c, d, x[8], 6L, 1873313359L);
/* 204 */     d = II(d, a, b, c, x[15], 10L, 4264355552L);
/* 205 */     c = II(c, d, a, b, x[6], 15L, 2734768916L);
/* 206 */     b = II(b, c, d, a, x[13], 21L, 1309151649L);
/* 207 */     a = II(a, b, c, d, x[4], 6L, 4149444226L);
/* 208 */     d = II(d, a, b, c, x[11], 10L, 3174756917L);
/* 209 */     c = II(c, d, a, b, x[2], 15L, 718787259L);
/* 210 */     b = II(b, c, d, a, x[9], 21L, 3951481745L);
/* 211 */     this.state[0] += a;
/* 212 */     this.state[1] += b;
/* 213 */     this.state[2] += c;
/* 214 */     this.state[3] += d;
/*     */   }
/*     */ 
/*     */   private void Encode(byte[] output, long[] input, int len)
/*     */   {
/* 219 */     int i = 0;
/* 220 */     for (int j = 0; j < len; j += 4)
/*     */     {
/* 222 */       output[j] = ((byte)(int)(input[i] & 0xFF));
/* 223 */       output[(j + 1)] = ((byte)(int)(input[i] >>> 8 & 0xFF));
/* 224 */       output[(j + 2)] = ((byte)(int)(input[i] >>> 16 & 0xFF));
/* 225 */       output[(j + 3)] = ((byte)(int)(input[i] >>> 24 & 0xFF));
/* 226 */       i++;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void Decode(long[] output, byte[] input, int len)
/*     */   {
/* 233 */     int i = 0;
/* 234 */     for (int j = 0; j < len; j += 4)
/*     */     {
/* 236 */       output[i] = (b2iu(input[j]) | b2iu(input[(j + 1)]) << 8 | b2iu(input[(j + 2)]) << 16 | b2iu(input[(j + 3)]) << 24);
/* 237 */       i++;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static long b2iu(byte b)
/*     */   {
/* 244 */     return b >= 0 ? b : b & 0xFF;
/*     */   }
/*     */ 
/*     */   public static String byteHEX(byte ib)
/*     */   {
/* 249 */     char[] Digit = { 
/* 250 */       '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
/* 251 */       'A', 'B', 'C', 'D', 'E', 'F' };
/*     */ 
/* 253 */     char[] ob = new char[2];
/* 254 */     ob[0] = Digit[(ib >>> 4 & 0xF)];
/* 255 */     ob[1] = Digit[(ib & 0xF)];
/* 256 */     String s = new String(ob);
/* 257 */     return s;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 290 */     Md5 m = new Md5();
/* 291 */     System.out.println(m.getMD5ofStr("12345678a"));
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.common.Md5
 * JD-Core Version:    0.6.2
 */