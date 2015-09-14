package com.mycompany.integration.project.utils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;


public class FileUtils {

	public static String getFileString(String path) throws Exception {
			
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			StringBuilder builder = new StringBuilder();
			String line;
			
			while ((line = br.readLine()) != null) {
				builder.append(line);
			}
			
			return builder.toString();
		}
	
	
	public static void writeFileString(String path, String content) throws FileNotFoundException {
		try (PrintStream out = new PrintStream(new FileOutputStream(path))) {
		    out.print(content);
		}
	}
}
