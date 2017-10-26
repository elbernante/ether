package edu.mum.cs525.framework.account;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mum.cs525.framework.entity.Account;
import edu.mum.cs525.framework.util.Util;

public class AccountActivityMonitor {
	
	private volatile static AccountActivityMonitor instance;
	
	public static AccountActivityMonitor getInstance() {
		if (null == instance) {
			synchronized (AccountActivityMonitor.class) {
				if (null == instance) {
					instance = new AccountActivityMonitor();
				}
			}
		}
		return instance;
	}
	
	
	private Map<String, List<ObjectMethodPair>> listeners = new HashMap<>();
	
	private List<ObjectMethodPair> getListenersForEvent(String event) {
		List<ObjectMethodPair> l = listeners.get(event);
		if (null == l) {
			l = new ArrayList<>();
			listeners.put(event, l);
		}
		return l;
	}
	
	public static void register(Class<?>... listeners) {
		for (Class<?> listener : listeners) {
			register(listener);
		}
	}
	
	private static void register(Class<?> listener) {
		AccountActivityMonitor aam = getInstance();
		Object o = Util.instantiate(listener);
		for(Method method : o.getClass().getMethods()) {
			if (method.getName().matches("^(on)[A-Z].*$")) {
				String eventName = method.getName().substring(2).toLowerCase();
				aam.getListenersForEvent(eventName).add(new ObjectMethodPair(o, method));
			}
		}
	} 
	
	public static void broadcast(String eventName, Account account, Object...args) {
		getInstance().listeners.getOrDefault(eventName, new ArrayList<>()).stream().forEach(o -> {
			Object[] params = fillParameters(o.method.getParameterTypes(), account, args);
			if (null != params) {
				try {
					o.method.invoke(o.object, params);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException(e.getMessage());
				}
			}
		});
	}
	
	private static Object[] fillParameters(Class<?>[] parameters, Account account, Object...args) {
		// Map class - instance for faster lookup
		Map<Class<?>, Object> argMap = new HashMap<>();
		argMap.put(account.getClass(), account);
		if (account.getCustomer() != null) {
			argMap.put(account.getCustomer().getClass(), account.getCustomer());
		}
		for (Object arg : args) {
			argMap.put(arg.getClass(), arg);
		}
		
		
		// Fill method parameters
		Object[] params = new Object[parameters.length];
		
		for (int i = 0; i < params.length; i++) {
			Object param = argMap.get(parameters[i]);
			
			if (null == param) {
				param = superClassSearch(parameters[i], account, args);
				if (null == param) {
					return null;
				}
			}
			params[i] = param;
		}
		
		return params;
	}
	
	private static Object superClassSearch(Class<?> type, Account account, Object...args) {
		Object param = null;
		
		if (type.isInstance(account)) {
			param = account;
		} else if (type.isInstance(account.getCustomer())) {
			param = account.getCustomer();
		} else {
			for (Object arg : args) {
				if (type.isInstance(arg)) {
					param = arg;
					break;
				}
			}
		}
		
		return param;
	}
	
	private static class ObjectMethodPair {
		public Object object;
		public Method method;
		
		public ObjectMethodPair(Object object, Method method) {
			this.object = object;
			this.method = method;
		}
	}
}
