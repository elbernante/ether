package edu.mum.cs525.framework;

import java.util.HashMap;
import java.util.Map;

import edu.mum.cs525.framework.account.AbstractAccountFactory;
import edu.mum.cs525.framework.account.AccountService;
import edu.mum.cs525.framework.account.AccountServiceImpl;
import edu.mum.cs525.framework.account.AccountServiceProxy;

public class ApplicationContext {
	
	private volatile static ApplicationContext context;
	
	public static ApplicationContext getContext() {
		if (null == context) {
			synchronized (ApplicationContext.class) {
				if (null == context) {
					context = new ApplicationContext();
				}
			}
		}
		return context;
	}
	
	private AccountService accountService = new AccountServiceImpl();
	
	private Map<String, AbstractAccountFactory> accountFactories = new HashMap<>();
	
	private ApplicationContext() {}
	
	public static void registerAccountFactory(AbstractAccountFactory...factories) {
		for (AbstractAccountFactory f : factories) {
			String [] classPackage = f.getClass().getName().split("\\.");
			String className = classPackage[classPackage.length - 1];
			getContext().accountFactories.put(className, f);
		}
	}
	
	public static AbstractAccountFactory getFactoryInstanceForClass(String className) {
		AbstractAccountFactory factory = getContext().accountFactories.get(className);
		if (null == factory) {
			throw new RuntimeException("Cannot find instance for " + className);
		}
		
		return factory;
	}

	public static void setAccountService(Class<? extends AccountService> clazz) {
		 getContext().accountService = AccountServiceProxy.newProxy(clazz);
	}

	public static AccountService getAccountService() {
		return getContext().accountService;
	}
}
