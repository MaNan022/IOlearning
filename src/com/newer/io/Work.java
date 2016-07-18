package com.newer.io;

import java.awt.event.FocusEvent;
import java.io.File;

public class Work {

	public static void main(String[] args) {
		
		File f = new File("/users/manan/documents/a");
	
		System.out.println(Work.dirSize(f));
		System.out.println(Work.dirLength(f));
		
		DirInfo info = Work.getDirInfo(f);
		info.show();
	}

	//内部类
	//静态上下文中，不能访问非静态的类型（资源）
	static class DirInfo{
		File dir;
		long length;
		int fileSize;
		int dirSize;
		public void show(){
			System.out.println(dir.getAbsolutePath());
			System.out.printf("大小;%,12d\n",length);
			System.out.printf("文件;%,12d\n",fileSize);
			System.out.printf("目录;%,12d\n",dirSize);
		}
	}
	
	/**
	 * 统计目录信息
	 * @param dir 目录
	 * @return DirInfo
	 */
	public static DirInfo getDirInfo(File dir){
		DirInfo info = new DirInfo();
		info.dir = dir;
		File[] files = dir.listFiles();
		if(files != null){
			//遍历
			for (File f : files) {
				if(f.isFile()){
					//文件
					//文件计数器累加
					info.fileSize++;
					//目录大小累加
					info.length +=f.length();
				}else{
					//子目录
					
					//目录的计数器累加
					info.dirSize ++;
					//进入子目录(递归)
					DirInfo innerInfo = getDirInfo(f);
					
					info.fileSize += innerInfo.fileSize;
					info.dirSize += innerInfo.fileSize;
					info.length += innerInfo.length;
				}
			}
		}
		return info;
	}
	
	/**
	 *  目录中子目录（文件夹）的数量
	 * @param dir
	 * @return
	 */
	public static int dirSize(File dir) {
		int i = 0;
		
		File[] files = dir.listFiles();
		if(files == null){
			return i;
		}else{
			for (File f : files) {
				if(f.isDirectory()){
					i++;
					
					//进入目录（递归）
					i += dirSize(f);
				}
			}
			return i;
		}
		
	}

	/*
	 * 目录中文件夹的数量
	 */
	public static int dirCounter(File file) {
		int c = 0;				//文件夹数目
		int cf = 0;				//文件数目
		File f = new File("");
		if(f.isDirectory()){
			File[] files = f.listFiles();
			for (File f1 : files) {
				if(f1.isDirectory()){
					c++;
					dirCounter(f1);
				}else{
					cf++;
				}
			}
		}
		System.out.println("目录中文件夹数目为："+c);
		return c;
	}
//------------------------------------------------------------
	
	/**
	 * 删除文件或目录
	 * @param dir要删除的文件或目录
	 * @return  成功删除返回true 否则返回false
	 */
	public static boolean rm(File dir) {

		if (dir.isFile()){
			//文件，直接删除
			return dir.delete();
		}else{
			//获得目录中的内容
			File[] files = dir.listFiles();
			//目录中有内容
			if(files != null){
				//遍历目录里的内容并删除
				for (File f : files) {
					if(f.isFile()){
						//文件，直接删除
						f.delete();
					}else{
						//子目录：递归（调用方法自身）
						rm(f);
					}
				}
			}
			//删除目录
			return dir.delete();
		}
	}
	
	
	/**
	 * 统计目录中文件的数量
	 * @param dir 目录
	 * @return int 文件数量
	 */
	public static int fileSize(File dir){
		int i = 0;
		//获得目录中的内容
		File[] files = dir.listFiles();
		for (File f : files) {
			if(f.isFile()){
				i++;
			}else{
				//子目录
				i += fileSize(f);
			}
		}
		return i;
	}
	
	
	/**
	 * 目录的大小（所有文件大小的总和）
	 * @param dir
	 * @return
	 */
	public static long dirLength(File dir){
		long length = 0;
		
		File[] files = dir.listFiles();
		for (File f : files) {
			if(f.isFile()){
				length += f.length();
			}else{
				//子目录:递归，进入各个子目录的层次
				length += dirLength(f);
			}
		}
		return length;
	}
	
	
}
