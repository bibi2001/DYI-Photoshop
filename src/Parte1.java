class Parte1 {
//  inserir aqui imagens para fazer o teste da parte 1
//	static ColorImage image1= new ColorImage("file");
//	static ColorImage image2= new ColorImage("file");

	//1 tem ponto de referencia 
	static ColorImage copyPasteOpaqueWithReferencePoint(ColorImage base, ColorImage copy, int x, int y, boolean transparency) {
		ImageUtil.validateNullPoint(base);
		ImageUtil.validateIllegalArgumentException(x,y);
		
		if(copy!=null)
			for(int i=0; i<copy.getWidth() && x+i<base.getWidth(); i++) //i não vai sair do copy e o x+i não vai sair da base
				for(int j=0; j<copy.getHeight() && y+j<base.getHeight(); j++)
					if(!transparency || !Color.TRANSPARENT.isIqualTo(copy.getColor(i,j))) //se a cor não for transparente, podemos pintar os pixeis
						base.setColor(i+x, j+y, copy.getColor(i,j)); //buscamos a cor da imagem copy, e metemos na imagem base no ponto certo
		return base;
	}
	
	//2
	static ColorImage wallpaper(ColorImage pattern, int width, int height){
		ImageUtil.validateNullPoint(pattern);
		ImageUtil.validateIllegalArgumentException(width, height);
		
		ColorImage wallpaper= new ColorImage(width,height);
		for(int i=0; i<wallpaper.getWidth(); i+=pattern.getWidth())
			for(int j=0; j<wallpaper.getHeight(); j+=pattern.getHeight())
				copyPasteOpaqueWithReferencePoint(wallpaper, pattern, i, j, false); 		
		return wallpaper;
	}
	
	//3 
	static ColorImage scale(ColorImage img, double value){
		ImageUtil.validateNullPoint(img);
		ImageUtil.validateIllegalArgumentException(value);
		
		 ColorImage imgScaled= new ColorImage((int)(value*img.getWidth()),(int)(value*img.getHeight()));
		 for(int i=0; i<imgScaled.getWidth(); i++)
			 for(int j=0; j<imgScaled.getHeight(); j++)
					imgScaled.setColor(i,j,img.getColor((int)(i/value),(int)(j/value)));
		 return imgScaled;
	}
	
	//4
	static ColorImage circleOnSquare(ColorImage img, int radius, int c1, int c2){
		ImageUtil.validateNullPoint(img);
		ImageUtil.validateIllegalArgumentException(c1, c2);
		if(c1<radius || c2<radius || c1+radius>img.getWidth() || c2+radius>img.getHeight() || radius>img.getWidth() || radius>img.getHeight())
			throw new IllegalArgumentException("Your circle is out of the image's limits!");
		
		ColorImage circleImg= img.selection(c1-radius, c2-radius, c1+radius, c2+radius);
		for(int i=0; i<circleImg.getWidth(); i++)
			 for(int j=0; j<circleImg.getHeight(); j++)
				 if((Math.pow(i-radius,2)+Math.pow(j-radius,2))>Math.pow(radius,2))
					 circleImg.setColor(i,j,Color.TRANSPARENT);		 
		
		return circleImg;
	}
	
	//5
	static ColorImage greyImage(ColorImage img){
		if(img!=null)
			for(int i=0; i!=img.getWidth(); i++)
				for(int j=0; j!=img.getHeight(); j++)
					img.setColor(i,j,img.getColor(i,j).setGrey());
		return img;
	}
}