package co.simplon.events.controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.events.database.Database;
import co.simplon.events.dtos.EventCreateDto;
import co.simplon.events.dtos.EventView;
import co.simplon.events.entities.Event;
import co.simplon.events.entities.Location;
import co.simplon.events.entities.Theme;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/events")
@CrossOrigin
public class EventController {

	@PostMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void create(@Valid @RequestBody EventCreateDto inputs) {
		Event event = new Event();
		event.setName(inputs.getName());
		event.setDate(inputs.getDate());
		Location location = Database.getLocationById(inputs.getLocationId());
		event.setLocation(location);
		Theme theme = Database.getThemeById(inputs.getThemeId());
		event.setTheme(theme);
		event.setPrice(inputs.getPrice());
		event.setDescription(inputs.getDescription());
		Database.saveEvent(event);
	}

	public static EventView buildEventView(Event event) {
		EventView view = new EventView();
		view.setId(event.getId());
		view.setName(event.getName());
		view.setDate(event.getDate());
		view.setLocation(event.getLocation());
		view.setTheme(event.getTheme());
		view.setPrice(event.getPrice());
		view.setDescription(event.getDescription());
		return view;
	}

	@GetMapping
	public Collection<EventView> getAll() {
		Collection<Event> events = Database.findAllEvents();
		Collection<EventView> views = new ArrayList<>();
		for (Event event : events) {
			EventView view = buildEventView(event);
			views.add(view);
		}
		return views;
	}

}
