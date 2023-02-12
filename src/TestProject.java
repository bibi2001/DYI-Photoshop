class TestProject {
	static void test(){
		ColorImage image1= new ColorImage("file");
		ColorImage image2= new ColorImage("file");
		ColorImage image3= new ColorImage("file");
		ColorImage wallpaper= new ColorImage("file");
		
		
		Layer layer1= new Layer(image3);
		layer1.setPosition(20,20);
		layer1.setFactor(1.5);
		layer1.setName("layer1");
		
		layer1.setActiveOrInactive(false);
		final ColorImage a1=layer1.layerFinalResult();
		layer1.setActiveOrInactive(true);
		final ColorImage a=layer1.layerFinalResult();
		
		Layer layer2= new Layer(image2);
		layer2.setName("layer2");
		
		Layer layer3= new Layer(image1,200,200);
		layer3.setName("layer3");
		
		Layer layer4= new Layer(image1,"layer4",100,400, 0.5);
		
		
		Poster poster=new Poster(1000,1000,4);
		poster.layerZeroBackgroundForThisPoster(wallpaper);
		poster.newLayerOnTheEndOfMyPoster(layer1);
		final ColorImage b=poster.posterFinalResult();
		int x=0;
		
		poster.addThisLayerHere(1,layer2);
		final ColorImage c= poster.posterFinalResult();
		x++;
		
		poster.swapTheseLayers(1,2);
		final ColorImage d= poster.posterFinalResult();
		x++;
		
		poster.addThisLayerHere(1,layer3);
		final ColorImage e= poster.posterFinalResult();
		x++;
		
		poster.doubleSizerInc();
		poster.addThisLayerHere(2,layer4);
		final ColorImage f= poster.posterFinalResult();
		x++;
		
		poster.removeThisLayerHere(1);
		final ColorImage g= poster.posterFinalResult();
		x++;
	}

}