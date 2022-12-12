import java.util.LinkedList;

public class Player {
    GameObject playerInfo;
    CreateWindow player;
    int speed = 1;
    int maxSpeed = 7;
    int gravity = 1;
    int maxFallSpeed = 10;
    int jumpHeight = 20;
    int xVelocity = 0;
    int yVelocity = 0;
    public Player(GameObject playerInfo) {
        this.playerInfo = playerInfo;
    }
    public Player spawnPlayer() {
        player = new CreateWindow("", playerInfo.x, playerInfo.y, playerInfo.width, playerInfo.height, false);
        return this;
    }
    public void updatePlayer(Level currentLoadedLevel) {
        if (player.dPressed && xVelocity < maxSpeed) {
            xVelocity += speed;
        }
        if (player.aPressed && xVelocity > -maxSpeed) {
            xVelocity -= speed;
        }
        if (!player.dPressed && !player.aPressed) {
            if (xVelocity > 0) {
                xVelocity -= speed;
            } else if (xVelocity < 0) {
                xVelocity += speed;
            }
        }
        if (yVelocity < maxFallSpeed) { yVelocity += gravity; }
        playerInfo.x += xVelocity;
        playerInfo.y += yVelocity;
        LinkedList<Integer> levelCollision = Collision.shortenCollisionList(playerInfo.checkLevelCollision(currentLoadedLevel));
        boolean touchingFloor = Collision.touchingFloor(levelCollision);
        boolean touchingRoof = Collision.touchingRoof(levelCollision);
        boolean touchingLeftWall = Collision.touchingLeftWall(levelCollision);
        boolean touchingRightWall = Collision.touchingRightWall(levelCollision);
        boolean canJump = false;
        while (touchingLeftWall) {
            playerInfo.x++;
            xVelocity = 0;
            levelCollision = Collision.shortenCollisionList(playerInfo.checkLevelCollision(currentLoadedLevel));
            touchingLeftWall = Collision.touchingLeftWall(levelCollision);
        }
        while (touchingRightWall) {
            playerInfo.x--;
            xVelocity = 0;
            levelCollision = Collision.shortenCollisionList(playerInfo.checkLevelCollision(currentLoadedLevel));
            touchingRightWall = Collision.touchingRightWall(levelCollision);
        }
        while (touchingFloor) {
            playerInfo.y--;
            if (yVelocity > 0) yVelocity = 0;
            levelCollision = Collision.shortenCollisionList(playerInfo.checkLevelCollision(currentLoadedLevel));
            touchingFloor = Collision.touchingFloor(levelCollision);
            canJump = true;
        }
        while (touchingRoof) {
            playerInfo.y++;
            if (yVelocity < 0) yVelocity = 0;
            levelCollision = Collision.shortenCollisionList(playerInfo.checkLevelCollision(currentLoadedLevel));
            touchingRoof = Collision.touchingRoof(levelCollision);
        }
        if (canJump && player.spacePressed) {
            yVelocity = -jumpHeight;
        }
        updatePlayerLocation();
    }
    public void updatePlayerLocation() {
        player.setLocation(playerInfo.x, playerInfo.y);
    }
    public void drawPlayer() {
        playerInfo.drawObject(player);
    }
}
