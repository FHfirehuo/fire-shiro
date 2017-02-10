package com.ciecc.fire.session;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;

public class Manager {

	private void managersession() {
		login("classpath:shiro.ini", "zhang", "123");
		Subject subject = SecurityUtils.getSubject();

		// 登录成功后使用Subject.getSession()即可获取会话；其等价于Subject.getSession(true)，
		// 即如果当前没有创建Session对象会创建一个；另外Subject.getSession(false)，
		// 如果当前没有创建Session则返回null（不过默认情况下如果启用会话存储功能的话在创建Subject时会主动创建一个Session）。
		Session session = subject.getSession();

		// 获取当前会话的唯一标识。
		session.getId();

		// 获取当前Subject的主机地址，该地址是通过HostAuthenticationToken.getHost()提供的。
		session.getHost();

		// 获取/设置当前Session的过期时间；如果不设置默认是会话管理器的全局过期时间。
		session.getTimeout();
		session.setTimeout(111); // 毫秒

		// 获取会话的启动时间及最后访问时间；如果是JavaSE应用需要自己定期调用session.touch()去更新最后访问时间；
		// 如果是Web应用，每次进入ShiroFilter都会自动调用session.touch()来更新最后访问时间。
		session.getStartTimestamp();
		session.getLastAccessTime();

		// 更新会话最后访问时间及销毁会话；当Subject.logout()时会自动调用stop方法来销毁会话。
		// 如果在web中，调用javax.servlet.http.HttpSession.invalidate()
		// 也会自动调用Shiro Session.stop方法进行销毁Shiro的会话。
		session.touch();
		session.stop();

		//设置/获取/删除会话属性；在整个会话范围内都可以对这些属性进行操作。 
		session.setAttribute("key", "123");
		Assert.assertEquals("123", session.getAttribute("key"));
		session.removeAttribute("key");
		
		
		

	}

	private void login(String string, String string2, String string3) {
		// TODO Auto-generated method stub

	}
}
