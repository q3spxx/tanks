package com.q3spxx.Game;

public enum EnemyType {
    USUAL(0, 4, 2, 8, 1),
    FASTER(1, 5, 4, 8, 1),
    FASTERSHOT(2, 6, 2, 8, 1),
    BRUSER(2, 7, 2, 4, 3);
    public int n;
    public int spriteX = 8;
    public int spriteY;
    public int speed;
    public int bulletSpeed;
    public int HP;
    EnemyType(int n, int spriteY, int speed, int bulletSpeed, int hp){
        this.n = n;
        this.spriteY = spriteY;
        this.speed = speed;
        this.bulletSpeed = bulletSpeed;
        this.HP = hp;
    }
}
