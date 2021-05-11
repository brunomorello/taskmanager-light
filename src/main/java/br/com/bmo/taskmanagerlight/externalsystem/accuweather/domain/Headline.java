package br.com.bmo.taskmanagerlight.externalsystem.accuweather.domain;

import java.net.URI;
import java.time.LocalDateTime;

public class Headline {

	private LocalDateTime effectiveDate;
	private String text;
	private String category;
	private URI link;
	
	public Headline(LocalDateTime effectiveDate, String text, String category, URI link) {
		this.effectiveDate = effectiveDate;
		this.text = text;
		this.category = category;
		this.link = link;
	}

	public LocalDateTime getEffectiveDate() {
		return effectiveDate;
	}

	public String getText() {
		return text;
	}

	public String getCategory() {
		return category;
	}

	public URI getLink() {
		return link;
	}
	
}
