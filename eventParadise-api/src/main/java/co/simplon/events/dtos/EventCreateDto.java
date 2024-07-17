package co.simplon.events.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EventCreateDto {

	@NotBlank
	private String name;

	@NotNull
	@Future
	private LocalDate date;

	@NotNull
	private Long locationId;

	@NotNull
	private Long themeId;

	@NotNull
	@DecimalMin(value = "0.01", inclusive = true)
	private double price;

	@NotBlank
	private String description;

	public EventCreateDto() {
		// TODO Auto-generated constructor stub
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

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Long getThemeId() {
		return themeId;
	}

	public void setThemeId(Long themeId) {
		this.themeId = themeId;
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
		return " {name=" + name + ", date=" + date + ", locationId="
				+ locationId + ", themeId=" + themeId + ", price=" + price
				+ ", description=" + description + "}";
	}

}
