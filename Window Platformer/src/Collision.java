import java.util.LinkedList;

public class Collision {
    public static final int COLLISION_FALSE = 0;
    public static final int COLLISION_LEFT = 1;
    public static final int COLLISION_RIGHT = 2;
    public static final int COLLISION_TOP = 3;
    public static final int COLLISION_BOTTOM = 4;

    public LinkedList<Integer> checkCollision(GameObject obj1, GameObject obj2) {
        LinkedList<Integer> output = new LinkedList<>();
        boolean left = obj1.x + obj1.width >= obj2.x && obj1.x + obj1.width <= obj2.x + obj2.width;
        boolean right = obj1.x <= obj2.x + obj2.width && obj1.x >= obj2.x;
        boolean top = obj1.y + obj1.height >= obj2.y && obj1.y + obj1.height <= obj2.y + obj2.height;
        boolean bottom = obj1.y <= obj2.y + obj2.height && obj1.y >= obj2.y;
        boolean xFull = obj1.x + obj1.width >= obj2.x && obj1.x <= obj2.x + obj2.width;
        boolean yFull = obj1.y + obj1.height >= obj2.y && obj1.y <= obj2.y + obj2.height;
        int distanceFromTop = obj2.y - obj1.y + obj1.height;
        int distanceFromBottom = obj1.y + obj1.height - obj2.y;
        if (!left && !right || !top && !bottom) {
            output.add(COLLISION_FALSE);
        }
        if (bottom && (left || right || xFull) && distanceFromTop < 10) {
            output.add(COLLISION_BOTTOM);
        } else if (top && (left || right || xFull) && distanceFromBottom < 20) {
            output.add(COLLISION_TOP);
        } else if (left && (top || bottom || yFull)) {
            output.add(COLLISION_LEFT);
        } else if (right && (top || bottom || yFull)) {
            output.add(COLLISION_RIGHT);
        }
        return output;
    }

    public static LinkedList<Integer> shortenCollisionList(LinkedList<Integer> list) {
        LinkedList<Integer> output = new LinkedList<>();
        boolean collisionHappened = false;
        for (int i : list) {
            if (i != COLLISION_FALSE) {
                collisionHappened = true;
                break;
            }
        }
        for (int i : list) {
            if (collisionHappened && i != COLLISION_FALSE) {
                output.add(i);
            }
        }
        if (!collisionHappened) {
            output.add(COLLISION_FALSE);
        }
        return output;
    }

    public LinkedList<Integer> checkLevelCollision(Level level, GameObject obj) {
        LinkedList<Integer> levelCollision = new LinkedList<>();
        for (GameObject i : level.gameObjects) {
            levelCollision.addAll(i.checkCollision(obj));
        }
        return levelCollision;
    }

    public static boolean touchingFloor(LinkedList<Integer> collisionList) {
        for (int i : collisionList) {
            if (i == COLLISION_BOTTOM) {
                return true;
            }
        }
        return false;
    }
    public static boolean touchingRoof(LinkedList<Integer> collisionList) {
        for (int i : collisionList) {
            if (i == COLLISION_TOP) {
                return true;
            }
        }
        return false;
    }
    public static boolean touchingLeftWall(LinkedList<Integer> collisionList) {
        for (int i : collisionList) {
            if (i == COLLISION_LEFT) {
                return true;
            }
        }
        return false;
    }public static boolean touchingRightWall(LinkedList<Integer> collisionList) {
        for (int i : collisionList) {
            if (i == COLLISION_RIGHT) {
                return true;
            }
        }
        return false;
    }

}