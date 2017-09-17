package com.dck;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Pendu {
  private String mWord;
  private List<String> wordGuessed;
  private int miss = 0;
  private int MAX_MISS = 5;

  public Pendu(String word) {
    mWord = word;
    wordGuessed = new ArrayList<String>();
  }

  private String showProgress() {
    String progress = "";

    for (char letter: mWord.toCharArray()) {
      if (wordGuessed.contains(String.valueOf(letter))) {
        progress += letter;
      } else {
        progress += "-";
      }
    }

    return progress;
  }

  private boolean checkMatching (String letter)  {
    boolean isLetterFound = mWord.contains(letter);
    
    if (!isLetterFound) {
      miss++;
    }

    wordGuessed.add(letter);

    return isLetterFound;
  }

   public void start() {
    System.out.println("/ JEU DU PENDU /");
    Scanner sc = new Scanner(System.in);

    do {
      System.out.printf("%nMot à deviner : %s %n--> ", showProgress());
      String input = sc.nextLine();
      
      if (wordGuessed.contains(input)) {
        System.out.printf("Vous avez déjà essayé la lettre : %s %n %n", input);
      } else {
        if (checkMatching(input)) {
          System.out.printf("Touché !%n");
        } else {
          System.out.printf("Loupé ! Coups restants : %d %n", MAX_MISS - miss);
        }
      }

    } while(miss < MAX_MISS && !showProgress().equals(mWord));

    if (miss == MAX_MISS) {
      System.out.printf("PERDU ! Le mot mystère était : %s %n", mWord);
    } else {
      System.out.printf("Bravo ! Vous avez gagné :) %n");
    }
  }
}