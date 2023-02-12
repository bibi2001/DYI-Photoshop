class Layer {
	ColorImage img;
	final ColorImage savedImg; 
	double factor=1;
	int x;
	int y;
	String defaultName;
	
	//i)
	Layer(ColorImage img, String defaultName, int x, int y, double factor){
		ImageUtil.validateNullPoint(img);
		ImageUtil.validateIllegalArgumentException(x,y);
		
		this.img= img;
		this.savedImg=img; 
		this.defaultName= defaultName;
		this.x= x;
		this.y= y;
		this.factor= factor;
	}
	
	//ii)
	Layer(ColorImage img, int x, int y){
		ImageUtil.validateNullPoint(img);
		ImageUtil.validateIllegalArgumentException(x,y);
		
		this.img=img;
		this.savedImg=img;
		this.x=x;
		this.y=y;
	}
	
	//iii)
	Layer(ColorImage img){
		ImageUtil.validateNullPoint(img);
		
		this.img=img;
		this.savedImg=img;
	}
	
	//1
	void setName(String newName){
		defaultName=newName;
	}
	
	//2
	void setPosition(int newX, int newY){
		ImageUtil.validateIllegalArgumentException(newX,newY);
		
		x=newX;
		y=newY;
	}
	void setFactor(double newFactor){
		ImageUtil.validateIllegalArgumentException(newFactor);
		
		factor=newFactor;
	}
	
	//3 
	void setActiveOrInactive(boolean isActive){		
		if(!isActive)
			img=img.giveTransparentCopy();
		else
			img=savedImg;
	}
	
	//4
	ColorImage layerFinalResult(){
		ColorImage imgScaled= Parte1.scale(img, factor);
		ColorImage layerBackground= new ColorImage(imgScaled.getWidth()+x,imgScaled.getHeight()+y);
		ColorImage finalResult= Parte1.copyPasteOpaqueWithReferencePoint(layerBackground.giveTransparentCopy(), imgScaled, x, y,true);
		return finalResult;
	}
}