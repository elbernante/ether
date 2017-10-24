package edu.mum.cs525.framework;

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
	
	private ApplicationContext() {}

	public static void setAccountService(Class<? extends AccountService> clazz) {
		 getContext().accountService = AccountServiceProxy.newProxy(clazz);
	}
	
	public static AccountService getAccountService() {
		return getContext().accountService;
	}

}
