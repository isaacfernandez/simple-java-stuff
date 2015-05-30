import javalib.worldimages.Posn;
import tester.Tester;

public class Examples {
	Paddle p;
	Ball b;
	//100 x 100 grid
	
	public void init() {
		p = new Paddle(0, 50);
		b = new Ball(50, 50);
		b.dir = 0;
	}
	
	void testBallMove(Tester t) {
		init();
		t.checkExpect(b.move(), new Posn(60, 50));
		b.dir = 90;
		t.checkExpect(b.move(), new Posn(50, 60));
		b.dir = 180;
		t.checkExpect(b.move(), new Posn(40, 50));
		b.dir = 270;
		t.checkExpect(b.move(), new Posn(50, 40));
	}
	
	
	void testBigBang(Tester t) {
		Game game = new Game();
		game.bigBang(Game.GAME_WIDTH, Game.GAME_HEIGHT, .069);
		
	}
	
	
	
	
}
