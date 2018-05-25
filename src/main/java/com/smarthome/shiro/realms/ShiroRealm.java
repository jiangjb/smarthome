package com.smarthome.shiro.realms;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.smarthome.imcp.dao.model.system.SysUser;
import com.smarthome.imcp.service.bo.PermissionServiceIface;
import com.smarthome.imcp.service.bo.RolePermissionServiceIface;
import com.smarthome.imcp.service.bo.RoleServiceIface;
import com.smarthome.imcp.service.bo.UserRoleServiceIface;
import com.smarthome.imcp.service.system.SysUserServiceIface;
import com.smarthome.shiro.entity.Permission;
import com.smarthome.shiro.entity.Role;
import com.smarthome.shiro.entity.Role_Permission;
import com.smarthome.shiro.entity.User_Role;

/**
 * @author Bing
 *
 */
public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private SysUserServiceIface<SysUser, Serializable> sysUserService;
	
	@Autowired
	private UserRoleServiceIface<User_Role, Serializable> userRoleService;
	
	@Autowired
	private RoleServiceIface<Role, Serializable> roleService;
	
	@Autowired
	private PermissionServiceIface<Permission, Serializable> permissionService;
	
	@Autowired
	private RolePermissionServiceIface<Role_Permission, Serializable> rolePermissionService;
	
	
	/** 
	 * @author Bing  
	 * 重写 认证 方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		System.out.println("[jdbcRealm] doGetAuthenticationInfo");
		
		//1. 把 AuthenticationToken 转换为 UsernamePasswordToken 
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		//2. 从 UsernamePasswordToken 中来获取 username和密码
		String username = upToken.getUsername();
		char[] c=upToken.getPassword();
//		String password = String.valueOf(c); 
		String password = new String(c); 
//		System.out.println("username:"+username);//root
		System.out.println("password:"+password);//[C@35869a46
		
		//3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
		System.out.println("从数据库中获取 username: " + username + " 所对应的用户信息.");
		
		//4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
		if("unknown".equals(username)){
			throw new UnknownAccountException("用户不存在!");
		}
		
		//5. 根据用户信息的情况, 决定是否需要抛出其他的 AuthenticationException 异常. 
		if("monster".equals(username)){
			throw new LockedAccountException("用户被锁定");
		}
		
		//6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
		//以下信息是从数据库中获取的.
		//1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象. 
		Object principal = username;//这里指定是username
		//2). credentials: shiro加密密码. 
		Object credentials = null; 
		
		//shiro加密密码
	    String hashAlgorithmName = "MD5";
		Object credentials01 = password;
//		Object salt = ByteSource.Util.bytes(username);//loginName在找回密码中用不了，所以把这个去了
		Object salt = ByteSource.Util.bytes("username");
		int hashIterations = 1024;
		Object result = new SimpleHash(hashAlgorithmName, credentials01, salt, hashIterations);
		String userPwd=result.toString();
		//确定用户是否存在
		SysUser sysUser = (SysUser)this.sysUserService.checkUser(username, userPwd);
		//若存在则把shiro加密后密码 赋值 给 credentials
		if(sysUser !=null) {
			credentials = userPwd;
		}
//		if("root".equals(username)){
//			credentials = "a9376b751f778ce53d8c5049a85c95c7";
//		}else if("user".equals(username)){
//			credentials = "098d2c478e9c11555ce2823231e02ec1";
//		}
		
		//3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
		String realmName = getName();
		//4). 盐值. 
//		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		ByteSource credentialsSalt = ByteSource.Util.bytes("username");
		
		SimpleAuthenticationInfo info = null; //new SimpleAuthenticationInfo(principal, credentials, realmName);
		info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);//这个方法，，，不清楚
//		System.out.println("info:"+info);//root
		return info;
	}

	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
//		String hashAlgorithmName01 =jdbcReams  //如何获取配置文件里面的属性
		Object credentials = "SZ2018mb168";
		Object salt = ByteSource.Util.bytes("username");
		int hashIterations = 1024;
		
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);//4c65ed253b74c54922c87081af54375d
//		String pwd=result.toString();//Object 转      String
		System.out.println(result);
//		System.out.println(pwd);
	}

	
	/* 
	 * @author Bing 
	 * 重写授权方法     会被 shiro 回调的方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		//1. 从 PrincipalCollection 中来获取登录用户的信息
		String userName=(String)principals.getPrimaryPrincipal();  
		
		//2. 利用登录的用户的信息来用户当前用户的角色或权限(可能需要查询数据库)
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();   
        try{
        	//根据用户名找到角色，然后存入info
        	//1)找到对应的SysUser
        	SysUser sysuser=this.sysUserService.getSysUsersByName(userName);
        	if(sysuser != null) {	
        		//2）取出userId
        		int userID=sysuser.getUserId();
        		//3）找到对应的Role(User-role中间表--》roleId   》Role     SysUser和Role:一对多关系)
        		List<Integer> roleIds=this.userRoleService.findListByUid(userID);
        		System.out.println("......roleIds:"+roleIds+",userID:"+userID);
        		List<Role> roles = null;
        		if(roleIds != null) {
        			roles=this.roleService.getListByRID(roleIds);
        			//4)取出里面的roleName,存入set集合
        			Set<String> roleNames= new HashSet<String>();
        			for(int i=0;i<roles.size();i++) {
        				String roleName = roles.get(i).getRole_name();
        				roleNames.add(roleName);
        			}
        			//测试set集合里是否有数据   ==测试通过
//        			Iterator<String> it = roleNames.iterator();  
//        			while (it.hasNext()) {  
//        				String str = it.next();  
//        				System.out.println("roleName:"+str);  
//        			}
        			//5)set集合放入setRoles方法
        			info.setRoles(roleNames); 
        			//根据用户名找到permission，然后放入info
                    //1)找到对应的SysUser
                    //2)取出userId
                    //3)找到对应的Role,取出roleId;并根据roleId，找到对应的permission
                    Set<String> permissionNames= new HashSet<String>();
//                    System.out.println("roleIds:"+roleIds);//有值 root 1
                	//根据roleIds找到对应的permission  （roleIds 》 permission_id 》 Permission）
                    List<Integer> permissionIds=this.rolePermissionService.findListByRids(roleIds);
                    System.out.println("permissionIds:"+permissionIds);
                    List<Permission> permissions = null;
                    if(permissionIds != null) {
                    	permissions=this.permissionService.getListByPid(permissionIds);
                    	for(int j=0;j<permissions.size();j++) {
                    		String permissionName=permissions.get(j).getPermission_name();
                    		permissionNames.add(permissionName);
                    	}
                    	//测试set集合里面的值
                    	Iterator<String> it01 = permissionNames.iterator();  
                    	while (it01.hasNext()) {  
                    		String str = it01.next();  
                    		System.out.println("permissionName:"+str);  
                    	} 
                    	//4）存入setStringPermissions方法
                    	info.setStringPermissions(permissionNames);  
                    }else {
                    	System.out.println("该角色暂未分配权限...");
                    }
        		}else {
        			System.out.println("该用户未分配角色...");
        		}        		   	
        	}else {
        		System.out.println("用户不存在...");
        	}
        }catch(Exception e){  
    		e.printStackTrace();  
    	}                   
		return info;
	}
}
