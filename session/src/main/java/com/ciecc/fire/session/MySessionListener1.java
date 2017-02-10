package com.ciecc.fire.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/****
 * 会话监听器用于监听会话创建、过期及停止事件：
 * 
 * @author fire
 *
 */
public class MySessionListener1 implements SessionListener {

	@Override
	public void onStart(Session session) {
		 System.out.println("会话创建：" + session.getId());

	}

	@Override
	public void onStop(Session session) {
		 System.out.println("会话过期：" + session.getId());

	}

	@Override
	public void onExpiration(Session session) {
		System.out.println("会话停止：" + session.getId());

	}

}
