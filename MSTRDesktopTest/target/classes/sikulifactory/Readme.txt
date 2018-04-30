 USage of FindBy() , FindByImage() and FindByImages() :- 
1) For Single Image 
--> @FindBy(image = "New-Dossier.png")
    private SikuliElement New-Dossier;
    
--> @FindByImage("New-Dossier.png")
	private SikuliElement New-Dossier;  
	   
2) For Multiple images -
 --> @FindBy(images = {"New-Dossier1.png", "New-Dossier2.png"}
 	 private SikuliElement New-Dossier;
 	 
 --> @FindByImages({"menu-windows10.png","menu-windows8.png"})
	 private SikuliElement menu;