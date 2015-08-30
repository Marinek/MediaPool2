package de.mediapool.server.entities.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mediapool.server.core.controller.MPController;
import de.mediapool.server.entities.media.movies.domain.MovieNodeDTO;
import de.mediapool.server.entities.media.movies.repository.MovieRepository;
import de.mediapool.server.entities.persons.domain.PersonNodeDTO;
import de.mediapool.server.entities.persons.repository.PersonsRepository;
import de.mediapool.server.entities.product.movies.domain.ProductMovieNodeDTO;
import de.mediapool.server.entities.product.movies.repository.ProductMovieRepository;
import de.mediapool.server.entities.users.domain.UserNodeDTO;
import de.mediapool.server.entities.users.repository.UserRepository;
import de.mediapool.server.security.domain.MPUserDetails;
import de.mediapool.server.security.domain.PreAuthorization;

@RestController
@RequestMapping("/rest/testmovieproduct")
public class TestMovieProductController implements MPController {

	private static final Logger logger = LoggerFactory.getLogger(TestMovieProductController.class);

	@Autowired
	private ProductMovieRepository productMovieRepository;

	@Autowired
	private PersonsRepository personsRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MovieRepository movieRepository;

	@PostConstruct
	public void init() {
		logger.debug("Invoking: init()");
	}

	@PreAuthorize(PreAuthorization.ROLE_USER)
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ProductMovieNodeDTO getProductMovie(@PathVariable("id") Long id, @AuthenticationPrincipal MPUserDetails test) {
		logger.debug("Invoking: getMovie(id)");

		ProductMovieNodeDTO productMovie = productMovieRepository.findOne(id);

		return productMovie;
	}

	@RequestMapping(value = "/deletePerson", method = RequestMethod.POST)
	public void deletePerson(String lastname) {

		PersonNodeDTO person = personsRepository.findByLastName(lastname);

		personsRepository.delete(person);
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public void deleteUser() {

		UserNodeDTO user = userRepository.findByUsername("Test");

		if (user != null) {
			userRepository.delete(user);
		}
	}

	@RequestMapping(value = "/deleteMovieProduct", method = RequestMethod.POST)
	public void deleteMovieProduct(String title) {

		List<ProductMovieNodeDTO> pmnl = productMovieRepository.findByTitle(title);

		if (pmnl != null && pmnl.size() > 0) {
			for (ProductMovieNodeDTO pmn : pmnl)
				productMovieRepository.delete(pmn);
		}
	}

	@RequestMapping(value = "/deleteMovie", method = RequestMethod.POST)
	public void deleteMovie(String title) {

		MovieNodeDTO movie = movieRepository.findByTitle(title);

		if (movie != null) {
			movieRepository.delete(movie);
		}
	}

	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	public void deleteAll() {

		deleteMovieProduct("Herr der Ringe Triologie");
		deleteMovie("Herr der Ringe - Die Gefährten");
		deleteMovie("Herr der Ringe - Die 2 Türme");
		deletePerson("Bloom");
		deletePerson("Tyler");
		deleteUser();
	}

	@RequestMapping
	public List<MovieNodeDTO> findMovieByTitle(String name) {
		return new ArrayList<>();
	}

	@RequestMapping(value = "/createAll", method = RequestMethod.POST)
	public void createMovie() {
		logger.debug("Invoking: createTestProductMovie(newTestProductMovie)");

		{
			UserNodeDTO newUser = new UserNodeDTO();

			newUser.setUsername("Test");
			newUser.setPassword("Test");

			userRepository.save(newUser);

			ProductMovieNodeDTO newProductMovie = new ProductMovieNodeDTO();

			newProductMovie.owendBy(newUser);

			newProductMovie.setTitle("Herr der Ringe Triologie");
			{
				MovieNodeDTO newMovieDTO = new MovieNodeDTO();

				newMovieDTO.setTitle("Herr der Ringe - Die Gefährten");

				newProductMovie.addMovie(newMovieDTO);

				PersonNodeDTO newPersonDTO = new PersonNodeDTO();

				newPersonDTO.setLastName("Bloom");
				newPersonDTO.setFirstName("Orlando");

				newMovieDTO.addPerson(newPersonDTO);
			}
			{
				MovieNodeDTO newMovieDTO = new MovieNodeDTO();

				newMovieDTO.setTitle("Herr der Ringe - Die 2 Türme");

				newProductMovie.addMovie(newMovieDTO);

				PersonNodeDTO newPersonDTO = new PersonNodeDTO();

				newPersonDTO.setLastName("Tyler");
				newPersonDTO.setFirstName("Liv");

				newMovieDTO.addPerson(newPersonDTO);
			}

			productMovieRepository.save(newProductMovie);
		}

	}

}
