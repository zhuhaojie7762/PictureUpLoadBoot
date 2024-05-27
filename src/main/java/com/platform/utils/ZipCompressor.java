package com.platform.utils;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCompressor {
	static final int BUFFER = 8192;
	public static void compress(List<String> pathNames,ByteArrayOutputStream baos) {
		ZipOutputStream out = null;
		try {
			CheckedOutputStream cos = new CheckedOutputStream(baos, new CRC32());
			out = new ZipOutputStream(cos);
			String basedir = "";
			for (int i = 0; i < pathNames.size(); i++) {
				String pathName=pathNames.get(i);
				if(pathName.startsWith("smb:")){
					compress(new SmbFile(pathName), out, basedir);
				}else{
					compress(new File(pathName), out, basedir);
				}
				
			}
			out.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	

	private static void compress(File file, ZipOutputStream out, String basedir) {
		/* 判断是目录还是文件 */
		if (file.isDirectory()) {
			System.out.println("压缩：" + basedir + file.getName());
			compressDirectory(file, out, basedir);
		} else {
			System.out.println("压缩：" + basedir + file.getName());
			compressFile(file, out, basedir);
		}
	}

	/** 压缩一个目录 */
	private static void compressDirectory(File dir, ZipOutputStream out, String basedir) {
		if (!dir.exists())
			return;

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			/* 递归 */
			compress(files[i], out, basedir + dir.getName() + "/");
		}
	}

	/** 压缩一个文件 */
	private static void compressFile(File file, ZipOutputStream out, String basedir) {
		if (!file.exists()) {
			return;
		}
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			ZipEntry entry = new ZipEntry(basedir + file.getName());
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}
			bis.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private static void compress(SmbFile file, ZipOutputStream out, String basedir) throws SmbException {
		/* 判断是目录还是文件 */
		if (file.isDirectory()) {
			System.out.println("压缩：" + basedir + file.getName());
			compressDirectory(file, out, basedir);
		} else {
			System.out.println("压缩：" + basedir + file.getName());
			compressFile(file, out, basedir);
		}
	}
	
	/** 压缩一个目录 
	 * @throws SmbException */
	private static void compressDirectory(SmbFile dir, ZipOutputStream out, String basedir) throws SmbException {
		if (!dir.exists())
			return;

		SmbFile[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			/* 递归 */
		    compress(files[i], out, basedir + dir.getName() + (dir.getName().endsWith("/") ? "":"/"));
		}
	}
		
	/** 压缩一个文件 
	 * @throws SmbException */
	private static void compressFile(SmbFile file, ZipOutputStream out, String basedir) throws SmbException {
		if (!file.exists()) {
			return;
		}
		try {
			SmbFileInputStream in = new SmbFileInputStream(file); 
			BufferedInputStream bis = new BufferedInputStream(in);
			ZipEntry entry = new ZipEntry(basedir + file.getName());
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}
			bis.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	

	public static void main(String[] args) throws IOException {
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		List<String> pathNames=new ArrayList<String>();
		//pathNames.add("\\\\192.168.102.229\\project");
		pathNames.add("smb://192.168.102.228/project/Splite/data/gps/20170813104617/");
		pathNames.add("D:\\demo");
		ZipCompressor.compress(pathNames, baos);
		File file=new File("D:\\ZipCompressor.zip");
		DataOutputStream to=new DataOutputStream(new FileOutputStream(file));
		baos.writeTo(to);
		
	}
}