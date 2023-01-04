package co.simplon.events.dtos;

import java.time.LocalDate;

import co.simplon.events.entities.Location;
import co.simplon.events.entities.Theme;

public class EventView {

    private Long id;

    private String name;

    private LocalDate date;

    private Location location;

    private Theme theme;

    private double price;

    private String description;

    public EventView() {
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public LocalDate getDate() {
	return date;
    }

    public void setDate(LocalDate date) {
	this.date = date;
    }

    public Location getLocation() {
	return location;
    }

    public void setLocation(Location location) {
	this.location = location;
    }

    public Theme getTheme() {
	return theme;
    }

    public void setTheme(Theme theme) {
	this.theme = theme;
    }

    public double getPrice() {
	return price;
    }

    public void setPrice(double price) {
	this.price = price;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Override
    public String toString() {
	return " {id=" + id + ", name=" + name + ", date="
		+ date + ", location=" + location
		+ ", theme=" + theme + ", price=" + price
		+ ", description=" + description + "}";
    }

}
