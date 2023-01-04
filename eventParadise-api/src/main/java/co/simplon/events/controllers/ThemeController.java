package co.simplon.events.controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.events.database.Database;
import co.simplon.events.dtos.ThemeView;
import co.simplon.events.entities.Theme;

@RestController
@RequestMapping("/themes")
@CrossOrigin
public class ThemeController {

    public static ThemeView buildThemeView(Theme theme) {
	ThemeView view = new ThemeView();
	view.setId(theme.getId());
	view.setName(theme.getName());
	return view;
    }

    @GetMapping
    public Collection<ThemeView> getAll() {
	Collection<Theme> themes = Database.findAllThemes();
	Collection<ThemeView> views = new ArrayList<>();
	for (Theme theme : themes) {
	    ThemeView view = buildThemeView(theme);
	    views.add(view);
	}
	return views;
    }

}
