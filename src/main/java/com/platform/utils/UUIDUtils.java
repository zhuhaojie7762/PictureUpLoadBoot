package com.platform.utils;

import java.util.UUID;

public abstract class UUIDUtils {

	public static String getUUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	public static void main(String[] args) {
		System.out.println(getUUID());
	}
}