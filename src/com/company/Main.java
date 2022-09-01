package com.company;

import java.util.Random;

public class Main {

    public static int bossHP = 700;
    public static int bossDM = 50;
    public static String bossDefence = "";
    public static int[] heroesHP = {250, 260, 245, 270};
    public static int[] heroesDM = {15, 10, 20, 0};
    public static String[] heroesType = {"Physical", "Magical", "Kinetic", "Medic"};
    public static int round = 0;

    public static void  heroMedic() {
        int index = 0;
        int help = 20;
        for (String name : heroesType) {
            if (name.equals("Medic")) {
                for (int i = 0 ; i < heroesHP.length; i++){
                    if (heroesHP[i] < 100 && heroesHP[i] > 0 && heroesHP[i] != heroesHP[index] && heroesHP[index] > 0) {
                        heroesHP[i] += help;
                        System.out.println("Medic help:  " + heroesType[i]);
                        break;
                    }
                }
            }
            index++;
        }
    }


    public static void main(String[] args) {
        printStatistics();
        while (!isGameOver()) {
            round();
            heroMedic();

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