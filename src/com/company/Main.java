package com.company;

import java.util.Random;

public class Main {

    public static int bossHP = 15000;
    public static int bossDM = 100;
    public static String bossDefence = "";
    public static int[] heroesHP = {250, 260, 245, 270, 210, 185, 200, 450};
    public static int[] heroesDM = {15, 10, 20, 0, 10, 15, 5, 20};
    public static String[] heroesType = {"Physical", "Magical", "Kinetic", "Medic", "Lucky", "Berserk", "Thor", "Golem"};
    public static int round = 0;

    public static void  heroMedic(){
        for (int i = 0; i < heroesHP.length ; i++) {
            if (i == 3){
                continue;
            }
            if (heroesHP[i] > 0 && heroesHP[i] < 100 && heroesHP[3] > 0){
                heroesHP[i] += 50;
                System.out.println("Medic HILL - " + heroesType[i]);
                break;
            }
        }
    }

    public static void heroGolem(){
        for (int i = 0; i < heroesHP.length; i++) {
            if (heroesHP[7] > 0){
                heroesHP[7] -= bossDM * 1 / 5;
                heroesHP[i] += bossDM * 1 / 5;
                System.out.println("Golem get: " + bossDM);
                break;
            }
        }
    }
    public static void heroLaki() {
        Random random = new Random();
        boolean run = random.nextBoolean();
        for (int i = 0; i < heroesHP.length ; i++){
            if (heroesHP[5] > 0)
                if (run){
                    heroesHP[5] = heroesHP[5] + 90;
                    System.out.println("Laki dodged damage");
                    break;
                }
        }
    }

    public static void heroBerserk(){

        for (int i = 0; i < heroesHP.length; i++) {
            if (heroesHP[5] > 0){
                heroesHP[5] -= bossDM * 1 / 14;
                heroesDM[5] += heroesDM[6] + bossDM * 1 / 14;
                System.out.println("Berserk blocking damage and up damage");
                break;
            }

        }
    }

    public static void heroTor(){
        Random random = new Random();
        boolean tor = random.nextBoolean();
        for (int i = 0; i < heroesHP.length; i++) {
            if (heroesHP[6] > 0){
                if(tor){
                    bossDM = bossDM - bossDM;
                    System.out.println("The boss is stunned");
                    break;
                }else{
                    bossDM = 60;
                }
            }
        }

    }

    public static void main(String[] args) {
        printStatistics();
        while (!isGameOver()) {
            round();
            heroMedic();
            heroGolem();
            heroLaki();
            heroBerserk();
            heroTor();
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(
                heroesType.length);
        bossDefence = heroesType[randomIndex];
        System.out.println("Boss chose " + bossDefence);
    }

    public static void round() {
        round++;
        chooseBossDefence();
        if (bossHP > 0) {
            bossHits();
        }
        heroesHit();
        printStatistics();
    }

    public static boolean isGameOver() {
        if (bossHP <= 0) {
            System.out.println("HEROES WIN!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHP.length; i++) {
            if (heroesHP[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss win LOOSER");
        }
        return allHeroesDead;
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDM.length; i++) {
            if (heroesHP[i] > 0 && bossHP > 0) {
                if (heroesType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(11);
                    if (bossHP - heroesDM[i] * coeff < 0) {
                        bossHP = 0;
                    } else {
                         bossHP = bossHP - heroesDM[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDM[i] * coeff );
                } else {
                    if (bossHP - heroesDM[i] < 0) {
                        bossHP = 0;
                    } else {
                        bossHP = bossHP - heroesDM[i];
                    }
                }
            }


        }

    }

    public static void bossHits() {
        for (int i = 0; i < heroesHP.length; i++) {
            if (heroesHP[i] > 0) {
                if (heroesHP[i] - bossDM < 0) {
                    heroesHP[i] = 0;
                } else {
                    heroesHP[i] = heroesHP[i] - bossDM;
                }
            }

        }


    }

    public static void printStatistics() {
        System.out.println("\n" + round + " ROUND ______________");
        System.out.println("Boss HP " + "(" + bossHP + ")" + " ; damage; " + "(" + bossDM + "),");
        for (int i = 0; i < heroesHP.length; i++) {
            System.out.println(heroesType[i]  + " HP " + heroesHP[i] + "; damage; "  + heroesDM[i]);


        }
        System.out.println("____________________" + " \n");
    }
}