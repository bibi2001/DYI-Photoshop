/**
 * Represents color images. Image data is represented as a matrix: - the number
 * of lines corresponds to the image height (data.length) - the length of the
 * lines corresponds to the image width (data[*].length) - pixel color is
 * encoded as integers (ARGB)
 */
class ColorImage {

	private int[][] data; // @colorimage

	ColorImage(String file) {
		this.data = ImageUtil.readColorImage(file);
	}

	ColorImage(int width, int height) {
		data = new int[height][width];
	}

	int getWidth() {
		return data[0].length;
	}

	int getHeight() {
		return data.length;
	}

	void setColor(int x, int y, Color c) {
		data[y][x] = ImageUtil.encodeRgb(c.getR(), c.getG(), c.getB());
	}

	Color getColor(int x, int y) {
		int[] rgb = ImageUtil.decodeRgb(data[y][x]);
		return new Color(rgb[0], rgb[1], rgb[2]);
	}
	
	ColorImage selection(int startx, int starty, int endx, int endy) {
		if(endx<startx || endy<starty)
			throw new IllegalArgumentException("endx<startx ou endy<starty!");
		ColorImage section = new ColorImage(endx - startx, endy - starty);
		for (int i = 0; i < section.getWidth(); i++)
			for (int j = 0; j < section.getHeight(); j++)
				section.setColor(i, j, getColor(startx+i, starty+j));
		return section;
	}

	ColorImage giveTransparentCopy(){
		ColorImage img 	= new ColorImage(getWidth(), getHeight());
		for(int i=0; i!=getWidth(); i++)
			for(int j=0; j!=getHeight();j++)
				img.setColor(i,j,Color.TRANSPARENT);
		return img;
	}
}