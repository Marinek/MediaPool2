package de.mediapool.server.thumbnails.services;

import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.gm4java.engine.support.GMConnectionPoolConfig;
import org.gm4java.engine.support.PooledGMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.event.Event;
import reactor.function.Function;

/**
 * Uses the GraphicsMagick package to do the image resizing.
 *
 * @author Jon Brisbin
 * @author Marinek
 */
class GraphicsMagickThumbnailer implements Function<Event<Path>, Path> {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private final int maxLongSide;
	private final PooledGMService gm;

	public GraphicsMagickThumbnailer(int maxLongSide) {
		this.maxLongSide = maxLongSide;
		GMConnectionPoolConfig config = new GMConnectionPoolConfig();
		this.gm = new PooledGMService(config);
	}

	@Override
	public Path apply(Event<Path> ev) {
		try {
			Path srcPath = ev.getData();
			Path thumbnailPath = Files.createTempFile("thumbnail", ".jpg").toAbsolutePath();
			BufferedImage imgIn = ImageIO.read(srcPath.toFile());

			double scale;
			if (imgIn.getWidth() >= imgIn.getHeight()) {
				// horizontal or square image
				scale = Math.min(maxLongSide, imgIn.getWidth()) / (double) imgIn.getWidth();
			} else {
				// vertical image
				scale = Math.min(maxLongSide, imgIn.getHeight()) / (double) imgIn.getHeight();
			}

			gm.execute("convert", srcPath.toString(), "-resize", Math.round(100 * scale) + "%", thumbnailPath.toString());

			log.info("Image thumbnail now at: {}", thumbnailPath);

			return thumbnailPath;
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

}