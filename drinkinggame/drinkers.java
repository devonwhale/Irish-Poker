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

public class drinkers {
    private String name;
    private String pNoun;
    private String pCNoun;
    private double resistance;
    private double sipAmount;
    private int drunk;
    private int amountDrunk;
    private drink userDrink;
    private cards[] ownedCards;
    
    public drinkers(String newName, double newRes, double newSip, String gen, String typeDrink){
        name = newName;
        resistance = newRes;
        sipAmount = newSip;
        userDrink = new drink(typeDrink);
        drunk = 0;
        ownedCards = new cards[4];
        if(gen.equals("m")) {
            pNoun = "he";
            pCNoun = "He";
        }
        else {
            pNoun = "she";
            pCNoun = "She";
        }
    }
    
    public void setCard(cards nCard, int pos) {
        ownedCards[pos] = nCard;
    }
    
    public String getCard(int pos) {
        return ownedCards[pos].getCard();
    }
    
    public String getSuit(int pos) {
        return ownedCards[pos].getSuit();
    }
    
    public String getValue(int pos) {
        return ownedCards[pos].getValue();
    }
    
    public int getNumVal(int pos) {
        return ownedCards[pos].getNumValue();
    }
    
    public int getCardLoc(int pos) {
        int location = ownedCards[pos].getNumValue();
        
        switch (getSuit(pos)) {
            case "Clubs":
                location = location * 1;
                break;
            case "Spades":
                location = location * 2;
                break;
            case "Hearts":
                location = location * 3;
                break;
            case "Diamonds":
                location = location * 4;
                break;
            default:
                break;
        }
        return location;
    }
    
    public String getCapNoun() {
        return pCNoun;
    }
    
    public String getNoun() {
        return pNoun;
    }
    
    public int getAmountDrunk() {
        return amountDrunk;
    }
        
    public int getDrunk() {
        return drunk;
    }
    
    public int getDrink() {
        return userDrink.getAmount();
    }
        
    public String getName() {
        return name;
    }
    
    public double getResistance() {
        return resistance;
    }
    
    public int takeDrink(int volume) {
        if(volume > userDrink.getAmount()) {
            int temp = userDrink.getAmount();
            userDrink.setAmount(0);
            drunk = drunk + (int)((temp/10)*resistance);
            amountDrunk = amountDrunk+temp;
        }
        else {
            int nAmount = (int)(volume*sipAmount);
            userDrink.subtractAmount(nAmount);
            drunk = drunk + (int)((volume/10)*resistance);
            amountDrunk = amountDrunk+(int)(volume*sipAmount);
        }
        return userDrink.getAmount();
    }
    
    public void refill() {
        userDrink.refill();
    }
    
    public void downDrink() {
        drunk = drunk + (int)((userDrink.getAmount()/10)*resistance);
        refill();
    }
}

