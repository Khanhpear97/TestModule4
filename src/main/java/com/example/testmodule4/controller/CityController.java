package com.example.testmodule4.controller;

import com.example.testmodule4.model.City;
import com.example.testmodule4.model.Country;
import com.example.testmodule4.service.ICityService;
import com.example.testmodule4.service.ICountryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private ICityService iCityService;

    @Autowired
    private ICountryService iCountryService;

    @GetMapping()
    public String showList(Model model) {
        List<City> cities = (List<City>) iCityService.findAll();
        model.addAttribute("cities", cities);
        return "city/list";
    }

    @GetMapping("/{id}/views")
    public String showDetails(@PathVariable("id") String id,
                              Model model) {
        Optional<City> city = iCityService.findById(Long.valueOf(id));
        if (city.isPresent()) {
            model.addAttribute("city", city.get());
            return "city/view";
        }
        return "redirect:/cities";
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        model.addAttribute("countries", iCountryService.findAll());
        model.addAttribute("city", new City());
        return "city/form";
    }

    @PostMapping("/create")
    public String save(@Valid @ModelAttribute("city") City city,
                      BindingResult bindingResult,
                      Model model) {
        if (!bindingResult.hasErrors()) {
            iCityService.save(city);
            return "redirect:/cities";
        }
        Iterable<Country> countries = iCountryService.findAll();
        model.addAttribute("countries", countries);
        model.addAttribute("city", city);
        return "city/form";
    }

    @GetMapping("/{id}/update")
    public String showFormUpdate(@PathVariable("id") String id,
                                 Model model) {
        Optional<City> city = iCityService.findById(Long.valueOf(id));
        if (city.isPresent()) {
            Iterable<Country> countries = iCountryService.findAll();
            model.addAttribute("countries", countries);
            model.addAttribute("city", city.get());
            return "city/form";
        }
        return "redirect:/cities";
    }

    @PostMapping("/{id}/update")
    public String update(@Valid @ModelAttribute City city,
                         BindingResult bindingResult,
                         Model model) {
        if (!bindingResult.hasErrors()) {
            iCityService.save(city);
            return "redirect:/cities";
        }
        Iterable<Country> countries = iCountryService.findAll();
        model.addAttribute("countries", countries);
        model.addAttribute("city", city);
        return "city/form";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") String id) {
        iCityService.remove(Long.valueOf(id));
        return "redirect:/cities";
    }

    @GetMapping("/search")
    public String searchByName(@RequestParam("keyword") String keyword,
                               Model model) {
        Iterable<City> cities = iCityService.findByName(keyword);
        model.addAttribute("cities", cities);
        Iterable<Country> countries = iCountryService.findAll();
        model.addAttribute("countries", countries);
        return "city/list";
    }
}
