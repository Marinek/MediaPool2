package de.mediapool.server.externsearch.domain;

import org.apache.commons.lang3.StringUtils;

import de.mediapool.server.core.domain.DataTransfereObject;
import lombok.Getter;
import lombok.Setter;

public class ExternalSearchResult implements DataTransfereObject, Comparable<ExternalSearchResult> {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private String title;
	
	@Getter
	private String imgSource;
	
	@Getter
	@Setter
	private String externalSource;

	@Getter
	@Setter
	private String source;
	
	@Getter
	private int score = 0;

	public void setTitle(String title) {
		if(!StringUtils.isEmpty(title)) {
			score++;
		}
		this.title = title;
	}

	public void setImgSource(String imgSource) {
		if(!StringUtils.isEmpty(imgSource)) {
			score++;
		}
		this.imgSource = imgSource;
	}

	@Override
	public int compareTo(ExternalSearchResult o) {
		if(o == null) {
			return 0;
		}
		
		return o.getScore() - this.getScore();
	}
	
}
