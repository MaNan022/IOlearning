package com.newer.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Date;

public class FileDemo {

	public static void main(String[] args) {

		showRoots();
		
//		showFileInfo();	
		
		//ctrl + 1  修复代码建议
//		createFile();
	
//		deleteFile();
		
//		System.out.println(f(7));

//		System.out.println(gcd(9,6));
		
//		System.out.println(fileCounter(new File("c:/study")));
		
//		dirFilter();
	}
	
	private static void dirFilter() {
		File path = new File("c:\\");
		
		File[] files = path.listFiles(new FileFilter(){		//匿名内部类FileFilter
			public boolean accept (File f){
				
				return (f.isFile() && f.length() >1024 * 1024);
			}
		});
		
		for (File f : files) {
			System.out.println(f.getName());
		}
	}

//	static class Foo implements FileFilter{
//		public boolean accept (File pathname){
//			//return pathname.isDirectory();
//			return pathname.getName().endsWith(".jar");
//		}
//	}
	
	/**
	 * 递归  统计文件夹中文件的数量
	 * @param dir
	 * @return
	 */
	public static int fileCounter(File dir){
		
		int c = 0;
		File[] files = dir.listFiles();
		for (File f : files) {
			if(f.isFile()){
				c++;
			}else{
				//目录
				c += fileCounter(f);
			}
		}
		return c;
	}
	

	/**
	 * 递归
	 * 寻找a,b的最大公约数
	 * @param a
	 * @param b
	 * @return
	 */
	public static int gcd(int a,int b){
		if(b == 0){
			return a;
		}
		return gcd(b, a % b);
	}

	/**
	 * 递归
	 * 求n的阶乘  n!
	 * @param n
	 * @return
	 */
	public static int f(int n){
		if(n == 1){
			return 1;
		}
		return n * f(n - 1);
	}
	
	
	private static void deleteFile() {
//-----------------删除文件----------------
		File f1 = new File("abc.txt");
		System.out.println(f1.delete());
		
//-----------------删除文件夹----------------
//文件夹必须为空才可删除，否则不能删除  29+36
		
		File f2 = new File("c:/hello");
		//获得文件夹内的内容
		File[] files = f2.listFiles();
		//循环删除文件夹内各个文件
		for (File f : files) {
			f.delete();
		}
		//最后删除文件夹
		System.out.println(f2.delete());
		
	}

	private static void createFile() {

//-----------------创建一个文件-------------------------
		File f1 = new File("abc.txt");		//相对路径
//		System.out.println(f1.exists());
		try {
			boolean b = f1.createNewFile();
			System.out.println(b);
		//	System.out.println(f1.createNewFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
//------------------首先检测文件是否存在-----------------------		
		File f2 = new File("as.txt");
		if(!f2.exists()){
			try {
				f1.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//---------------File("指定文件夹","所创建文件")---------------		
		File f3 = new File("c:\\study\\adv","a.txt");
		boolean b;
		try {
			b = f3.createNewFile();
			System.out.println(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//--------------------创建文件夹-----------------------------		
//		File path = new File("c:\\study\\adv");
		//path.mkdirs()   创建嵌套目录结构(多个文件夹)
//		System.out.println(path.mkdir());
		
//-----------例----创建多级文件夹并在里边创建hello.java-----------------		
		File path1 = new File("./com/ibm/hr");
		if(!path1.exists()){
			path1.mkdirs();
		}
		File f4 = new File(path1,"hello.java");
		try {
			f4.createNewFile();
			System.out.println("创建成功");
		} catch (IOException e) {
			e.printStackTrace();
		}

//--------------------------文件重命名-----------------------
		f4.renameTo(new File(path1,"he.java"));
		
	}

	private static void showFileInfo() {
		//分隔符
		//C:\study\
		// /user/abc
			
		//拼接路径 		windows\	unix/	不同操作系统显示不同
//		System.out.println(File.separator);
//		System.out.println(File.separatorChar);
			
		//路径分隔符	windows;	unix:
//		System.out.println(File.pathSeparator);		//String
//		System.out.println(File.pathSeparatorChar);	//char
			
			//char/boolean/基本类型-->String
			//'a' + '' = "a"
			//String.valueOf(a)
			int a = 1;
			String s = String.valueOf(a);
			//	\转译
//		System.out.println("hello \\\n world");
			
			//f1 用来操作当前目录的引用/指针
			File f1 = new File("C:\\mine");
			System.out.println(f1.isDirectory());		//是目录？
			System.out.println(f1.isAbsolute());		//是绝对路径？
			System.out.println(f1.isFile());			//是文件？
			System.out.println(f1.isHidden());			//是否隐藏？
			System.out.println(f1.exists());			//是否存在？
			
		//绝对路径
			//c:\ajva\jdk
			// /user/ab
			
		//相对路径	
			//jdk
			//jdk/bin
			// .jdk/bin   .当前路径
			// ..jdk/bin  ..上一级目录
			
			//查看目录里的内容：文件、目录
			File[] files = f1.listFiles();
			System.out.println(files.length);
			System.out.println();
			
			System.out.printf("类型\t大小\t\t路径\t\t\t最后修改日期\n");
			for (File f : files) {
				
				
				String type = f.isFile() ? "文件" : "目录";
				long size = f.length();
				long t = f.lastModified();
				Date time = new Date(t);
				//%7d  7为数字，不足补0
				//%,7d  ,分离数字
				//f.getName()当前路径
				//System.out.printf("%s\t%d\t%s\n",type,size,f);
				//time.toLocaleString()  将时间换成本地时间
				System.out.printf("%s\t%,8d\t%s\t\t%s\n",type,size,f.getName(),time.toLocaleString());
	
			}
	}

	/*
	 * 《重构》(refactor)Martin Fowler
	 */
	private static void showRoots() {
		//显示系统根(root)目录的信息      mac/	
		File[] roots = File.listRoots();
		for (File f : roots) {	//fore
			System.out.println(f);
		}
	}
}
