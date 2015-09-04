package de.mediapool.server.entities.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mediapool.server.core.controller.MPController;
import de.mediapool.server.entities.lists.domain.ListNodeDTO;
import de.mediapool.server.entities.lists.repository.ListRepository;
import de.mediapool.server.entities.media.movies.domain.MovieNodeDTO;
import de.mediapool.server.entities.media.movies.repository.MovieRepository;
import de.mediapool.server.entities.persons.domain.PersonNodeDTO;
import de.mediapool.server.entities.persons.repository.PersonsRepository;
import de.mediapool.server.entities.product.domain.MediaType;
import de.mediapool.server.entities.product.domain.ProductNodeDTO;
import de.mediapool.server.entities.product.repository.ProductRepository;
import de.mediapool.server.entities.users.domain.UserNodeDTO;
import de.mediapool.server.entities.users.domain.UserRoleNodeDTO;
import de.mediapool.server.entities.users.repository.UserRepository;
import de.mediapool.server.entities.users.repository.UserRoleRepository;
import de.mediapool.server.security.domain.MPUserDetails;
import de.mediapool.server.security.domain.PreAuthorization;

@RestController
@RequestMapping("/rest/testProduct")
public class TestProductController implements MPController {

	private static final Logger logger = LoggerFactory.getLogger(TestProductController.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PersonsRepository personsRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private ListRepository listRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@PostConstruct
	public void init() {
		logger.debug("Invoking: init()");
	}

	@RequestMapping
	public List<MovieNodeDTO> findMovieByTitle(String name) {
		return new ArrayList<>();
	}

	@PreAuthorize(PreAuthorization.ROLE_USER)
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ProductNodeDTO getProduct(@PathVariable("id") Long id, @AuthenticationPrincipal MPUserDetails test) {
		logger.debug("Invoking: getMovie(id)");

		ProductNodeDTO Product = productRepository.findOne(id);

		return Product;
	}

	@RequestMapping(value = "/deletePerson", method = RequestMethod.POST)
	public void deletePerson(String lastname) {

		List<PersonNodeDTO> personList = personsRepository.findByLastName(lastname);

		if (personList != null && personList.size() > 0) {
			for (PersonNodeDTO person : personList)
				personsRepository.delete(person);
		}
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public void deleteUser(String username) {

		List<UserNodeDTO> userList = userRepository.findAllByUsername(username);

		if (userList != null && userList.size() > 0) {
			for (UserNodeDTO user : userList) {
				userRepository.delete(user);
			}
		}

	}

	@RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
	public void deleteProduct(String title) {

		List<ProductNodeDTO> pmnl = productRepository.findByTitle(title);

		if (pmnl != null && pmnl.size() > 0) {
			for (ProductNodeDTO pmn : pmnl)
				productRepository.delete(pmn);
		}
	}

	@RequestMapping(value = "/deleteMovie", method = RequestMethod.POST)
	public void deleteMovie(String title) {

		List<MovieNodeDTO> movieList = movieRepository.findByTitle(title);

		if (movieList != null && movieList.size() > 0) {
			for (MovieNodeDTO movie : movieList) {
				movieRepository.delete(movie);
			}
		}
	}

	@RequestMapping(value = "/deleteAllRoles", method = RequestMethod.POST)
	public void deleteAllRoles() {

		Result<UserRoleNodeDTO> urnl = userRoleRepository.findAll();

		Iterator<UserRoleNodeDTO> it = urnl.iterator();
		while (it.hasNext()) {
			UserRoleNodeDTO role = it.next();
			userRoleRepository.delete(role);
		}
	}

	@RequestMapping(value = "/deleteAllMovies", method = RequestMethod.POST)
	public void deleteAllMovies() {

		Result<MovieNodeDTO> mnl = movieRepository.findAll();

		Iterator<MovieNodeDTO> it = mnl.iterator();
		while (it.hasNext()) {
			MovieNodeDTO movie = it.next();
			movieRepository.delete(movie);
		}
	}

	@RequestMapping(value = "/deleteList", method = RequestMethod.POST)
	public void deleteList(String title) {

		List<ListNodeDTO> listList = listRepository.findByTitle(title);

		if (listList != null && listList.size() > 0) {
			for (ListNodeDTO list : listList) {
				listRepository.delete(list);
			}
		}
	}

	@RequestMapping(value = "/deleteAllPersons", method = RequestMethod.POST)
	public void deleteAllPersons() {

		Result<PersonNodeDTO> pnl = personsRepository.findAll();

		Iterator<PersonNodeDTO> it = pnl.iterator();
		while (it.hasNext()) {
			PersonNodeDTO person = it.next();
			personsRepository.delete(person);
		}
	}

	@RequestMapping(value = "/deleteAllLists", method = RequestMethod.POST)
	public void deleteAllLists() {

		Result<ListNodeDTO> lnl = listRepository.findAll();

		Iterator<ListNodeDTO> it = lnl.iterator();
		while (it.hasNext()) {
			ListNodeDTO list = it.next();
			listRepository.delete(list);
		}

	}

	@RequestMapping(value = "/deleteAllProducts", method = RequestMethod.POST)
	public void deleteAllProducts() {

		Result<ProductNodeDTO> pmnl = productRepository.findAll();

		Iterator<ProductNodeDTO> it = pmnl.iterator();
		while (it.hasNext()) {
			ProductNodeDTO product = it.next();
			productRepository.delete(product);
		}

	}

	@RequestMapping(value = "/deleteAllUsers", method = RequestMethod.POST)
	public void deleteAllUsers() {

		Result<UserNodeDTO> unl = userRepository.findAll();

		Iterator<UserNodeDTO> it = unl.iterator();
		while (it.hasNext()) {
			UserNodeDTO user = it.next();
			userRepository.delete(user);
		}

	}

	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	public void deleteAll() {

		deleteAllProducts();
		deleteAllUsers();
		deleteAllLists();
		deleteAllPersons();
		deleteAllRoles();
		deleteAllMovies();

	}

	private Date getDateForString(String dateValue) {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd.MM.yyyy").parse(dateValue);
		} catch (ParseException ex) {
			logger.error("Wrong Dateformat: " + dateValue);
			date = new Date();
		}
		return date;
	}

	@RequestMapping(value = "/createAll", method = RequestMethod.POST)
	public void createAll() {
		logger.debug("Invoking: createAllStuff");

		UserNodeDTO newUser1 = new UserNodeDTO("Test1", "Test1");
		userRepository.save(newUser1);
		UserNodeDTO newUser2 = new UserNodeDTO("Test2", "Test2");
		userRepository.save(newUser2);
		UserNodeDTO newUser3 = new UserNodeDTO("Test3", "Test3");
		userRepository.save(newUser3);
		UserNodeDTO newUser4 = new UserNodeDTO("Test4", "Test4");
		userRepository.save(newUser4);
		UserNodeDTO newUser5 = new UserNodeDTO("Test5", "Test5");
		userRepository.save(newUser5);

		newUser1.follows(newUser2);
		newUser2.follows(newUser3);
		newUser3.follows(newUser4);
		newUser4.follows(newUser5);
		newUser5.follows(newUser1);

		userRepository.save(newUser1);
		userRepository.save(newUser2);
		userRepository.save(newUser3);
		userRepository.save(newUser4);
		userRepository.save(newUser5);

		ProductNodeDTO newProduct1 = new ProductNodeDTO(MediaType.MOVIE, "Herr der Ringe Triologie", "Herr der Ringe Triologie", 2001, "Extended", "German", 10, "cover.jpg", "All Movies together",
				"000-000", "Blu-ray");
		ProductNodeDTO newProduct2 = new ProductNodeDTO(MediaType.MOVIE, "Star Wars Triologie", "Star Wars Triologie", 2001, "Extended", "German", 10, "cover.jpg", "All Movies together", "000-000",
				"Blu-ray");
		ProductNodeDTO newProduct3 = new ProductNodeDTO(MediaType.MOVIE, "Stirb langsam Triologie", "Stirb langsam Triologie", 2001, "Extended", "German", 10, "cover.jpg", "All Movies together",
				"000-000", "Blu-ray");
		ProductNodeDTO newProduct4 = new ProductNodeDTO(MediaType.MOVIE, "Indiana Jones Triologie", "Indiana Jones Triologie", 2001, "Extended", "German", 10, "cover.jpg", "All Movies together",
				"000-000", "Blu-ray");
		ProductNodeDTO newProduct5 = new ProductNodeDTO(MediaType.MOVIE, "Zurück in die Zukunft Triologie", "Zurück in die Zukunft Triologie", 2001, "Extended", "German", 10, "cover.jpg",
				"All Movies together", "000-000", "Blu-ray");
		ProductNodeDTO newProduct6 = new ProductNodeDTO(MediaType.MOVIE, "Mad Max Triologie", "Mad Max Triologie", 2001, "Extended", "German", 10, "cover.jpg", "All Movies together", "000-000",
				"Blu-ray");
		ProductNodeDTO newProduct7 = new ProductNodeDTO(MediaType.MOVIE, "X-Men Triologie", "X-Men Triologie", 2001, "Extended", "German", 10, "cover.jpg", "All Movies together", "000-000",
				"Blu-ray");
		ProductNodeDTO newProduct8 = new ProductNodeDTO(MediaType.MOVIE, "Matrix Triologie", "Matrix Triologie", 2001, "Extended", "German", 10, "cover.jpg", "All Movies together", "000-000",
				"Blu-ray");
		ProductNodeDTO newProduct9 = new ProductNodeDTO(MediaType.MOVIE, "Iron Man Triologie", "Iron Man Triologie", 2001, "Extended", "German", 10, "cover.jpg", "All Movies together", "000-000",
				"Blu-ray");
		ProductNodeDTO newProduct0 = new ProductNodeDTO(MediaType.MOVIE, "Der kleine Hobbit Triologie", "Der kleine Hobbit Triologie", 2001, "Extended", "German", 10, "cover.jpg",
				"All Movies together", "000-000", "Blu-ray");

		MovieNodeDTO newMovie1 = new MovieNodeDTO("Herr der Ringe - Die Gefährten", "Herr der Ringe - Die Gefährten", 2001, "Fantasy", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron",
				"FeatureFilm", 180, 12);
		MovieNodeDTO newMovie2 = new MovieNodeDTO("Herr der Ringe - Die 2 Türme", "Herr der Ringe - Die 2 Türme", 2001, "Fantasy", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron",
				"FeatureFilm", 180, 12);
		MovieNodeDTO newMovie3 = new MovieNodeDTO("Krieg der Sterne", "Krieg der Sterne", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		MovieNodeDTO newMovie4 = new MovieNodeDTO("Das Imperium schlägt zurück", "Das Imperium schlägt zurück", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron",
				"FeatureFilm", 180, 12);
		MovieNodeDTO newMovie5 = new MovieNodeDTO("Stirb langsam", "Stirb langsam", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		MovieNodeDTO newMovie6 = new MovieNodeDTO("Stirb langsam II", "Stirb langsam II", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		MovieNodeDTO newMovie7 = new MovieNodeDTO("Indiana Jones", "Indiana Jones", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		MovieNodeDTO newMovie8 = new MovieNodeDTO("Indiana Jones II", "Indiana Jones II", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		MovieNodeDTO newMovie9 = new MovieNodeDTO("Zurück in die Zukunft", "Zurück in die Zukunft", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm",
				180, 12);
		MovieNodeDTO newMovie10 = new MovieNodeDTO("Zurück in die Zukunft II", "Zurück in die Zukunft II", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron",
				"FeatureFilm", 180, 12);
		MovieNodeDTO newMovie11 = new MovieNodeDTO("Mad Max", "Mad Max", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		MovieNodeDTO newMovie12 = new MovieNodeDTO("Mad Max II", "Mad Max II", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);

		MovieNodeDTO newMovie13 = new MovieNodeDTO("X-Men", "X-Men", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		MovieNodeDTO newMovie14 = new MovieNodeDTO("X-Men II", "X-Men II", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		MovieNodeDTO newMovie15 = new MovieNodeDTO("Matrix", "Matrix", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		MovieNodeDTO newMovie16 = new MovieNodeDTO("Matrix II", "Matrix II", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		MovieNodeDTO newMovie17 = new MovieNodeDTO("Iron Man", "Iron Man", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		MovieNodeDTO newMovie18 = new MovieNodeDTO("Iron Man II", "Iron Man II", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		MovieNodeDTO newMovie19 = new MovieNodeDTO("Der kleine Hobbit", "Der kleine Hobbit", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180,
				12);
		MovieNodeDTO newMovie20 = new MovieNodeDTO("Der kleine Hobbit II", "Der kleine Hobbit II", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm",
				180, 12);

		PersonNodeDTO newPerson1 = new PersonNodeDTO("Orlando", "Bloom", getDateForString("20.04.1982"), "USA", "m", "Actor", false, "image.jpg");
		PersonNodeDTO newPerson2 = new PersonNodeDTO("Liv", "Tyler", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");
		PersonNodeDTO newPerson3 = new PersonNodeDTO("Harrison", "Ford", getDateForString("20.04.1982"), "USA", "m", "Actor", false, "image.jpg");
		PersonNodeDTO newPerson4 = new PersonNodeDTO("Carrie", "Fisher", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");
		PersonNodeDTO newPerson5 = new PersonNodeDTO("Bruce", "Willis", getDateForString("20.04.1982"), "USA", "m", "Actor", false, "image.jpg");
		PersonNodeDTO newPerson6 = new PersonNodeDTO("Alan", "Rickman", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");
		// PersonNodeDTO newPerson7 = new PersonNodeDTO("Harrison", "Ford",
		// getDateForString("20.04.1982"), "USA", "m", "Actor", false,
		// "image.jpg");
		PersonNodeDTO newPerson8 = new PersonNodeDTO("Dan", "Aykroyd", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");

		PersonNodeDTO newPerson9 = new PersonNodeDTO("Michael", "J. Fox", getDateForString("20.04.1982"), "USA", "m", "Actor", false, "image.jpg");
		PersonNodeDTO newPerson10 = new PersonNodeDTO("Christopher", "Lloyd", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");

		PersonNodeDTO newPerson11 = new PersonNodeDTO("Mel", "Gibson", getDateForString("20.04.1982"), "USA", "m", "Actor", false, "image.jpg");
		PersonNodeDTO newPerson12 = new PersonNodeDTO("Tina", "Turner", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");

		PersonNodeDTO newPerson13 = new PersonNodeDTO("Hugh", "Jackmann", getDateForString("20.04.1982"), "USA", "m", "Actor", false, "image.jpg");
		PersonNodeDTO newPerson14 = new PersonNodeDTO("Patrick", "Steward", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");

		PersonNodeDTO newPerson15 = new PersonNodeDTO("Keanu", "Reeves", getDateForString("20.04.1982"), "USA", "m", "Actor", false, "image.jpg");
		PersonNodeDTO newPerson16 = new PersonNodeDTO("Lawrence", "Fishbourne", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");

		PersonNodeDTO newPerson19 = new PersonNodeDTO("Orlando", "Bloom", getDateForString("20.04.1982"), "USA", "m", "Actor", false, "image.jpg");
		PersonNodeDTO newPerson20 = new PersonNodeDTO("Liv", "Tyler", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");

		PersonNodeDTO newPerson17 = new PersonNodeDTO("Martin", "Freeman", getDateForString("20.04.1982"), "USA", "m", "Actor", false, "image.jpg");
		PersonNodeDTO newPerson18 = new PersonNodeDTO("Ian", "McKellen", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");

		personsRepository.save(newPerson1);
		personsRepository.save(newPerson2);
		personsRepository.save(newPerson3);
		personsRepository.save(newPerson4);
		personsRepository.save(newPerson5);
		personsRepository.save(newPerson6);
		// personsRepository.save(newPerson7);
		personsRepository.save(newPerson8);
		personsRepository.save(newPerson9);
		personsRepository.save(newPerson10);
		personsRepository.save(newPerson11);
		personsRepository.save(newPerson12);
		personsRepository.save(newPerson13);
		personsRepository.save(newPerson14);
		personsRepository.save(newPerson15);
		personsRepository.save(newPerson16);
		personsRepository.save(newPerson17);
		personsRepository.save(newPerson18);
		personsRepository.save(newPerson19);
		personsRepository.save(newPerson20);

		newMovie1.addPerson(newPerson1);
		newMovie2.addPerson(newPerson2);
		newMovie3.addPerson(newPerson3);
		newMovie4.addPerson(newPerson4);
		newMovie5.addPerson(newPerson5);
		newMovie6.addPerson(newPerson6);
		newMovie7.addPerson(newPerson3);
		newMovie8.addPerson(newPerson8);
		newMovie9.addPerson(newPerson9);
		newMovie10.addPerson(newPerson10);
		newMovie11.addPerson(newPerson11);
		newMovie12.addPerson(newPerson12);
		newMovie13.addPerson(newPerson13);
		newMovie14.addPerson(newPerson14);
		newMovie15.addPerson(newPerson15);
		newMovie16.addPerson(newPerson16);
		newMovie17.addPerson(newPerson17);
		newMovie18.addPerson(newPerson18);
		newMovie19.addPerson(newPerson19);
		newMovie20.addPerson(newPerson20);

		newProduct1.addMovie(newMovie1);
		newProduct1.addMovie(newMovie2);
		newProduct2.addMovie(newMovie3);
		newProduct2.addMovie(newMovie4);
		newProduct3.addMovie(newMovie5);
		newProduct3.addMovie(newMovie6);
		newProduct4.addMovie(newMovie7);
		newProduct4.addMovie(newMovie8);
		newProduct5.addMovie(newMovie9);
		newProduct5.addMovie(newMovie10);
		newProduct6.addMovie(newMovie11);
		newProduct6.addMovie(newMovie12);
		newProduct7.addMovie(newMovie13);
		newProduct7.addMovie(newMovie14);
		newProduct8.addMovie(newMovie15);
		newProduct8.addMovie(newMovie16);
		newProduct9.addMovie(newMovie17);
		newProduct9.addMovie(newMovie18);
		newProduct0.addMovie(newMovie19);
		newProduct0.addMovie(newMovie20);

		productRepository.save(newProduct1);
		productRepository.save(newProduct2);
		productRepository.save(newProduct3);
		productRepository.save(newProduct4);
		productRepository.save(newProduct5);
		productRepository.save(newProduct6);
		productRepository.save(newProduct7);
		productRepository.save(newProduct8);
		productRepository.save(newProduct9);
		productRepository.save(newProduct0);

		newUser1.owens(newProduct1);
		newUser2.owens(newProduct2);
		newUser4.owens(newProduct3);
		newUser4.owens(newProduct4);
		newUser4.owens(newProduct5);
		newUser4.owens(newProduct6);
		newUser4.owens(newProduct7);
		newUser4.owens(newProduct8);
		newUser4.owens(newProduct9);
		newUser4.owens(newProduct0);

		newUser1.createNewList("Wishlist");
		newUser3.createNewList("Wishlist");

		userRepository.save(newUser1);
		userRepository.save(newUser2);
		userRepository.save(newUser3);
		userRepository.save(newUser4);

		addProductWithTitleToList("Star Wars Triologie", "Wishlist", newUser1);
		addProductWithTitleToList("Herr der Ringe Triologie", "Wishlist", newUser3);

	}

	private void addProductWithTitleToList(String productTitle, String listTitle, UserNodeDTO user) {
		List<ProductNodeDTO> pmnl = productRepository.findByTitle(productTitle);
		ListNodeDTO list = user.getListByTitle(listTitle);

		if (pmnl != null && pmnl.size() > 0) {
			for (ProductNodeDTO pmn : pmnl) {
				list.addToList(pmn);
			}
			listRepository.save(list);
		}
	}

}
