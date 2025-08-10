public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int plantNum = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int plantNum = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[plantNum];
        int i = 0;
        while (i < plantNum && in.hasNextLine()) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();

            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i++] = new Planet(xPos, yPos, xVel, yVel, mass, imgFileName);
        }
        return planets;
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("参数数量小于3，请重新传参.");
        }
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String fileName = args[2];
        double radius = readRadius(fileName);
        Planet[] planets = readPlanets(fileName);

        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);

        StdDraw.enableDoubleBuffering();

        double t = 0;
        while (t < T) {
            double[] xForce = new double[planets.length];
            double[] yForce = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForce[i] = planets[i].calcNetForceExertedByX(planets);
                yForce[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForce[i], yForce[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }
}
