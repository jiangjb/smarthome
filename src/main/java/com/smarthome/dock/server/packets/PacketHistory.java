/*     */ package com.smarthome.dock.server.packets;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class PacketHistory
/*     */ {
/*     */   private HashMap<String, LinkedHashSet<Integer>> dockUserHash;
/*     */   private Map<OutPacket, OutPacket> requests;
/*     */   private static final int THRESHOLD = 2000;
/*     */ 
/*     */   public PacketHistory()
/*     */   {
/*  47 */     this.dockUserHash = new HashMap();
/*  48 */     this.requests = new Hashtable();
/*     */   }
/*     */ 
/*     */   public boolean check(Packet packet, boolean add)
/*     */   {
/*  68 */     return check(packet.getDevId(), packet.hashCode(), add);
/*     */   }
/*     */ 
/*     */   public boolean check(String devId, int hashValue, boolean add)
/*     */   {
/*     */     LinkedHashSet hash;
///*     */     LinkedHashSet hash;
/*  83 */     if (this.dockUserHash.containsKey(devId)) {
/*  84 */       hash = (LinkedHashSet)this.dockUserHash.get(devId);
/*     */     } else {
/*  86 */       hash = new LinkedHashSet();
/*  87 */       this.dockUserHash.put(devId, hash);
/*     */     }
/*     */ 
/*  91 */     if (hash.contains(Integer.valueOf(hashValue))) {
/*  92 */       hash.remove(Integer.valueOf(hashValue));
/*  93 */       return true;
/*     */     }
/*     */ 
/*  96 */     if (add)
/*  97 */       hash.add(Integer.valueOf(hashValue));
/*     */     else {
/*  99 */       return false;
/*     */     }
/*     */ 
/* 102 */     if (hash.size() >= 2000)
/*     */     {
/* 104 */       Iterator it = hash.iterator();
/* 105 */       for (int i = 0; i < 1000; i++) {
/* 106 */         it.next();
/* 107 */         it.remove();
/*     */       }
/*     */     }
/*     */ 
/* 111 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean check(Object packet, boolean add)
/*     */   {
/* 127 */     return check((Packet)packet, add);
/*     */   }
/*     */ 
/*     */   public void putRequest(OutPacket packet)
/*     */   {
/* 137 */     this.requests.put(packet, packet);
/*     */   }
/*     */ 
/*     */   public Object retrieveRequest(InPacket packet)
/*     */   {
/* 149 */     return this.requests.remove(packet);
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 156 */     for (Iterator it = this.dockUserHash.values().iterator(); it.hasNext(); ) {
/* 157 */       ((LinkedHashSet)it.next()).clear();
/*     */     }
/* 159 */     this.dockUserHash.clear();
/* 160 */     this.requests.clear();
/*     */   }
/*     */ 
/*     */   public void clear(Integer dockUser)
/*     */   {
/* 168 */     this.dockUserHash.remove(dockUser);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.PacketHistory
 * JD-Core Version:    0.6.2
 */