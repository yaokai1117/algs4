
public class CollisionSystem {
	private MinPQ<Event> pq;
	private double t = 0.0;
	private Particle[] particles;
	
	public CollisionSystem(Particle[] particles) {
		// TODO Auto-generated constructor stub
		this.particles = particles.clone(); 
	}
	
	private void predict(Particle a) {
		if (a == null)
			return;
		for (Particle particle : particles) {
			if (particle == a)
				continue;
			double dt = a.timeToHit(particle);
			pq.insert(new Event(t + dt, a, particle));
		}
		pq.insert(new Event(t + a.timeToHitVerticalWall(), a, null));
		pq.insert(new Event(t + a.timeToHitHorizontalWall(), null, a));
	}
	
	private void redraw() {
		StdDraw.clear();
		for (Particle particle : particles) {
			particle.draw();
		}
	}
	
	public void simulate() {
		StdDraw.setXscale(0, 100);
		StdDraw.setYscale(0, 100);
		
		pq = new MinPQ<Event>();
		for (Particle particle : particles) 
			predict(particle);
		
		while (!pq.isEmpty()) {
			Event event = pq.delMin();
			if (!event.isValid(t))
				continue;
			Particle a = event.getA();
			Particle b = event.getB();
			System.out.print(event.getTime() + " : " + t);
			if (a == null || b == null)
				System.out.println("my fault");
			else 
				System.out.println("fuck");
			
			double moveTime = event.getTime() - t;
			while (moveTime > 0.5) {
				StdDraw.clear();
				for (Particle particle : particles){
					particle.move(0.5);
					particle.draw();
				}
				StdDraw.show(50);
				moveTime -= 0.5;
			}
			StdDraw.clear();
			for (Particle particle : particles){
				particle.move(moveTime);
				particle.draw();
			}
			StdDraw.show((int)(moveTime*100));
			t = event.getTime();
			
			if (a != null && b != null)
				a.bounceOff(b);
			else if (a != null && b == null)
				a.bounceOffVerticalWall();
			else if (a == null && b != null)
				b.bounceOffHorizontalWall();
			
			predict(a);
			predict(b);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In(args[0]);
		int N = in.readInt();
		Particle[] test = new Particle[N];
		for (int i = 0; i < N; i++) {
			double rx = in.readDouble();
			double ry = in.readDouble();
			double vx = in.readDouble();
			double vy = in.readDouble();
			double radius = in.readDouble();
			double mass = in.readDouble();
			test[i] = new Particle(rx, ry, vx, vy, radius, mass);
		}
		
		CollisionSystem testCollisionSystem = new CollisionSystem(test);
		testCollisionSystem.simulate();
	}

}
