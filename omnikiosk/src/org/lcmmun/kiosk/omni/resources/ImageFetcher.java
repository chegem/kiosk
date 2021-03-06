package org.lcmmun.kiosk.omni.resources;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import org.lcmmun.kiosk.resources.ImageType;

/**
 * A cacher/fetcher for images.
 * 
 * @author William Chargin
 * 
 */
public final class ImageFetcher {

	private static final Map<ImageType, Image> cache = new HashMap<ImageType, Image>();

	static {
		// At the beginning, cache all the images.
		for (ImageType type : ImageType.values()) {
			cache(type);
		}
	}

	/**
	 * Caches the icon for the given image type.
	 * 
	 * @param type
	 *            the image type to cache
	 */
	private static void cache(ImageType type) {
		if (cache.get(type) == null) {
			try {
				cache.put(
						type,
						new ImageIcon(
								ImageFetcher.class
										.getResource("/org/lcmmun/kiosk/omni/resources/"
												+ type.filename)).getImage());
			} catch (Exception e) {
				// It's one of the ones we didn't need.
			}
		}
	}

	/**
	 * Fetches the given image, adding it to the cache if necessary.
	 * 
	 * @param type
	 *            the type of image to fetch
	 * @return the image
	 */
	public static Image fetchImage(ImageType type) {
		if (cache.get(type) == null) {
			cache(type);
		}
		return cache.get(type);
	}

	/**
	 * Fetches the given image icon, adding it to the cache if necessary.
	 * 
	 * @param type
	 *            the type of image to fetch
	 * @return the image icon
	 */
	public static ImageIcon fetchImageIcon(ImageType type) {
		if (cache.get(type) == null) {
			cache(type);
		}
		return new ImageIcon(cache.get(type));
	}

	/**
	 * No instantiation.
	 */
	private ImageFetcher() {
	}

}
