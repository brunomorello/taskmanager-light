package br.com.bmo.taskmanagerlight.externalsystem.accuweather.dto;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.bmo.taskmanagerlight.externalsystem.accuweather.domain.Headline;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HeadlineDTO {

	@JsonProperty(value = "EffectiveDate")
	private String effectiveDate;
	
	@JsonProperty(value = "Text")
	private String text;
	
	@JsonProperty(value = "Category")
	private String category;
	
	@JsonProperty(value = "Link")
	private String link;
	
	public HeadlineDTO() {	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public Headline parse() {
		return new Headline(LocalDateTime.parse(effectiveDate, DateTimeFormatter.ISO_DATE_TIME), text, category, URI.create(link));
	}
	
}
