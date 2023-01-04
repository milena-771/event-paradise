package co.simplon.events.controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.events.database.Database;
import co.simplon.events.dtos.LocationView;
import co.simplon.events.entities.Location;

@RestController
@RequestMapping("/locations")
@CrossOrigin
public class LocationController {

    public static LocationView buildLocationView(
	    Location location) {
	LocationView view = new LocationView();
	view.setId(location.getId());
	view.setName(location.getName());
	return view;
    }

    @GetMapping
    public Collection<LocationView> getAll() {
	Collection<Location> locations = Database
		.findAllLocations();
	Collection<LocationView> views = new ArrayList<>();
	for (Location location : locations) {
	    LocationView view = buildLocationView(location);
	    views.add(view);
	}
	return views;
    }

}
