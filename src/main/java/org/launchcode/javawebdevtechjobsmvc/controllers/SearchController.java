package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    private static String searchTerm;
    private static String searchType;

    static ArrayList<Job> resultJobs = new ArrayList<>();
 // static HashMap<String, String> columnChoices = new HashMap<>();
    static HashMap<String, Object> tableChoices = new HashMap<>();

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        model.addAttribute("tableChoices", tableChoices);

//        model.addAttribute("searchTerm", searchTerm);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @RequestMapping(value = "results")
    public String displaySearchResults (Model model,@RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("columns",columnChoices);
        if (searchType.toLowerCase().equals("all")){
            resultJobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
            resultJobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "resultJobs with " + columnChoices.get(searchType) + ": " + searchTerm);
        }
        model.addAttribute("resultJobs", resultJobs);
        //return "redirect:";
        return "search";
    }

}
