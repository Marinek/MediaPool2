package de.mediapool.server.entities.product.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.mediapool.server.core.domain.Node;
import de.mediapool.server.entities.media.movies.domain.Movie;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NodeEntity
public class Product extends Node {

	private static final long serialVersionUID = 1L;

	private String title;

	private String orginaltitle;

	private int publishedYear;

	private String special;

	private String language;

	private double price;

	private String cover;

	private String description;

	private String ean;

	private String format;

	private int duration;

	private int ageRestriction;

	private int numberOfDiscs;

	private MediaType mediaType;

	@JsonIgnore
	@RelatedTo(type = "IS_ON", direction = Direction.INCOMING)
	private @Fetch Set<Movie> movies;

	public Product(MediaType mediaType, String title, String orginaltitle, int publishedYear, String special, String language, double price, String cover, String description, String ean,
			String format) {
		super();
		this.mediaType = mediaType;
		this.title = title;
		this.orginaltitle = orginaltitle;
		this.publishedYear = publishedYear;
		this.special = special;
		this.language = language;
		this.price = price;
		this.cover = cover;
		this.description = description;
		this.ean = ean;
		this.format = format;
	}

	public Product() {
		super();
	}

	public boolean addMovie(Movie movie) {
		if (MediaType.MOVIE == this.mediaType) {
			if (movies == null) {
				movies = new HashSet<Movie>();
			}
			movies.add(movie);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "ProductNodeDTO [title=" + title + "]";
	}

}
