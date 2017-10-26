package edu.mum.cs525.framework;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class UnmodifiableProxy {

	public static Object createProxy(Object target) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
			if (method.getName().startsWith("set")) {
				throw new IllegalAccessError("Object is not modifiable.");
			}
			return proxy.invoke(target, args);
		});
		
		return enhancer.create();
	}
	
}
