package nl.highco.thuglife;

public class MapGenerator {
	private String[][] map;
	private int width;
	private int hight;
	
	public MapGenerator(int width, int hight){
		this.width = width;
		this.hight = hight;
		map = new String[width][hight];
	}
	
	public String[][] getRandomMap(){		
		for(int x = 0; x < width; x++){
			for(int y = 0; y < hight; y++){
				double number = Math.random();
				if(number < 0.33){
					map[x][y] = "w";
				}else{
					map[x][y] = "e";
				}
			}
		}
		return map;
	}
	
	/*
	 * w = wall
	 * e = empty space
	 * s = shop
	 */
	
	public String[][] getStandardMap(){
		map = new String[][]{
				{"w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w",}
				
		};
		return map;
	}
	
	public String[][] getStandardMap2(){
		map = new String[][]{
				{"w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w",},
				{"w","e","w","w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","w","w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","w","w","w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","w","w","w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","e","w",},
				{"w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w",}
				
		};
		return map;
	}
}
