/*Copyright (C) 2013  George Bell

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License along
with this program; if not, write to the Free Software Foundation, Inc.,
51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.*/

package drinkinggame;
import java.util.Random;

public class DrinkingGame{
    public static void main(String[] args) {
        
        Random gen = new Random();
        
        drinkers[] character = new drinkers[8]; //Creation of the drinkers
        character[0] = new drinkers("Person 1", 1.1, 1, "m", "lag");
        character[1] = new drinkers("Person 2", 1.0, 1, "m", "lag");
        character[2] = new drinkers("Person 3", 1.2, 1, "m", "dis");
        character[3] = new drinkers("Person 4", 1.2, 0.8, "m", "wine");
        character[4] = new drinkers("Person 5", 1.1, 0.9, "f", "wine");
        character[5] = new drinkers("Person 6", 1.5, 0.6, "f", "vodka");
        character[6] = new drinkers("Person 7", 1.2, 1.0, "f", "mal");
        character[7] = new drinkers("Person 8", 0.9, 1.0, "f", "whisk");
        
        cards[] deck = new cards[53];//Card generation
        deck[52] = new cards(4, 13);
        deck[52].setOwner("Invalid");
        int j = 0;
        int k = 0;
        for(int i=0;i<52;i++){
            if(j==4)
                j = 0;
            if(k==13)
                k = 0;
            deck[i] = new cards(j,k);
            j++;
            k++;
        }
        
        for (int i = 0; i<(character.length);i++) {
            for (k = 0; k<4; k++) {
                int cCard;
                cCard = 52;
                while (deck[cCard].getOwned()==true)
                    cCard = gen.nextInt(52);
                deck[cCard].setOwner(character[i].getName());
                character[i].setCard(deck[cCard], k);
            }
        }
        
        //Round 1
        
        System.out.println("Round 1");
        System.out.println("");
        
        for(int i = 0; i<character.length; i++){
            
            boolean guess;//true = red, false = black
            int cCard = gen.nextInt(2);
            if(cCard == 0){//Guess Red
                guess = true;
                System.out.println(character[i].getName() + " has guessed Red.");
            }
            else{
                guess = false; //Guess Black
                System.out.println(character[i].getName() + " has guessed Black.");
            }
            
            if(((character[i].getSuit(0).equals("Hearts")||character[i].getSuit(0).equals("Diamonds"))&&guess==true)||((character[i].getSuit(0).equals("Clubs")||character[i].getSuit(0).equals("Spades"))&&guess==false)){
                System.out.println(character[i].getCapNoun()+" was right. It was " + character[i].getCard(0)+".");
                int l = i;
                while(l==i) {
                    l=gen.nextInt(8);
                }
                if(character[i].getNumVal(0)<9) {
                    System.out.println(character[l].getName() + " was given these drinks by " + character[i].getName() + ". The amount of drink "+character[l].getNoun()+" has left is " + character[l].takeDrink((character[i].getNumVal(0)+2)*10));
                }
                else if(character[i].getNumVal(0)==12) {
                    System.out.println(character[l].getName() + " was given these drinks by " + character[i].getName() + ". The amount of drink "+character[l].getNoun()+" has left is " + character[l].takeDrink(100));
                }
                else {
                    System.out.println(character[l].getName() + " was given this down by " + character[i].getName() + ". They have downed their drink.");
                    character[l].downDrink();
                }
                
                if(character[l].getDrink()<=0) {
                    System.out.println(character[l].getName() + " has had a refill.");
                    character[l].refill();
                }
            }
            else {
                System.out.println(character[i].getCapNoun()+" was wrong. It was actually " + character[i].getCard(0)+".");
                if(character[i].getNumVal(0)<9)
                    System.out.println(character[i].getCapNoun()+" took a drink. The amount of drink "+character[i].getNoun()+" has left is " + character[i].takeDrink((character[i].getNumVal(0)+2)*10));
                else if(character[i].getNumVal(0)==12)
                    System.out.println(character[i].getCapNoun()+" took a drink. The amount of drink "+character[i].getNoun()+" has left is " + character[i].takeDrink(100));
                else {
                    System.out.println(character[i].getCapNoun()+" has downed their drink.");
                    character[i].downDrink();
                }
            }
            if(character[i].getDrink()<=1){
                character[i].refill();
                System.out.println(character[i].getName()+" has had a refill.");
            }
            System.out.println("");
        }
        
        //Round 2

        System.out.println("Round 2");
        System.out.println("");
        
        for(int i = 0; i<character.length; i++){
            
            while(character[i].getNumVal(0) == 0 || character[i].getNumVal(0) == 12) {
                String temp = character[i].getCard(0);
                int cCard;
                cCard = 52;
                while (deck[cCard].getOwned()==true)
                    cCard = gen.nextInt(52);
                String owner = character[i].getName();
                deck[cCard].setOwner(owner);
                deck[character[i].getCardLoc(0)].replace(character[i].getName());
                character[i].setCard(deck[cCard], 0);
                System.out.println("As " + character[i].getName() + " has " + temp + ", a new card, " + character[i].getCard(0) + ", has been drawn to replace it.");
            }
            
            while(character[i].getNumVal(0) == character[i].getNumVal(1)) {
                int cCard;
                cCard = 52;
                while (deck[cCard].getOwned()==true)
                    cCard = gen.nextInt(52);
                String owner = character[i].getName();
                deck[cCard].setOwner(owner);
                deck[character[i].getCardLoc(1)].replace(character[i].getName());
                character[i].setCard(deck[cCard], 1);
            }
            
            System.out.println(character[i].getName()+" currently has "+character[i].getCard(0)+" .");
            
            boolean guess;//true = higher, false = lower
            int cCard = gen.nextInt(2);
            if(character[i].getValue(0).equals("2")||character[i].getValue(0).equals("3")||character[i].getValue(0).equals("4")||character[i].getValue(0).equals("5")){
                guess = true; //Guess higher
                System.out.println(character[i].getName() + " has guessed Higher.");
            }
            else if(character[i].getValue(0).equals("Ace")||character[i].getValue(0).equals("King")||character[i].getValue(0).equals("Queen")||character[i].getValue(0).equals("Jack")) {
                guess = false; //Guess lower
                System.out.println(character[i].getName() + " has guessed Lower.");
            }
            else if(cCard == 0) {
                guess = true; //Guess higher
                System.out.println(character[i].getName() + " has guessed Higher.");
            }
            else {
                guess = false; //Guess lower
                System.out.println(character[i].getName() + " has guessed Lower.");
            }
            
            if((character[i].getNumVal(1)>character[i].getNumVal(0))&&guess==true){
                System.out.println("They were right. It was " + character[i].getCard(1)+".");
                int l = i;
                while(l==i) {
                    l=gen.nextInt(8);
                }
                if(character[i].getNumVal(1)<9) {
                    System.out.println(character[l].getName() + " was given these drinks by " + character[i].getName() + ". The amount of drink "+character[l].getNoun()+" have left is " + character[l].takeDrink((character[i].getNumVal(0)+2)*10));
                }
                else if(character[i].getNumVal(1)==12) {
                    System.out.println(character[l].getName() + " was given these drinks by " + character[i].getName() + ". The amount of drink "+character[l].getNoun()+" have left is " + character[l].takeDrink(100));
                }
                else {
                    System.out.println(character[l].getName() + " was given this down by " + character[i].getName() + ". "+character[l].getCapNoun()+" have downed their drink.");
                    character[l].downDrink();
                }
                if(character[l].getDrink()<=0) {
                    System.out.println(character[l].getName() + " has had a refill.");
                    character[l].refill();
                }
            }
            else if((character[i].getNumVal(1)<character[i].getNumVal(0))&&guess==false){
                System.out.println("They were right. It was " + character[i].getCard(1)+".");
                int l = i;
                while(l==i) {
                    l=gen.nextInt(8);
                }
                if(character[i].getNumVal(1)<9) {
                    System.out.println(character[l].getName() + " was given these drinks by " + character[i].getName() + ". The amount of drink "+character[l].getNoun()+" have left is " + character[l].takeDrink((character[i].getNumVal(0)+2)*10));
                }
                else if(character[i].getNumVal(1)==12) {
                    System.out.println(character[l].getName() + " was given these drinks by " + character[i].getName() + ". The amount of drink "+character[l].getNoun()+"v have left is " + character[l].takeDrink(100));
                }
                else {
                    System.out.println(character[l].getName() + " was given this down by " + character[i].getName() + ". "+character[l].getCapNoun()+" have downed their drink.");
                    character[l].downDrink();
                }
                if(character[l].getDrink()<=0) {
                    System.out.println(character[l].getName() + " has had a refill.");
                    character[l].refill();
                }
            }
            else {
                System.out.println("They were wrong. It was actually " + character[i].getCard(1)+".");
                if(character[i].getNumVal(1)<9)
                    System.out.println("They take a drink. The amount of drink they have left is " + character[i].takeDrink((character[i].getNumVal(0)+2)*10));
                else if(character[i].getNumVal(1)==12)
                    System.out.println("They take a drink. The amount of drink they have left is " + character[i].takeDrink(100));
                else {
                    System.out.println("They have downed their drink.");
                    character[i].downDrink();
                }
            }
            
            if(character[i].getDrink()<=1){
                character[i].refill();
                System.out.println(character[i].getName()+" has had a refill.");
            }
            System.out.println("");
        }
        
        //Round 3

        System.out.println("Round 3");
        System.out.println("");
        
        for(int i = 0; i<character.length; i++){
            
            while((character[i].getNumVal(0) == 0 && character[i].getNumVal(12) == 12)||(character[i].getNumVal(0) == 12 && character[i].getNumVal(1) == 0)||(character[i].getNumVal(0)+1==character[i].getNumVal(1))||(character[i].getNumVal(0)-1==character[i].getNumVal(1))) {
                String temp = character[i].getCard(1);
                int cCard;
                cCard = 52;
                while (deck[cCard].getOwned()==true)
                    cCard = gen.nextInt(52);
                String owner = character[i].getName();
                deck[cCard].setOwner(owner);
                deck[character[i].getCardLoc(1)].replace(character[i].getName());
                character[i].setCard(deck[cCard], 1);
                System.out.println("As " + character[i].getName() + " has " + character[i].getCard(0) + " and " + temp + ", a new card, " + character[i].getCard(1) + ", has been drawn to replace it.");
            }
            
            System.out.println(character[i].getName()+" currently has "+character[i].getCard(0)+" and "+character[i].getCard(1)+".");
            
            boolean guess;//true = inside, false = outside
            int cCard = gen.nextInt(2);
            int diff;
            if(character[i].getNumVal(0)>character[i].getNumVal(1))
                diff = character[i].getNumVal(0)-character[i].getNumVal(1);
            else
                diff = character[i].getNumVal(1)-character[i].getNumVal(0);
            if(diff > 7) {
                guess = true; //Guess inside
                System.out.println(character[i].getName() + " has guessed Inside.");
            }
            else if(diff <5) {
                guess = false; //Guess outside
                System.out.println(character[i].getName() + " has guessed Outside.");
            }
            else if(cCard == 0) {
                guess = true; //Guess inside
                System.out.println(character[i].getName() + " has guessed Inside.");
            }
            else {
                guess = false; //Guess outside
                System.out.println(character[i].getName() + " has guessed Outside.");
            }
            
            if(((character[i].getNumVal(0)<character[i].getNumVal(2))&&(character[i].getNumVal(1)>character[i].getNumVal(2))||((character[i].getNumVal(1)<character[i].getNumVal(2))&&(character[i].getNumVal(0)>character[i].getNumVal(2))))&&guess==true){
                System.out.println("They were right. It was " + character[i].getCard(2)+".");
                int l = i;
                while(l==i) {
                    l=gen.nextInt(8);
                }
                if(character[i].getNumVal(2)<9) {
                    System.out.println(character[l].getName() + " was given these drinks by " + character[i].getName() + ". The amount of drink "+character[l].getNoun()+" have left is " + character[l].takeDrink((character[i].getNumVal(0)+2)*10));
                }
                else if(character[i].getNumVal(2)==12) {
                    System.out.println(character[l].getName() + " was given these drinks by " + character[i].getName() + ". The amount of drink "+character[l].getNoun()+" have left is " + character[l].takeDrink(100));
                }
                else {
                    System.out.println(character[2].getName() + " was given this down by " + character[i].getName() + ". "+character[l].getCapNoun()+" have downed their drink.");
                    character[l].downDrink();
                }
                if(character[l].getDrink()<=0) {
                    System.out.println(character[l].getName() + " has had a refill.");
                    character[l].refill();
                }
            }
            else if(((character[i].getNumVal(2)<character[i].getNumVal(0))||(character[i].getNumVal(2)>character[i].getNumVal(1))||((character[i].getNumVal(2)<character[i].getNumVal(1))||(character[i].getNumVal(2)>character[i].getNumVal(0))))&&guess==false){
                System.out.println("They were right. It was " + character[i].getCard(2)+".");
                int l = i;
                while(l==i) {
                    l=gen.nextInt(8);
                }
                if(character[i].getNumVal(2)<9) {
                    System.out.println(character[l].getName() + " was given these drinks by " + character[i].getName() + ". The amount of drink "+character[l].getNoun()+" have left is " + character[l].takeDrink((character[i].getNumVal(0)+2)*10));
                }
                else if(character[i].getNumVal(2)==12) {
                    System.out.println(character[l].getName() + " was given these drinks by " + character[i].getName() + ". The amount of drink "+character[l].getNoun()+" have left is " + character[l].takeDrink(100));
                }
                else {
                    System.out.println(character[l].getName() + " was given this down by " + character[i].getName() + ". "+character[l].getCapNoun()+" have downed their drink.");
                    character[l].downDrink();
                }
                if(character[l].getDrink()<=0) {
                    System.out.println(character[l].getName() + " has had a refill.");
                    character[l].refill();
                }
            }
            else {
                System.out.println("They were wrong. It was actually " + character[i].getCard(2)+".");
                if(character[i].getNumVal(2)<9)
                    System.out.println("They take a drink. The amount of drink they have left is " + character[i].takeDrink((character[i].getNumVal(0)+2)*10));
                else if(character[i].getNumVal(2)==12)
                    System.out.println("They take a drink. The amount of drink they have left is " + character[i].takeDrink(100));
                else {
                    System.out.println("They have downed their drink.");
                    character[i].downDrink();
                }
            }
            if(character[i].getDrink()<=1){
                character[i].refill();
                System.out.println(character[i].getName()+" has had a refill.");
            }
            System.out.println("");
        }
        
        //Round 4

        System.out.println("Round 4");
        System.out.println("");
        
        for(int i = 0; i<character.length; i++){

            System.out.println(character[i].getName()+" currently has "+character[i].getCard(0)+", "+character[i].getCard(1)+" and "+character[i].getCard(2)+".");
            
            int guess = 4;//0 = heart 1 = diamond 2 = club 3 = spade
            int cCard = gen.nextInt(4);
            if(character[i].getSuit(0).equals(character[i].getSuit(1))&&character[i].getSuit(0).equals(character[i].getSuit(2))) {
                if(character[i].getSuit(0).equals("Hearts"))
                    guess = 0;
                if(character[i].getSuit(0).equals("Diamonds"))
                    guess = 1;
                if(character[i].getSuit(0).equals("Clubs"))
                    guess = 2;
                if(character[i].getSuit(0).equals("Spades"))
                    guess = 3;
            }
            else if(cCard == 0) {
                guess = 0; //Guess heart
                System.out.println(character[i].getName() + " has guessed Heart.");
            }
            else if(cCard == 1) {
                guess = 1; //Guess diamond
                System.out.println(character[i].getName() + " has guessed Diamond.");
            }
            else if(cCard == 2) {
                guess = 2; //Guess club
                System.out.println(character[i].getName() + " has guessed Club.");
            }
            else {
                guess = 3; //Guess spade
                System.out.println(character[i].getName() + " has guessed Spade.");
            }
            
            if((guess == 0 && character[i].getSuit(3).equals("Hearts"))||(guess == 1 && character[i].getSuit(3).equals("Diamonds"))||(guess == 2 && character[i].getSuit(3).equals("Clubs"))||(guess == 3 && character[i].getSuit(3).equals("Spades"))) {
                System.out.println("They were right. It was " + character[i].getCard(3)+".");
                int l = i;
                while(l==i) {
                    l=gen.nextInt(8);
                }
                if(character[i].getNumVal(2)<9) {
                    System.out.println(character[l].getName() + " was given these drinks by " + character[i].getName() + ". The amount of drink "+character[l].getNoun()+" have left is " + character[l].takeDrink((character[i].getNumVal(0)+2)*10));
                }
                else if(character[i].getNumVal(2)==12) {
                    System.out.println(character[l].getName() + " was given these drinks by " + character[i].getName() + ". The amount of drink "+character[l].getNoun()+" have left is " + character[l].takeDrink(100));
                }
                else {
                    System.out.println(character[2].getName() + " was given this down by " + character[i].getName() + ". "+character[l].getCapNoun()+" have downed their drink.");
                    character[l].downDrink();
                }
                if(character[l].getDrink()<=0) {
                    System.out.println(character[l].getName() + " has had a refill.");
                    character[l].refill();
                }
            }
            else {
                System.out.println("They were wrong. It was actually " + character[i].getCard(3)+".");
                if(character[i].getNumVal(2)<9)
                    System.out.println("They take a drink. The amount of drink they have left is " + character[i].takeDrink((character[i].getNumVal(0)+2)*10));
                else if(character[i].getNumVal(2)==12)
                    System.out.println("They take a drink. The amount of drink they have left is " + character[i].takeDrink(100));
                else {
                    System.out.println("They have downed their drink.");
                    character[i].downDrink();
                }
            }
            if(character[i].getDrink()<=1){
                character[i].refill();
                System.out.println(character[i].getName()+" has had a refill.");
            }
            System.out.println("");
        }
        for(int i=0;i<8;i++){
            System.out.println(character[i].getName() + " is " + character[i].getDrunk() + " drunk, has drunk "+character[i].getAmountDrunk()+"ml and has " + character[i].getCard(0) + ", " + character[i].getCard(1) + ", " + character[i].getCard(2) + " and " + character[i].getCard(3));
        }
    }
}
