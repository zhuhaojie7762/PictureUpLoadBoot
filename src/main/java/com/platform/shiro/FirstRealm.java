//package com.platform.shiro;
//
//import com.platform.entity.SysUser;
//import com.platform.service.SysUserService;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.util.ByteSource;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * 此realm既有认证也有授权
// *
// * @author zhuhaojie AuthenticatingRealm //只认证时使用
// */
//public class FirstRealm extends AuthorizingRealm {
//
//	@Autowired
//	private SysUserService sysUserService;
//
//	/**
//	 * 认证
//	 */
//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//		System.out.println("【FirstRealm】doGetAuthenticationInfo:" + token);
//		System.out.println("token.hashCode:" + token.hashCode());
//		/**
//		 * 由于 AuthenticationToken对象是由Subject的login方法传递过来的且
//		 * login()的参数为UsernamePasswordToken所以可以直接强转
//		 */
//		/**
//		 * 1、转换
//		 */
//		UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;
//		/**
//		 * 2、从UsernamePasswordToken中获取用户名
//		 */
//		String userName = passwordToken.getUsername();
//
//		/**
//		 * 根据用户名从数据库中获取密码
//		 */
//
//		SysUser sysUserparam = new SysUser();
//		sysUserparam.setName(userName);
//		SysUser sysUser = null;
//		try {
//			sysUser = sysUserService.get(sysUserparam);
//		} catch (Exception e) {
//			throw new AuthenticationException(e);
//		}
//
//		if (sysUser == null) {
//			throw new UnknownAccountException("用户不存在");
//		}
//
//		/**
//		 * 3、 realmName：realm的名称 当前realm的名称 调用父类的getName()方法就可以
//		 */
//		String realmName = getName();
//		/**
//		 * 4、/** principal:认证的实体对象，可能是用户名也可能是用户实体对象
//		 */
//		Object principal = userName;
//		/**
//		 * credentials
//		 */
//		/**
//		 * credentials:密码 从数据获取的密码
//		 */
//		Object credentials = sysUser.getPassword();
//		// 盐
//		ByteSource salt = ByteSource.Util.bytes(userName);
//		// SimpleAuthenticationInfo info = new
//		// SimpleAuthenticationInfo(principal, credentials,realmName);
//		/**
//		 * 盐值加密
//		 */
//		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, salt, realmName);
//		return info;
//
//	}
//
//	/**
//	 * 授权
//	 */
//	@Override
//	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//
//		System.out.println("ShiroRealm  doGetAuthorizationInfo---------------");
//
//		// 1、从principals对象中获取用户信息
//		/**
//		 * 获取认证实体
//		 */
//		Object principal = principals.getPrimaryPrincipal();
//		// 2、利用登录用户信息获取当前用户的角色和权限信息（可能需要查询数据库）
//		Set<String> roles = new HashSet<String>();
//		roles.add("user");
//		if ("admin".equals(principal)) { // 如果principal是admin的话
//			roles.add("admin");
//		}
//		// 这样可以访问的页面是不一样的，如果是user，只能访问一个，admin可以访问两个
//		// 3、创建SimpleAuthorizationInfo，并设置角色roles
//		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
//		// 返回这个对象
//		return info;
//	}
//
//}
