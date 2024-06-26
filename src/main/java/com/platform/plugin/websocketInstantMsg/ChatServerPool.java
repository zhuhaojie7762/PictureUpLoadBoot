package com.platform.plugin.websocketInstantMsg;

import org.java_websocket.WebSocket;

import java.util.*;

/**
 * 即时通讯
 * @author FH
 * QQ 313596790
 * 2015-5-16
 */
public class ChatServerPool {

	private static final Map<WebSocket,String> userconnections = new HashMap<WebSocket,String>();
	
	/**
	 * 获取用户名
	 */
	public static String getUserByKey(WebSocket conn){
		return userconnections.get(conn);
	}
	
	/**
	 * 获取WebSocket
	 * @param user
	 */
	public static WebSocket getWebSocketByUser(String user){
		Set<WebSocket> keySet = userconnections.keySet();
		synchronized (keySet) {
			for (WebSocket conn : keySet) {
				String cuser = userconnections.get(conn);
				if(cuser.equals(user)){
					return conn;
				}
			}
		}
		return null;
	}
	
	/**
	 * 向连接池中添加连接
	 */
	public static void addUser(String user, WebSocket conn){
		userconnections.put(conn,user);	//添加连接
	}
	
	/**
	 * 获取所有的在线用户
	 * @return
	 */
	public static Collection<String> getOnlineUser(){
		List<String> setUsers = new ArrayList<String>();
		Collection<String> setUser = userconnections.values();
		for(String u:setUser){
			setUsers.add("<a onclick=\"toUserMsg('"+u+"');\">"+u+"</a>");
		}
		return setUsers;
	}
	
	/**
	 * 移除连接池中的连接
	 */
	public static boolean removeUser(WebSocket conn){
		if(userconnections.containsKey(conn)){
			userconnections.remove(conn);	//移除连接
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 向特定的用户发送数据
	 * @param message
	 */
	public static void sendMessageToUser(WebSocket conn,String message){
		if(null != conn && null != userconnections.get(conn)){
			conn.send(message);
		}
	}
	
	/**
	 * 向所有的用户发送消息
	 * @param message
	 */
	public static void sendMessage(String message){
		Set<WebSocket> keySet = userconnections.keySet();
		synchronized (keySet) {
			for (WebSocket conn : keySet) {
				String user = userconnections.get(conn);
				if(user != null){
					conn.send(message);
				}
			}
		}
	}
}
