/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class Page
/*     */ {
/*  13 */   private static Logger logger = Logger.getLogger(Page.class.getName());
/*     */   public static final int DEFAULT_PAGE_SIZE = 10;
/*     */   private int totalCount;
/*  16 */   private int pageSize = 10;
/*  17 */   private int pageNum = 1;
/*  18 */   private int currentPage = 1;
/*     */   private int totalPages;
/*  20 */   private int startRow = 0;
/*  21 */   private int endRow = 0;
/*     */   private String orderField;
/*     */   private String orderDirection;
/*  24 */   private boolean isAsc = false;
/*     */ 
/*     */   public Page(int _totalCount, int _pageSize) {
/*  27 */     this.totalCount = _totalCount;
/*  28 */     this.pageSize = _pageSize;
/*  29 */     initTotalPages();
/*  30 */     this.startRow = 0;
/*     */   }
/*     */ 
/*     */   public Page(int _totalCount) {
/*  34 */     this.totalCount = _totalCount;
/*  35 */     initTotalPages();
/*  36 */     this.startRow = 0;
/*     */   }
/*     */ 
/*     */   public Page() {
/*     */   }
/*     */ 
/*     */   private void initTotalPages() {
/*  43 */     this.totalPages = (this.totalCount / this.pageSize);
/*  44 */     int mod = this.totalCount % this.pageSize;
/*  45 */     if (mod > 0)
/*  46 */       this.totalPages += 1;
/*     */   }
/*     */ 
/*     */   public int getStartRow()
/*     */   {
/*  51 */     return this.startRow;
/*     */   }
/*     */ 
/*     */   public int getTotalPages() {
/*  55 */     return this.totalPages;
/*     */   }
/*     */ 
/*     */   public int getPageNum() {
/*  59 */     return this.pageNum;
/*     */   }
/*     */ 
/*     */   public int getCurrentPage() {
/*  63 */     return this.currentPage;
/*     */   }
/*     */ 
/*     */   public void setTotalCount(int totalCount) {
/*  67 */     this.totalCount = totalCount;
/*  68 */     initTotalPages();
/*     */   }
/*     */ 
/*     */   public void setStartRow(int startRow) {
/*  72 */     this.startRow = startRow;
/*     */   }
			//new
			public void setCurrentPage(int currentPage) {
				this.currentPage = currentPage;
			}
/*     */ 
/*     */   public void setPageNum(int pageNum) {
/*  76 */     this.pageNum = (this.currentPage = pageNum);
/*  77 */     this.startRow = ((pageNum - 1) * this.pageSize);
/*     */   }
/*     */ 
/*     */   public int getPageSize() {
/*  81 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize) {
/*  85 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public int getTotalCount() {
/*  89 */     return this.totalCount;
/*     */   }
/*     */ 
/*     */   public int getEndRow() {
/*  93 */     return this.endRow;
/*     */   }
/*     */ 
/*     */   public void setEndRow(int endRow) {
/*  97 */     this.endRow = endRow;
/*     */   }
/*     */ 
/*     */   public void first() {
/* 101 */     this.pageNum = (this.currentPage = 1);
/* 102 */     this.startRow = 0;
/*     */   }
/*     */ 
/*     */   public void previous() {
/* 106 */     if (this.pageNum == 1) {
/* 107 */       return;
/*     */     }
/* 109 */     this.pageNum -= 1;
/* 110 */     this.currentPage -= 1;
/* 111 */     this.startRow = ((this.pageNum - 1) * this.pageSize);
/*     */   }
/*     */ 
/*     */   public void next() {
/* 115 */     if (this.pageNum < this.totalPages) {
/* 116 */       this.pageNum += 1;
/* 117 */       this.currentPage += 1;
/*     */     }
/* 119 */     this.startRow = ((this.pageNum - 1) * this.pageSize);
/*     */   }
/*     */ 
/*     */   public void last() {
/* 123 */     this.pageNum = this.totalPages;
/* 124 */     this.currentPage = this.totalPages;
/* 125 */     this.startRow = ((this.pageNum - 1) * this.pageSize);
/* 126 */     logger.info("最后页为：" + this.totalPages);
/*     */   }
/*     */ 
/*     */   public void go()
/*     */   {
/* 131 */     if (this.pageNum >= this.totalPages)
/* 132 */       last();
/* 133 */     else if (this.pageNum <= 0)
/* 134 */       first();
/*     */     else
/* 136 */       this.startRow = ((this.pageNum - 1) * this.pageSize);
/*     */   }
/*     */ 
/*     */   public void refresh(int _pageNum)
/*     */   {
/* 141 */     this.pageNum = (this.currentPage = _pageNum);
/* 142 */     if (this.pageNum > this.totalPages)
/* 143 */       last();
/*     */   }
/*     */ 
/*     */   public void setTotalPages(int totalPages)
/*     */   {
/* 148 */     this.totalPages = totalPages;
/*     */   }
/*     */ 
/*     */   public String getOrderField() {
/* 152 */     return this.orderField;
/*     */   }
/*     */ 
/*     */   public void setOrderField(String orderField) {
/* 156 */     this.orderField = orderField;
/*     */   }
/*     */ 
/*     */   public String getOrderDirection() {
/* 160 */     return this.orderDirection;
/*     */   }
/*     */ 
/*     */   public void setOrderDirection(String orderDirection) {
/* 164 */     this.orderDirection = orderDirection;
/* 165 */     this.isAsc = (!"DESC".equals(orderDirection.toUpperCase()));
/*     */   }
/*     */ 
/*     */   public boolean isAsc() {
/* 169 */     return this.isAsc;
/*     */   }
/*     */ 
/*     */   public void setAsc(boolean isAsc) {
/* 173 */     this.isAsc = isAsc;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.Page
 * JD-Core Version:    0.6.2
 */