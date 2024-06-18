package com.shannontheoret.tinytowns;

public class RelativePosition {
    Integer x;
    Integer y;


    public RelativePosition(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getIndex() {
        if (y==0) {
            if(x==0) {
                return 0;
            } else if (x==1) {
                return 1;
            } else if (x==2) {
                return 2;
            } else if (x==3) {
                return 3;
            }
        } else if (y==1) {
            if (x==0) {
                return 4;
            } else if (x==1) {
                return 5;
            } else if (x==2) {
                return 6;
            } else if (x==3) {
                return 7;
            }
        } else if (y==2) {
            if (x==0) {
                return 8;
            } else if (x==1) {
                return 9;
            } else if (x==2) {
                return 10;
            } else if (x==3) {
                return 11;
            }
        } else if (y==3) {
            if (x==0) {
                return 12;
            } else if (x==1) {
                return 13;
            } else if (x==2) {
                return 14;
            } else if (x==3) {
                return 15;
            }
        }
        return -1;
    }

    public RelativePosition generateShift(RelativePosition relativePosition) {
        return new RelativePosition(relativePosition.getX() - x, relativePosition.getY() - y);
    }

    public RelativePosition shift(RelativePosition relativePosition) {
        return new RelativePosition(relativePosition.getX() + x, relativePosition.getY() + y);
    }

    public static RelativePosition getRelativePositionFromIndex(Integer index) throws IndexOutOfBoundsException {
        switch (index) {
            case 0:
                return new RelativePosition(0,0);
            case 1:
                return new RelativePosition(1,0);
            case 2:
                return new RelativePosition(2,0);
            case 3:
                return new RelativePosition(3,0);
            case 4:
                return new RelativePosition(0,1);
            case 5:
                return new RelativePosition(1,1);
            case 6:
                return  new RelativePosition(2, 1);
            case 7:
                return new RelativePosition(3, 1);
            case 8:
                return new RelativePosition(0, 2);
            case 9:
                return new RelativePosition(1, 2);
            case 10:
                return new RelativePosition(2, 2);
            case 11:
                return new RelativePosition(3, 2);
            case 12:
                return new RelativePosition(0, 3);
            case 13:
                return new RelativePosition(1, 3);
            case 14:
                return new RelativePosition(2, 3);
            case 15:
                return new RelativePosition(3,3);
        }
        throw new IndexOutOfBoundsException();
    }

    public static boolean isValidIndex(Integer index) {
        return index >= 0 && index < 16;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelativePosition that = (RelativePosition) o;

        if (!x.equals(that.x)) return false;
        return y.equals(that.y);
    }

    @Override
    public int hashCode() {
        int result = x.hashCode();
        result = 31 * result + y.hashCode();
        return result;
    }
}
