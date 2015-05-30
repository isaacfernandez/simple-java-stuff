import javalib.worldimages.DiskImage;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;
import javalib.colors.*;

public class Ball {
	int dir;
	Posn p;
	final int SPD = 10; //Speed
	
	Ball(int x, int y) {
		dir = (int) (Math.random() * 90); //dir is [0, 90]
		dir -= 45; //dir is [-45, 45]
		if (Math.random() > .5) {
			
		} else {
			dir += 180;
		}
		//Dir is now one of [-45, 45] or [135, 225]
		//Giving a good >< shaped initial range
		p = new Posn(x, y);
	}
	
	public Posn move() {
		double dirR = Math.PI * dir / 180;
		int nux = (int) (this.p.x + SPD * Math.cos(dirR));
		int nuy = (int) (this.p.y + SPD * Math.sin(dirR));
		return new Posn(nux, nuy);
	}
	
	public WorldImage render() {
		return new DiskImage(p, 10, new Red());
	}
	
}
