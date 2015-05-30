import javalib.impworld.World;
import javalib.colors.*;
import javalib.worldimages.*;

public class Game extends World {
	Ball ball;
	Paddle left;
	Paddle right;
	int leftScore = 0;
	int rightScore = 0;
	static final int GAME_WIDTH = 320;
	static final int GAME_HEIGHT = 320;
	
	Game() {
		ball = new Ball(GAME_WIDTH / 2, GAME_HEIGHT / 2);
		left = new Paddle(0, GAME_HEIGHT / 2);
		right = new Paddle(GAME_WIDTH, GAME_HEIGHT / 2);
	}

	public WorldImage makeImage() {
		WorldImage bg = new RectangleImage(
				new Posn(GAME_HEIGHT / 2, GAME_WIDTH /2),
				GAME_WIDTH, GAME_HEIGHT, new Black()); 
		bg = new OverlayImages(bg, ball.render());
		bg = new OverlayImages(bg, left.render());
		bg = new OverlayImages(bg, right.render());
		bg = new OverlayImages(bg, this.ui());
		return bg;
	}
	
	public void restart() {
		ball = new Ball(GAME_WIDTH / 2, GAME_HEIGHT / 2);
		left = new Paddle(0, GAME_HEIGHT / 2);
		right = new Paddle(GAME_WIDTH, GAME_HEIGHT / 2);
	}
	
	public boolean collision(Posn p, Paddle paddle) {
		boolean horiz = (Math.abs(p.x - paddle.p.x) <= 10);
		boolean vert = ((p.y >= paddle.p.y - paddle.PADDLE_HEIGHT / 2) 
						&&
				 (p.y <= paddle.p.y + paddle.PADDLE_HEIGHT / 2));
		return horiz && vert;
	}
	
	public void onTick() {
		Posn nupos = ball.move();
		if (nupos.y <= 5 || nupos.y >= (GAME_HEIGHT - 5)) {
			ball.dir *= -1;  //Flip the direction
			ball.dir = ball.dir % 360; 
		}
		if (nupos.x <= 0) {
			this.restart();
			this.leftScore += 1;
			return;
		} else if (nupos.x >= GAME_WIDTH) {
			this.restart();
			this.rightScore += 1;
			return;
		}
		
		if (collision(nupos, left) || collision(nupos, right)) {
			ball.dir = 180 - ball.dir;
			//That's enough for reflect the ball back
			//Pong mechanics changes it based on paddle hit though
			ball.dir += (Math.random() * 10) - 5; //Varies the angle by +- 5
		}
		ball.p = ball.move();
	}
	
	public void onKeyEvent(String key) {
		int up = Paddle.UP;
		int down = Paddle.DOWN;
        if (key.equals("r")) {
        	//Reset
        } 
        else if (key.equals("a")) {
        	this.left.move(up);
        }
        else if (key.equals("z")) {
        	this.left.move(down);
        }
        else if (key.equals("k")) {
        	this.right.move(up);
        }
        else if (key.equals("m")) {
        	this.right.move(down);
        }
        else if (key.equals("r")) {
        	this.restart();
        	this.leftScore = 0;
        	this.rightScore = 0;
        }
    }

	public WorldImage ui() {
		return new TextImage(new Posn(GAME_WIDTH / 2, 30),
				this.leftScore + "  :  " + this.rightScore,
				13, 0, new White());
	}
	
	
}
