package Modules;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;

import filters.StatFilter;

public class FlashcardGen {


	static Integer WIDTH = 800;
	static Integer HEIGHT = 600;
	static Integer BRIGHT = 180;
	static Integer tileROWS = 5;
	static Integer tileCOLS = 3;
	static Integer FONT_SIZE = 72;

	public FlashcardGen() {
		

	}

	public void create_flashcard(String word, String img_path, String output_path) throws Exception {

		//try to load either .png  or jpg
		BufferedImage img = loadImage(img_path);
		img = resize(img, this.WIDTH,this.HEIGHT);

		// === analyze sections STATS
		StatFilter filter = new StatFilter();
		filter.setImage(img);
		filter.createTiles(this.tileROWS,this.tileCOLS);
		Boolean descending = true;
		int[] sorted_tiles = filter.sortTiles("entropy", descending);

		//choose text color
		Integer best_tile = sorted_tiles[0];
		Double mean = filter.getTile(best_tile).getStats().get("mean");
		Color text_color = (mean >this.BRIGHT) ? Color.BLACK : Color.WHITE;

		//get best coordinates
		int r = filter.getTileCoordinates(best_tile)[0]; // best row
		int c = filter.getTileCoordinates(best_tile)[1]; //best col

		//getpixel coordinates (true: absolute tile coordinates)
		int x = filter.tiles[r][c].get_center_x(true);
		int y = filter.tiles[r][c].get_center_y(true);

		//add text
		filter.setImage(img);
		filter.tiles[0][0].add_text(word, this.FONT_SIZE, text_color, x,y);
		filter.saveImage(output_path, "jpg");
		
	}
	

	public static BufferedImage loadImage(String filepath) {

		String[] extensions = { "jpg", "png", "jpeg" };

		for (String extension : extensions) {

			File file = new File(filepath + "." + extension);

			if (file.exists()) {
				try {
					return ImageIO.read(file);
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
		}
		System.out.println(filepath + " does not correspond to any image" );
		return null; // If no valid image is found
	}


    public BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    }
