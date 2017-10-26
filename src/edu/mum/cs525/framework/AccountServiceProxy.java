package edu.mum.cs525.framework;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class AccountServiceProxy {

	public static AccountService newProxy(Class<? extends AccountService> clazz) {
		return clazz.isInterface() ? interFaceServiceClass(clazz) : nonInterfaceServiceClass(clazz);
	}
	
	private static AccountService nonInterfaceServiceClass(Class<? extends AccountService> clazz) {
		return (AccountService) Util.instantiate(clazz);
	}
	
	private static AccountService interFaceServiceClass(Class<? extends AccountService> clazz) {
		AccountService instance = new AccountServiceImpl();
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
			Object retval = null;
			if (method.getName().matches("^(create)[A-Z].*(Account)$")) {
				String className = method.getName().substring(6) + "Factory";
				Class<?> accountType = method.getReturnType();
				AbstractAccountFactory factory = ApplicationContext.getFactoryInstanceForClass(className);
				
				Method m = instance.getClass().getMethod("createAccount", AbstractAccountFactory.class, Class.class, String.class, Customer.class);
				retval = m.invoke(instance, new Object[] {factory, accountType, args[0], args[1]});
			} else {
				retval = proxy.invoke(instance, args);
			}
			return retval;
		});

		return (AccountService) enhancer.create();
	}
}
