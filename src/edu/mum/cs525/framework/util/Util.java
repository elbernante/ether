package edu.mum.cs525.framework.util;

public class Util {

	public static <T> T instantiate(Class<T> clazz) {
		T o = null;
		try {
			o = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e.getMessage());
		}
		return o;
	}
}
