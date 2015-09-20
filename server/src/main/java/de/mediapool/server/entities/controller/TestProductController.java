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
import de.mediapool.server.entities.lists.domain.Listing;
import de.mediapool.server.entities.lists.repository.ListRepository;
import de.mediapool.server.entities.media.movies.domain.Movie;
import de.mediapool.server.entities.media.movies.repository.MovieRepository;
import de.mediapool.server.entities.persons.domain.Person;
import de.mediapool.server.entities.persons.repository.PersonsRepository;
import de.mediapool.server.entities.product.domain.MediaType;
import de.mediapool.server.entities.product.domain.Product;
import de.mediapool.server.entities.product.repository.ProductRepository;
import de.mediapool.server.entities.users.domain.User;
import de.mediapool.server.entities.users.domain.UserRole;
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
	public List<Movie> findMovieByTitle(String name) {
		return new ArrayList<>();
	}

	@PreAuthorize(PreAuthorization.ROLE_USER)
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable("id") Long id, @AuthenticationPrincipal MPUserDetails test) {
		logger.debug("Invoking: getMovie(id)");

		Product Product = productRepository.findOne(id);

		return Product;
	}

	@RequestMapping(value = "/deletePerson", method = RequestMethod.POST)
	public void deletePerson(String lastname) {

		List<Person> personList = personsRepository.findByLastName(lastname);

		if (personList != null && personList.size() > 0) {
			for (Person person : personList)
				personsRepository.delete(person);
		}
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public void deleteUser(String username) {

		List<User> userList = userRepository.findAllByUsername(username);

		if (userList != null && userList.size() > 0) {
			for (User user : userList) {
				userRepository.delete(user);
			}
		}

	}

	@RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
	public void deleteProduct(String title) {

		List<Product> pmnl = productRepository.findByTitle(title);

		if (pmnl != null && pmnl.size() > 0) {
			for (Product pmn : pmnl)
				productRepository.delete(pmn);
		}
	}

	@RequestMapping(value = "/deleteMovie", method = RequestMethod.POST)
	public void deleteMovie(String title) {

		List<Movie> movieList = movieRepository.findByTitle(title);

		if (movieList != null && movieList.size() > 0) {
			for (Movie movie : movieList) {
				movieRepository.delete(movie);
			}
		}
	}

	@RequestMapping(value = "/deleteAllRoles", method = RequestMethod.POST)
	public void deleteAllRoles() {

		Result<UserRole> urnl = userRoleRepository.findAll();

		Iterator<UserRole> it = urnl.iterator();
		while (it.hasNext()) {
			UserRole role = it.next();
			userRoleRepository.delete(role);
		}
	}

	@RequestMapping(value = "/deleteAllMovies", method = RequestMethod.POST)
	public void deleteAllMovies() {

		Result<Movie> mnl = movieRepository.findAll();

		Iterator<Movie> it = mnl.iterator();
		while (it.hasNext()) {
			Movie movie = it.next();
			movieRepository.delete(movie);
		}
	}

	@RequestMapping(value = "/deleteList", method = RequestMethod.POST)
	public void deleteList(String title) {

		List<Listing> listList = listRepository.findByTitle(title);

		if (listList != null && listList.size() > 0) {
			for (Listing list : listList) {
				listRepository.delete(list);
			}
		}
	}

	@RequestMapping(value = "/deleteAllPersons", method = RequestMethod.POST)
	public void deleteAllPersons() {

		Result<Person> pnl = personsRepository.findAll();

		Iterator<Person> it = pnl.iterator();
		while (it.hasNext()) {
			Person person = it.next();
			personsRepository.delete(person);
		}
	}

	@RequestMapping(value = "/deleteAllLists", method = RequestMethod.POST)
	public void deleteAllLists() {

		Result<Listing> lnl = listRepository.findAll();

		Iterator<Listing> it = lnl.iterator();
		while (it.hasNext()) {
			Listing list = it.next();
			listRepository.delete(list);
		}

	}

	@RequestMapping(value = "/deleteAllProducts", method = RequestMethod.POST)
	public void deleteAllProducts() {

		Result<Product> pmnl = productRepository.findAll();

		Iterator<Product> it = pmnl.iterator();
		while (it.hasNext()) {
			Product product = it.next();
			productRepository.delete(product);
		}

	}

	@RequestMapping(value = "/deleteAllUsers", method = RequestMethod.POST)
	public void deleteAllUsers() {

		Result<User> unl = userRepository.findAll();

		Iterator<User> it = unl.iterator();
		while (it.hasNext()) {
			User user = it.next();
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

		User newUser1 = new User("mp@mp.de", "mp");
		userRepository.save(newUser1);
		User newUser2 = new User("Test2", "Test2");
		userRepository.save(newUser2);
		User newUser3 = new User("Test3", "Test3");
		userRepository.save(newUser3);
		User newUser4 = new User("Test4", "Test4");
		userRepository.save(newUser4);
		User newUser5 = new User("Test5", "Test5");
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

		Product newProduct1 = new Product(MediaType.MOVIE, "Herr der Ringe Triologie", "Herr der Ringe Triologie", 2001, "Extended", "German", 10, "cover.jpg", "All Movies together",
				"000-000", "Blu-ray");
		Product newProduct2 = new Product(MediaType.MOVIE, "Star Wars Triologie", "Star Wars Triologie", 2001, "Extended", "German", 10, "cover.jpg", "All Movies together", "000-000",
				"Blu-ray");
		Product newProduct3 = new Product(MediaType.MOVIE, "Stirb langsam Triologie", "Stirb langsam Triologie", 2001, "Extended", "German", 10, "cover.jpg", "All Movies together",
				"000-000", "Blu-ray");
		Product newProduct4 = new Product(MediaType.MOVIE, "Indiana Jones Triologie", "Indiana Jones Triologie", 2001, "Extended", "German", 10, "cover.jpg", "All Movies together",
				"000-000", "Blu-ray");
		Product newProduct5 = new Product(MediaType.MOVIE, "Zurück in die Zukunft Triologie", "Zurück in die Zukunft Triologie", 2001, "Extended", "German", 10, "cover.jpg",
				"All Movies together", "000-000", "Blu-ray");
		Product newProduct6 = new Product(MediaType.MOVIE, "Mad Max Triologie", "Mad Max Triologie", 2001, "Extended", "German", 10, "cover.jpg", "All Movies together", "000-000",
				"Blu-ray");
		Product newProduct7 = new Product(MediaType.MOVIE, "X-Men Triologie", "X-Men Triologie", 2001, "Extended", "German", 10, "cover.jpg", "All Movies together", "000-000",
				"Blu-ray");
		Product newProduct8 = new Product(MediaType.MOVIE, "Matrix Triologie", "Matrix Triologie", 2001, "Extended", "German", 10, "cover.jpg", "All Movies together", "000-000",
				"Blu-ray");
		Product newProduct9 = new Product(MediaType.MOVIE, "Iron Man Triologie", "Iron Man Triologie", 2001, "Extended", "German", 10, "cover.jpg", "All Movies together", "000-000",
				"Blu-ray");
		Product newProduct0 = new Product(MediaType.MOVIE, "Der kleine Hobbit Triologie", "Der kleine Hobbit Triologie", 2001, "Extended", "German", 10, "cover.jpg",
				"All Movies together", "000-000", "Blu-ray");

		Movie newMovie1 = new Movie("Herr der Ringe - Die Gefährten", "Herr der Ringe - Die Gefährten", 2001, "Fantasy", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron",
				"FeatureFilm", 180, 12);
		Movie newMovie2 = new Movie("Herr der Ringe - Die 2 Türme", "Herr der Ringe - Die 2 Türme", 2001, "Fantasy", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron",
				"FeatureFilm", 180, 12);
		Movie newMovie3 = new Movie("Krieg der Sterne", "Krieg der Sterne", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		Movie newMovie4 = new Movie("Das Imperium schlägt zurück", "Das Imperium schlägt zurück", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron",
				"FeatureFilm", 180, 12);
		Movie newMovie5 = new Movie("Stirb langsam", "Stirb langsam", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		Movie newMovie6 = new Movie("Stirb langsam II", "Stirb langsam II", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		Movie newMovie7 = new Movie("Indiana Jones", "Indiana Jones", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		Movie newMovie8 = new Movie("Indiana Jones II", "Indiana Jones II", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		Movie newMovie9 = new Movie("Zurück in die Zukunft", "Zurück in die Zukunft", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm",
				180, 12);
		Movie newMovie10 = new Movie("Zurück in die Zukunft II", "Zurück in die Zukunft II", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron",
				"FeatureFilm", 180, 12);
		Movie newMovie11 = new Movie("Mad Max", "Mad Max", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		Movie newMovie12 = new Movie("Mad Max II", "Mad Max II", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);

		Movie newMovie13 = new Movie("X-Men", "X-Men", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		Movie newMovie14 = new Movie("X-Men II", "X-Men II", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		Movie newMovie15 = new Movie("Matrix", "Matrix", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		Movie newMovie16 = new Movie("Matrix II", "Matrix II", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		Movie newMovie17 = new Movie("Iron Man", "Iron Man", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		Movie newMovie18 = new Movie("Iron Man II", "Iron Man II", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180, 12);
		Movie newMovie19 = new Movie("Der kleine Hobbit", "Der kleine Hobbit", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm", 180,
				12);
		Movie newMovie20 = new Movie("Der kleine Hobbit II", "Der kleine Hobbit II", 2001, "ScienceFiction", "english", "Oscar", "cover.jpg", "Trying to defeat Sauron", "FeatureFilm",
				180, 12);

		Person newPerson1 = new Person("Orlando", "Bloom", getDateForString("20.04.1982"), "USA", "m", "Actor", false, "image.jpg");
		Person newPerson2 = new Person("Liv", "Tyler", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");
		Person newPerson3 = new Person("Harrison", "Ford", getDateForString("20.04.1982"), "USA", "m", "Actor", false, "image.jpg");
		Person newPerson4 = new Person("Carrie", "Fisher", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");
		Person newPerson5 = new Person("Bruce", "Willis", getDateForString("20.04.1982"), "USA", "m", "Actor", false, "image.jpg");
		Person newPerson6 = new Person("Alan", "Rickman", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");
		// PersonNodeDTO newPerson7 = new PersonNodeDTO("Harrison", "Ford",
		// getDateForString("20.04.1982"), "USA", "m", "Actor", false,
		// "image.jpg");
		Person newPerson8 = new Person("Dan", "Aykroyd", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");

		Person newPerson9 = new Person("Michael", "J. Fox", getDateForString("20.04.1982"), "USA", "m", "Actor", false, "image.jpg");
		Person newPerson10 = new Person("Christopher", "Lloyd", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");

		Person newPerson11 = new Person("Mel", "Gibson", getDateForString("20.04.1982"), "USA", "m", "Actor", false, "image.jpg");
		Person newPerson12 = new Person("Tina", "Turner", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");

		Person newPerson13 = new Person("Hugh", "Jackmann", getDateForString("20.04.1982"), "USA", "m", "Actor", false, "image.jpg");
		Person newPerson14 = new Person("Patrick", "Steward", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");

		Person newPerson15 = new Person("Keanu", "Reeves", getDateForString("20.04.1982"), "USA", "m", "Actor", false, "image.jpg");
		Person newPerson16 = new Person("Lawrence", "Fishbourne", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");

		Person newPerson19 = new Person("Orlando", "Bloom", getDateForString("20.04.1982"), "USA", "m", "Actor", false, "image.jpg");
		Person newPerson20 = new Person("Liv", "Tyler", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");

		Person newPerson17 = new Person("Martin", "Freeman", getDateForString("20.04.1982"), "USA", "m", "Actor", false, "image.jpg");
		Person newPerson18 = new Person("Ian", "McKellen", getDateForString("20.04.1982"), "USA", "f", "Actor", false, "image.jpg");

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

	private void addProductWithTitleToList(String productTitle, String listTitle, User user) {
		List<Product> pmnl = productRepository.findByTitle(productTitle);
		Listing list = user.getListByTitle(listTitle);

		if (pmnl != null && pmnl.size() > 0) {
			for (Product pmn : pmnl) {
				list.addToList(pmn);
			}
			listRepository.save(list);
		}
	}

}
