package de.mediapool.server.mvc.module.index;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mediapool.server.core.controller.MPController;

@RestController
public class StaticController implements MPController {

	@RequestMapping(value = "css/**", method = RequestMethod.GET, produces = "text/css")
	public ClassPathResource getCSS(HttpServletRequest  request) {
		return new ClassPathResource("static" + request.getServletPath());
	}

	@RequestMapping(value = "js/**", method = RequestMethod.GET)
	public ClassPathResource getJS(HttpServletRequest  request) {
		return new ClassPathResource("static" + request.getServletPath());
	}

	@RequestMapping(value = "fonts/**", method = RequestMethod.GET)
	public ClassPathResource getFonts(HttpServletRequest  request) {
		return new ClassPathResource("static" + request.getServletPath());
	}
}
