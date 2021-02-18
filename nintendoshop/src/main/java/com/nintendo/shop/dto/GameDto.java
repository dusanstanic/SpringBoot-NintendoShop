package com.nintendo.shop.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nintendo.shop.models.Console;
import com.nintendo.shop.models.Genre;
import com.nintendo.shop.models.Image;

public class GameDto {
	private int id;
	private String description;
	private String image;
	private String pgRating;
	private int price;
	private Date releaseDate;
	private String title;
	private String status;
	private Genre genre;
	private List<ConsoleDto> consoles = new ArrayList<>();
	private List<Image> images = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPgRating() {
		return pgRating;
	}

	public void setPgRating(String pgRating) {
		this.pgRating = pgRating;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List<ConsoleDto> getConsoles() {
		return consoles;
	}

	public void setConsoles(List<ConsoleDto> consoles) {
		this.consoles = consoles;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "GameDto [id=" + id + ", description=" + description + ", image=" + image + ", pgRating=" + pgRating
				+ ", price=" + price + ", releaseDate=" + releaseDate + ", title=" + title + ", genre=" + genre
				+ ", consoles=" + consoles + ", images=" + images + "]";
	}

}
