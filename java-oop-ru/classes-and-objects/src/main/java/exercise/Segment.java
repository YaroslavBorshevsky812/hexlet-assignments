package exercise;

// BEGIN


public class Segment {

    public Point beginPoint;
    public Point endPoint;
    public Point midPoint;
    public Segment(Point begingPoint, Point endPoint) {
        this.beginPoint = begingPoint;
        this.endPoint = endPoint;
        int xMidPoint = (begingPoint.getX() + endPoint.getX()) / 2;
        int yMidPoint = (begingPoint.getY() + endPoint.getY()) / 2;
        this.midPoint = new Point(xMidPoint, yMidPoint);
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getMidPoint() {
        return midPoint;
    }
}
// END
