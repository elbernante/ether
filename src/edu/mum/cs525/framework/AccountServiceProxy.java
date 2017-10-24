package edu.mum.cs525.framework;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class AccountServiceProxy {

	public static AccountService newProxy(Class<? extends AccountService> clazz) {
		AccountService instance;
		if (clazz.isInterface()) {
			instance = new AccountServiceImpl();
		} else {
			instance = Util.instantiate(clazz);
		}
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
			Object retval = null;
			if (method.getName().matches("^(create)[A-Z].*(Account)$")) {
				Method m = instance.getClass().getMethod("createAccount", Class.class, String.class, Customer.class);
				retval = m.invoke(instance, new Object[] {method.getReturnType(), args[0], args[1]});
			} else {
				retval = proxy.invoke(instance, args);
			}
			return retval;
		});

		return (AccountService) enhancer.create();
	}
}
