package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Parser {
	
	private File inputFile;
	private HashMap<String, String> hmEvidence = new HashMap<String, String>();
	
	public Parser(File inputFile){
		this.inputFile = inputFile;
		parseFile();
	}
	
	private void parseFile(){
		Path filepath = Paths.get(inputFile.getAbsolutePath());
		String line;
		try(BufferedReader reader = Files.newBufferedReader(filepath, StandardCharsets.UTF_8)){
			while((line = reader.readLine()) != null){
				String[] entries = line.split("\t");
				hmEvidence.put(entries[0], entries[1].toLowerCase());
			}
		}
		catch(IOException e){
			
		}
		
	}
	
	public HashMap<String, String> getEvidence(){
		return hmEvidence;
	}
	
	
	

}
