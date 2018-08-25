package com.fei.common.util.generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.exception.ShellException;

public class MyShellCallback implements ShellCallback{
	
	private static final Pattern SELF_CODE_START_PATTERN = Pattern.compile("^\\s*//self_code_start\\s*$") ; 
	
	private static final Pattern SELF_CODE_END_PATTERN = Pattern.compile("^\\s*//self_code_end\\s*$") ; 
	
	private static final Pattern SELECT_BY_EXAMPLE = Pattern.compile("^\\s*List<(.*)>\\s*selectByExample\\((\\w+)\\s+(\\w+)\\);\\s*$") ; 
	
	@Override
	public File getDirectory(String targetProject, String targetPackage) throws ShellException {
		File workspaceFile = getWorkSpace() ;
		File parentFile = new File(workspaceFile,targetProject) ; 
		String targetPackagePath = targetPackage.replaceAll("\\.","\\\\") ;
		File file = new File(parentFile,targetPackagePath);
		return file ; 
	}

	@Override
	public String mergeJavaFile(String newFileSource, String existingFileFullPath, String[] javadocTags,
			String fileEncoding) throws ShellException {
		File file = new File(existingFileFullPath) ; 
		if(!file.exists()){
			return  newFileSource; 
		}
		StringBuilder sb = new StringBuilder("") ; 
		Scanner in;
		boolean flag = false ; 
		boolean add = false; 
		String selectOneByExample = "" ; 
		try {
			in = new Scanner(new FileInputStream(file));
			while(in.hasNext()){
				String line = in.nextLine() ;
				Matcher matcher  = SELECT_BY_EXAMPLE.matcher(line) ; 
				if(matcher.find()){
					selectOneByExample += "\t" + matcher.group(1) + " selectOneByExample("+matcher.group(2)+" "+matcher.group(3)+");\n"; 
				}
				if(SELF_CODE_START_PATTERN.matcher(line).matches()){
					flag = true ;
					add = true ; 
					sb.append("\n") ; 
				}
				if(flag){
					sb.append(line+"\n") ; 
				}
				if(SELF_CODE_END_PATTERN.matcher(line).matches()){
					flag = false ; 
				}
			}
			in.close();  
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(add){			
			sb.append("\n}") ; 
		}else{
			sb.append("\n\t//self_code_start\n\n\t//self_code_end\n}") ; 
		}
		String newValue = newFileSource.substring(0,newFileSource.lastIndexOf("}")) + "\n" + selectOneByExample +sb.toString() ; 
		return newValue; 
	}

	@Override
	public void refreshProject(String project) {
	}

	@Override
	public boolean isMergeSupported() {
		return true;
	}

	@Override
	public boolean isOverwriteEnabled() {
		return false;
	}
	
	public File getWorkSpace(){
		String path = System.getProperty("user.dir") ; 
		path = path.substring(0,path.lastIndexOf("\\")) ; 
		return new File(path) ; 
	}
	
	
	
}
