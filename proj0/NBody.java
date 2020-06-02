public class NBody {

    private static String backgroundImage = "./images/starfield.jpg";

    public static double readRadius(String s) {
        In file = new In(s);
        double radius = file.readDouble();
        radius = file.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String s) {
        In file = new In(s);
        int n = file.readInt();
        Planet[] res = new Planet[n];
        file.readDouble();

        for (int i = 0; i < n; i ++) {
            res[i] = new Planet(file.readDouble(), file.readDouble(), file.readDouble(), file.readDouble(), file.readDouble(), file.readString());
        }

        return res;
    }


    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        Double radius = readRadius(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);

        int time = 0;
        int n = planets.length;
        double[] xForces = new double[n];
        double[] yForces = new double[n];
        while (time < T) {

            for (int i = 0; i < n; i++) {
                double fX = planets[i].calcNetForceExertedByX(planets);
                double fY = planets[i].calcNetForceExertedByY(planets);
                xForces[i] = fX;
                yForces[i] = fY;

            }

            for (int i = 0; i < n; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, NBody.backgroundImage);

            for (Planet p : planets) {
                p.draw();
            }

            StdDraw.show();

            StdDraw.pause(10);
            time += dt;
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