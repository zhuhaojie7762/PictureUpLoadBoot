package com.platform.util;

import java.util.UUID;

public class UUIDUtils {

	public static String get32UUID() {
		return UUID.randomUUID().toString().trim().replaceAll("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(get32UUID());
	}
}

