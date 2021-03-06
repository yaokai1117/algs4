
public class Particle {
	private double rx, ry;
	private double vx, vy;
	private final double radius;
	private final double mass;
	private int count;
	
	public Particle(double rx, double ry, double vx, double vy, double radius, double mass) {
		// TODO Auto-generated constructor stub
		this.rx = rx;
		this.ry = ry;
		this.vx = vx;
		this.vy = vy;
		this.radius = radius;
		this.mass = mass;
		this.count = 0;
	}
	
	public void move(double dt) {
		rx += dt*vx;
		ry += dt*vy;
	}
	
	public void draw() {
		StdDraw.filledCircle(rx, ry, radius);
	}
	
	public int getCount() {
		return count;
	}
	
	public double timeToHit(Particle that) {
		if (this == that) 
			return Double.POSITIVE_INFINITY;
		double dx = that.rx - this.rx, dy = that.ry - this.ry;
		double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
		double dvdr = dx*dvx + dy*dvy;
		if( dvdr > 0) 
			return Double.POSITIVE_INFINITY;
		double dvdv = dvx*dvx + dvy*dvy;
		double drdr = dx*dx + dy*dy;
		double sigma = this.radius + that.radius;
		double d = (dvdr*dvdr) - dvdv * (drdr - sigma*sigma);
		if (d < 0) 
			return Double.POSITIVE_INFINITY;
		return -(dvdr + Math.sqrt(d)) / dvdv;
	}
	
	public void bounceOff(Particle that) {
		double dx = that.rx - this.rx, dy = that.ry - this.ry;
		double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
		double dvdr = dx*dvx + dy*dvy;
		double dist = this.radius + that.radius;
		double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
		double Jx = J * dx / dist;
		double Jy = J * dy / dist;
		this.vx += Jx / this.mass;
		this.vy += Jy / this.mass;
		that.vx -= Jx / that.mass;
		that.vy -= Jy / that.mass;
		this.count++;
		that.count++;
	}
	
	public double timeToHitVerticalWall() {
		if (vx > 0) {
			if (rx < 100 - radius)
				return (100.0 - radius - rx)/vx;
			else 
				return 0;
		}
		else if (vx < 0) {
			if (rx > radius)
				return (radius - rx)/vx;
			else 
				return 0;
		}
		else 
			return Double.POSITIVE_INFINITY;
	}
	
	public double timeToHitHorizontalWall() {
		if (vy > 0) 
			return (100.0 - radius - ry)/vy;
		else if (vy < 0) 
			return (radius - ry)/vy;
		else 
			return Double.POSITIVE_INFINITY;
	}
	
	public void bounceOffVerticalWall() {
		vx = -vx;
		count++;
	}
	
	public void bounceOffHorizontalWall() {
		vy = -vy;
		count++;		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
