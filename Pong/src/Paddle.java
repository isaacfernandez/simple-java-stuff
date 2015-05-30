import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;
import javalib.colors.*;

public class Paddle {
	final static int UP = 1;
	final static int DOWN = -1;
	final int PADDLE_WIDTH = 10;
	final int PADDLE_HEIGHT = 50;
	Posn p;
	
	public Paddle(int x, int y) {
		if (x == 0) {
			x += PADDLE_WIDTH /  2;
		} else {
			x -= PADDLE_WIDTH / 2;
		}
		p = new Posn(x, y);
	}
	
	
	//dir is one of
	//1 - moving up
	//-1 - moving down
	//Move the paddle according to its current dir
	void move( int dir ) {
		//Guard against literal edge cases
		if (p.y - PADDLE_WIDTH / 2 == 0 && dir == UP) {
			return;
		} 
		else if (p.y + PADDLE_WIDTH / 2 == Game.GAME_HEIGHT &&
				   dir == DOWN){
			return;
		}
		//Handle all three cases of dir
		if (dir == UP) {
			p.y -= 5; } 
		else if (dir == DOWN) {
			p.y += 5; }
		else {
			return;
		}
	}

	public WorldImage render() {
		return 
			new RectangleImage(p, PADDLE_WIDTH, PADDLE_HEIGHT, new Green());
	}

}
