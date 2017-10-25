package edu.mum.cs525.framework;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class AccountProxy {
	
	public static Account newProxy(Account account) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(account.getClass());
		enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
			Object retval = proxy.invoke(account, args);
			
			if (method.getName() == Account.DEPOSIT && null != retval) {
				account.trigger(Account.DEPOSIT, retval);
			}
			
			if (method.getName() == Account.WITHDRAW && null != retval) {
				account.trigger(Account.WITHDRAW, retval);
			}
			
			if (method.getName() == Account.ADD_INTEREST && null != retval) {
				account.trigger(Account.ADD_INTEREST, retval);
			}
			
			return retval;
		});
		return (Account) enhancer.create();
	}
}
