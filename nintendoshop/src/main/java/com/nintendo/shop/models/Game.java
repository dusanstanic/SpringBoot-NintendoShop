package com.nintendo.shop.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the game database table.
 * 
 */
@Entity
@NamedQuery(name = "Game.findAll", query = "SELECT g FROM Game g")
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String description;

	private String image;

	@Column(name = "pg_rating")
	private String pgRating;

	private int price;

	@Temporal(TemporalType.DATE)
	@Column(name = "release_date")
	private Date releaseDate;

	private String title;

	@Column(name = "status")
	private String status;

	// bi-directional many-to-one association to Genre
	@ManyToOne
	private Genre genre;

	// bi-directional many-to-many association to Role
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "game_console", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "console_id"))
	private List<Console> consoles = new ArrayList<>();

	// bi-directional many-to-one association to Image
	@JsonIgnore
	@OneToMany(mappedBy = "game")
	private List<Image> images;

	public Game() {
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

	public String getPgRating() {
		return this.pgRating;
	}

	public void setPgRating(String pgRating) {
		this.pgRating = pgRating;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List<Console> getConsoles() {
		return consoles;
	}

	public void setConsoles(List<Console> consoles) {
		this.consoles = consoles;
	}

	public List<Image> getImages() {
		return this.images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Image addImage(Image image) {
		getImages().add(image);
		image.setGame(this);

		return image;
	}

	public Image removeImage(Image image) {
		getImages().remove(image);
		image.setGame(null);

		return image;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", description=" + description + ", image=" + image + ", pgRating=" + pgRating
				+ ", price=" + price + ", releaseDate=" + releaseDate + ", title=" + title + ", status=" + status
				+ ", genre=" + genre + ", consoles=" + consoles + ", images=" + images + "]";
	}

}