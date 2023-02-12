class Poster {
	final int width;
	final int height;
	Layer[] poster;
	int nextLayer=1;

	Poster(int width, int height){
		ImageUtil.validateIllegalArgumentException(width,height);
		
		this.width=width;
		this.height=height;
		poster= new Layer[50];
	}
	Poster(int width, int height, int numberOfLayers){
		ImageUtil.validateIllegalArgumentException(width,height);
		ImageUtil.validateIllegalArgumentException(numberOfLayers,numberOfLayers);
		
		this.width=width;
		this.height=height;
		poster= new Layer[numberOfLayers];
	}
	
	//1
	void layerZeroBackgroundForThisPoster(ColorImage wallpaperPattern){
		ImageUtil.validateNullPoint(wallpaperPattern);
		
		poster[0]=new Layer(Parte1.wallpaper(wallpaperPattern,width,height));
	}
	
	//2
	void newLayerOnTheEndOfMyPoster(Layer newLayer){
		ImageUtil.validateNullPoint(newLayer);
		ImageUtil.isFull(nextLayer, poster.length);
		ImageUtil.validateIllegalArgumentException(newLayer.x, newLayer.y, width, height);
		
		poster[nextLayer]=newLayer;
		nextLayer++;
	}
	//cria um novo poster[] copiando o antigo e duplicando o numero de camadas
	void doubleSizerInc(){
		Layer[] doubleSizedPoster= new Layer[poster.length*2];
		for(int i=0; i!=nextLayer; i++)
			doubleSizedPoster[i]=poster[i];	
		poster=doubleSizedPoster;
	}
	
	//3
	void removeThisLayerHere(int layerIdx){
		ImageUtil.validateIllegalArgument(layerIdx);
		
		 for(int i=layerIdx; i!=nextLayer-1; i++)
			 poster[i]=poster[i+1];
		 if(layerIdx<nextLayer)
			 nextLayer--;
	}
	//4
	void addThisLayerHere(int layerIdx, Layer layerThatWeAreAdding){
		ImageUtil.validateIllegalArgument(layerIdx);
		ImageUtil.validateNullPoint(layerThatWeAreAdding);
		ImageUtil.isFull(nextLayer, poster.length);
		ImageUtil.validateIllegalArgumentException(layerThatWeAreAdding.x, layerThatWeAreAdding.y, width, height);
		
		if(layerIdx>nextLayer)
			layerIdx=nextLayer;
		for(int i=nextLayer; i!=layerIdx-1; i--)
			poster[i]=poster[i-1];
		poster[layerIdx]=layerThatWeAreAdding;	
		nextLayer++;
	}
			
	//5
	void swapTheseLayers(int layerIdxFirst, int layerIdxSecond){
		ImageUtil.validateIllegalArgument(layerIdxFirst);
		ImageUtil.validateIllegalArgument(layerIdxSecond);
		
		if(layerIdxSecond>=nextLayer || layerIdxFirst>=nextLayer)
			throw new IllegalArgumentException("One of the layers you want to swap is empty or non-existant");
		
		final Layer savedLayerIdxFirst= poster[layerIdxFirst];
		poster[layerIdxFirst]=poster[layerIdxSecond];
		poster[layerIdxSecond]=savedLayerIdxFirst;
	}
	
	//6
	ColorImage posterFinalResult(){
		ColorImage finalResult= poster[0].layerFinalResult();
		for(int i=1; i<nextLayer; i++)
			finalResult=Parte1.copyPasteOpaqueWithReferencePoint(finalResult, poster[i].layerFinalResult(), 0, 0, true);	
     return finalResult;
	}
}