package co.simplon.events.database;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import co.simplon.events.entities.Event;
import co.simplon.events.entities.Location;
import co.simplon.events.entities.Theme;

public class Database {

    private static final Map<Long, Location> LOCATIONS = new HashMap<>();

    private static final Map<Long, Theme> THEMES = new HashMap<>();

    private static Long eventId = 0L;

    private static final Map<Long, Event> EVENTS = new HashMap<>();

    // Initialize three Locations when application is loaded
    static {
	Location paris = new Location();
	paris.setId(1L);
	paris.setName("Paris");
	LOCATIONS.put(paris.getId(), paris);
	Location rennes = new Location();
	rennes.setId(2L);
	rennes.setName("Rennes");
	LOCATIONS.put(rennes.getId(), rennes);
	Location marseille = new Location();
	marseille.setId(3L);
	marseille.setName("Marseille");
	LOCATIONS.put(marseille.getId(), marseille);
    }

    static {
	Theme ecologie = new Theme();
	ecologie.setId(1L);
	ecologie.setName("Ecologie");
	THEMES.put(ecologie.getId(), ecologie);
	Theme rh = new Theme();
	rh.setId(2L);
	rh.setName("Ressources Humaines");
	THEMES.put(rh.getId(), rh);
	Theme tech = new Theme();
	tech.setId(3L);
	tech.setName("Tech");
	THEMES.put(tech.getId(), tech);
    }

    public static Collection<Location> findAllLocations() {
	return LOCATIONS.values();
    }

    public static Collection<Theme> findAllThemes() {
	return THEMES.values();
    }

    public static void saveEvent(Event event) {
	eventId++;
	event.setId(eventId);
	EVENTS.put(event.getId(), event);
    }

    public static Collection<Event> findAllEvents() {
	return EVENTS.values();
    }

    public static Location getLocationById(
	    Long idLocation) {
	Location location = LOCATIONS.get(idLocation);
	return location;
    }

    public static Theme getThemeById(Long idTheme) {
	Theme theme = THEMES.get(idTheme);
	return theme;
    }

}
