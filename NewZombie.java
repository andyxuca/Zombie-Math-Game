//Andy Xu and Anant Chaudhary 4/9/18
// Class to create new Zombies

public class NewZombie
{
	public int xD, yD;
	public int zx, zy;
	public NewZombie(int zx, int zy){

		//CHECKS ZOMBIE COORDINATES AND DETERMINES ZOMBIE'S DIRECTION TOWARDS HUMAN
		if((zx<350)&&(zy<350)) {
			xD = 1;
			yD = 1;
		}
		if((zx>350)&&(zy<350)) {
			xD = -1;
			yD = 1;
		}
		if((zx<350)&&(zy>350)) {
			xD = 1;
			yD = -1;
		}
		if((zx>350)&&(zy>350)) {
			xD = -1;
			yD = -1;
		}

	}
	
	//getter for x direction
	public int GetXDirection(){
		return xD;
	}
	
	//getter for y direction
	public int GetYDirection(){
		return yD;
	}
}