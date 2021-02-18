package com.nintendo.shop.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the console database table.
 * 
 */
@Entity
@NamedQuery(name = "Console.findAll", query = "SELECT c FROM Console c")
public class Console implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String description;

	private String image;

	private int price;

	private String type;

	private String conditionn;

	public String getCondition() {
		return conditionn;
	}

	public void setCondition(String conditionn) {
		this.conditionn = conditionn;
	}

	private String logo;

	@Temporal(TemporalType.DATE)
	@Column(name = "release_date")
	private Date releaseDate;

	private String title;

	// bi-directional many-to-one association to Customer
	@ManyToMany(mappedBy = "consoles")
	private List<Game> games = new ArrayList<>();

	// bi-directional many-to-one association to Image
	@JsonIgnore
	@OneToMany(mappedBy = "console")
	private List<Image> images;

	public Console() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public List<Image> getImages() {
		return this.images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Image addImage(Image image) {
		getImages().add(image);
		image.setConsole(this);

		return image;
	}

	public Image removeImage(Image image) {
		getImages().remove(image);
		image.setConsole(null);

		return image;
	}

	@Override
	public String toString() {
		return "Console [id=" + id + ", image=" + image + ", price=" + price + ", releaseDate=" + releaseDate
				+ ", title=" + title + "]";
	}

}