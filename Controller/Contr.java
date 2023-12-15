package ca.sheridancollege.uppkaram.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.uppkaram.Beans.Movie;
import ca.sheridancollege.uppkaram.DatabaseAccess.DatabaseAcc;


@Controller
public class Contr 
{
	@Autowired
	private DatabaseAcc da;
	
	@GetMapping("/")
	public String index(Model model)
	{
		model.addAttribute("movie", new Movie());
		model.addAttribute("movieList", da.getMovieList());
		
		return "index";
	}
	
	@GetMapping("/display")
	public String displayByYearForm()
	{
		return "display";
	}
	
	@PostMapping("/insertMovie")
	public String insertMovie(Model model, @ModelAttribute Movie movie)
	{
		da.insertMovie(movie);
		
		model.addAttribute("movie", new Movie());
		model.addAttribute("movieList", da.getMovieList());
		
		return "index";
	}
	
	@PostMapping("/displayByYear")
	public String displayByYearForm(@RequestParam int releaseYear, Model model)
	{
		da.getMovieListByYear(releaseYear);
		model.addAttribute("movie", new Movie());
		model.addAttribute("movieList", da.getMovieListByYear(releaseYear));
		
		return "index";
	}
}

