package algoritms;

public class ChaseLogic {

    public static int[] getMove(int enemyX, int enemyY, int heroX, int heroY) {
        int rangeToHeroY = Math.abs(enemyY - heroY);
        int rangeToHeroX = Math.abs(enemyX - heroX);
        int[] array = new int[2];
        if (rangeToHeroX > rangeToHeroY) {
            if (enemyX > heroX) {
                array = new int[]{0, -1};
            } else {
                array = new int[]{0, 1};
            }
        } else {
            if (enemyY > heroY) {
                array = new int[]{-1, 0};
            } else {
                array = new int[]{1, 0};
            }
        }
        return array;
    }

}
