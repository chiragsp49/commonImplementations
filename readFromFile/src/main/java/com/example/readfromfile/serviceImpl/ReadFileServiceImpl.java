package com.example.readfromfile.serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.readfromfile.pojos.Component;
import com.example.readfromfile.pojos.SBOM;
import com.example.readfromfile.service.ReadFileService;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ReadFileServiceImpl implements ReadFileService{

	@Override
	public List<String> getListOfNameAndVersion(String name, String version) {
		
		List<String> fileWithVulnerabilities = new ArrayList<String>(); 
		try {
			List<String> resourceFiles = getResourceFiles("static");
			for(String fileName:resourceFiles) {
				SBOM sbom = readFileAndGetJSONObject("static/"+fileName);
				if(sbom!=null) {
					List<Component> components = sbom.getComponents();
					for(Component component:components) {
						if(component.getName().equals(name) && component.getVersion().equals(version)) {
							fileWithVulnerabilities.add(fileName);
							break;
						}
					}
				}
				
				
			}

		} catch (IOException e) {
			e.printStackTrace();
			// throw error response
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fileWithVulnerabilities;
	}
	
	
	private SBOM readFileAndGetJSONObject(String filePath) throws StreamReadException, DatabindException, IOException, URISyntaxException{
       URL resource = getClass().getClassLoader().getResource(filePath);
        
		SBOM sbom = new ObjectMapper().readValue(new File(resource.toURI()), SBOM.class);
		return sbom;
		
		//OR		
		
//		JsonNode jsonNode = new ObjectMapper().readTree(getClass().getClassLoader().getResourceAsStream(filePath));
//		SBOM sbom = new ObjectMapper().readValue(jsonNode.toPrettyString(), SBOM.class);
//		
//		return sbom;
	}
	
	private List<String> getResourceFiles(String path) throws IOException {
	    List<String> filenames = new ArrayList<>();
	    try (
	        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
	        String resource;

	        while ((resource = br.readLine()) != null) {
	            filenames.add(resource);
	        }
	    }

	    return filenames;
	}

}
