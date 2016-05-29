package de.mediapool.server.externsearch.google;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import de.mediapool.server.externsearch.components.ExternalSearchCompoenent;
import de.mediapool.server.externsearch.domain.ExternalSearchResult;
import de.mediapool.server.externsearch.google.domain.GoogleSearchItem;
import de.mediapool.server.externsearch.google.domain.GoogleSearchResult;

@Component
public class GoogleSearch implements ExternalSearchCompoenent {

	@Override
	public List<ExternalSearchResult> search (String searchQuery) {
//		https://www.googleapis.com/customsearch/v1?key=&cx=&q=5425016922194
		
        RestTemplate restTemplate = new RestTemplate();
        
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        
        ResponseEntity<GoogleSearchResult> forObject = restTemplate.exchange("https://www.googleapis.com/customsearch/v1?key={key}&cx={cx}&q={q}", HttpMethod.GET, entity, GoogleSearchResult.class, getParameter(searchQuery));
        
        return getResults(forObject.getBody());
	}
	

	private List<ExternalSearchResult> getResults(GoogleSearchResult googleSearchResult) {
		List<ExternalSearchResult> returnList = new ArrayList<>();
		
		for(GoogleSearchItem item : googleSearchResult.getItems()) {
			ExternalSearchResult externalSearchResult = new ExternalSearchResult();

			if(item.getPagemap() == null || item.getPagemap().getProduct().isEmpty()) {
				continue;
			}
			
			externalSearchResult.setImgSource(item.getPagemap().getCse_image().get(0).getSrc());
			externalSearchResult.setTitle(item.getPagemap().getProduct().get(0).getName());
			externalSearchResult.setSource("");
			externalSearchResult.setExternalSource(getName());
			
			returnList.add(externalSearchResult);
		}
		
		return returnList;
	}

	@Override
	public String getName() {
		return "Google API Search";
	}
	
	protected Map<String, String> getParameter(String searchQuery) {
		Map<String, String> parameters = new HashMap<>();
		

		parameters.put("q", searchQuery);
		
		return parameters;
	}

}
