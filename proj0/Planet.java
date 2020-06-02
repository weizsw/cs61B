public class Planet {

    private static final double G = 6.67 * 1e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet (double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet (Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance (Planet p) {
        double dx = xxPos - p.xxPos;
        double dy = yyPos - p.yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return r;
    }

    public double calcForceExertedBy (Planet p) {
        double r = calcDistance(p);
        double force = (G * mass * p.mass) / (r * r);
        return force;
    }

    public double calcForceExertedByX (Planet p) {
        double force = calcForceExertedBy(p);
        double fx = (force * (p.xxPos - xxPos)) / calcDistance(p);
        return fx;
    }

    public double calcForceExertedByY (Planet p) {
        double force = calcForceExertedBy(p);
        double fy = (force * (p.yyPos - yyPos)) / calcDistance(p);
        return fy;
    }

    private boolean equals (Planet p) {
        if (xxPos == p.xxPos && yyPos == p.yyPos && xxVel == p.xxVel && yyVel == p.yyVel && mass == p.mass && imgFileName == p.imgFileName) {
            return true;
        }
        else {
            return false;
        }
    }

    public double calcNetForceExertedByX (Planet[] ps) {
        double netF = 0;
        for (Planet p : ps) {
            if (!equals(p)) {
                netF += calcForceExertedByX(p);
            }
            else {
                continue;
            }
        }

        return netF;
    }

    public double calcNetForceExertedByY (Planet[] ps) {
        double netF = 0;
        for (Planet p : ps) {
            if (!equals(p)) {
                netF += calcForceExertedByY(p);
            }
            else {
                continue;
            }
        }
        return netF;
    }

    public void update (double dt, double fx, double fy) {
        double aNetx = fx / this.mass;
        double aNety = fy / this.mass;
        double vNewx = this.xxVel + (dt * aNetx);
        double vNewy = this.yyVel + (dt * aNety);
        double pNewx = this.xxPos + (dt * vNewx);
        double pNewy = this.yyPos + (dt * vNewy);

        this.xxVel = vNewx;
        this.yyVel = vNewy;
        this.xxPos = pNewx;
        this.yyPos = pNewy;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }
}