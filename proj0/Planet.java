public class Planet {
    public static final double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((p.xxPos - xxPos) * (p.xxPos - xxPos) + (p.yyPos - yyPos) * (p.yyPos - yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        double r = calcDistance(p);
        double F = G * mass * p.mass / (r * r);
        return F;
    }

    public double calcForceExertedByX(Planet p) {
        double r = calcDistance(p);
        double F = calcForceExertedBy(p);
        double Fx = F * (p.xxPos - xxPos) / r;
        return Fx;
    }

    public double calcForceExertedByY(Planet p) {
        double r = calcDistance(p);
        double F = calcForceExertedBy(p);
        double Fy = F * (p.yyPos - yyPos) / r;
        return Fy;
    }

    public double calcNetForceExertedByX(Planet[] allP) {
        double allX = 0;
        for (Planet p : allP) {
            if (this.equals(p)) {
                continue;
            }
            allX += calcForceExertedByX(p);
        }
        return allX;
    }

    public double calcNetForceExertedByY(Planet[] allP) {
        double allY = 0;
        for (Planet p : allP) {
            if (this.equals(p)) {
                continue;
            }
            allY += calcForceExertedByY(p);
        }
        return allY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += aX * dt;
        yyVel += aY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}
