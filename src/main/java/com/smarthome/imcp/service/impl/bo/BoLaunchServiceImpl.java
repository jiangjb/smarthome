/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.ContextUtil;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.bo.BoLaunchDaoIface;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaLaunch;
/*     */ import com.smarthome.imcp.dao.model.bo.BoLaunch;
/*     */ import com.smarthome.imcp.dao.vo.system.FileVo;
/*     */ import com.smarthome.imcp.exception.BusinessException;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoLaunchServiceIface;
/*     */ import com.smarthome.imcp.service.system.FileServiceIface;
/*     */ import java.awt.Image;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import javax.imageio.ImageIO;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boLaunchService")
/*     */ public class BoLaunchServiceImpl extends AbstractBasicService<BoLaunch, Serializable>
/*     */   implements BoLaunchServiceIface<BoLaunch, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoLaunchDaoIface<BoLaunch, Serializable> boLaunchDao;
/*     */ 
/*     */   @Autowired
/*     */   private FileServiceIface<Object, Serializable> fileService;
/*     */ 
/*     */   public List<BoLaunch> getListByGroupId(Integer groupId, Integer type)
/*     */   {
/*  42 */     SearchCriteriaLaunch searchCriteriaLaunch = new SearchCriteriaLaunch();
/*  43 */     searchCriteriaLaunch.setGroupId(groupId);
/*  44 */     searchCriteriaLaunch.setType(type);
/*  45 */     return this.boLaunchDao.getList(searchCriteriaLaunch);
/*     */   }
/*     */ 
/*     */   public List<BoLaunch> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  50 */     SearchCriteriaLaunch searchCriteriaLaunch = (SearchCriteriaLaunch)searchCriteria;
/*  51 */     if (chkCriteriaValid(searchCriteria)) {
/*  52 */       return this.boLaunchDao.getList(searchCriteriaLaunch, page);
/*     */     }
/*  54 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoLaunch model)
/*     */   {
/*  59 */     return true;
/*     */   }
/*     */ 
/*     */   public BoLaunch save(BoLaunch model)
/*     */   {
/*  64 */     if (chkSaveValid(model)) {
/*  65 */       this.boLaunchDao.save(model);
/*     */     }
/*     */ 
/*  68 */     saveFile(model);
/*  69 */     return model;
/*     */   }
/*     */ 
/*     */   private void saveFile(BoLaunch model) {
/*  73 */     String fileNames = model.getFileNames();
/*  74 */     String root = System.getProperty("webapp.root");
/*     */ 
/*  76 */     if (StringUtils.isNotEmpty(fileNames)) {
/*  77 */       String desDir = "uploads/images";
/*     */ 
/*  79 */       StringTokenizer agendast = new StringTokenizer(fileNames, ",");
/*  80 */       while (agendast.hasMoreTokens()) {
/*  81 */         String fileName = agendast.nextToken().trim();
/*     */ 
/*  83 */         String tempStr = root + "temp" + "/" + ContextUtil.getSessionId() + "/" + fileName;
/*  84 */         File tempFile = new File(tempStr);
/*  85 */         if (!tempFile.exists()) {
/*  86 */           throw new BusinessException("文件不存在！");
/*     */         }
/*     */ 		  Image image;
/*     */         try
/*     */         {
/*  91 */           image = ImageIO.read(tempFile);
/*     */         }
/*     */         catch (IOException e)
/*     */         {
///*     */           Image image;
/*  93 */           throw new BusinessException("文件不存在！");
/*     */         }
/*     */         
/*  96 */         int height = image.getHeight(null);
/*  97 */         int width = image.getWidth(null);
/*     */ 
/* 103 */         model.setImageHeight(Integer.valueOf(height));
/* 104 */         model.setImageWidth(Integer.valueOf(width));
/*     */ 
/* 106 */         FileVo fileVo = this.fileService.copyTempFileToDir(fileName, desDir, ContextUtil.getSessionId());
/*     */ 
/* 108 */         model.setImageFilename(fileName);
/* 109 */         model.setImageSize(Integer.valueOf(fileVo.getFileSize().intValue()));
/* 110 */         model.setImagePath(fileVo.getPathName());
/* 111 */         model.setFileSizeName(fileVo.getFileSizeName());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public BoLaunch findByKey(Serializable id)
/*     */   {
/* 118 */     BoLaunch model = (BoLaunch)this.boLaunchDao.findById(id);
/* 119 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoLaunch model)
/*     */   {
/* 124 */     return true;
/*     */   }
/*     */ 
/*     */   public BoLaunch update(BoLaunch model)
/*     */   {
/* 129 */     if (chkUpdateValid(model)) {
/* 130 */       this.boLaunchDao.update(model);
/*     */     }
/* 132 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String id)
/*     */   {
/* 137 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/* 142 */     if (chkDeleteValid(id))
/* 143 */       this.boLaunchDao.deleteByKey(id);
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 149 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 150 */     while (st.hasMoreElements()) {
/* 151 */       String id = st.nextToken();
/* 152 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoLaunchServiceImpl
 * JD-Core Version:    0.6.2
 */