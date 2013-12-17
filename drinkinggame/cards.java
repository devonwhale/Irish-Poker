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

public class cards {
    private String name;
    private String suit;
    private String value;
    private String owner;
    private int numValue;
    private boolean owned;
    
    public cards(int setSuit, int setValue){

        if (setSuit == 0) {
            suit = "Clubs";
        }
        else if (setSuit == 1) {
            suit = "Spades";
        }
        else if (setSuit == 2) {
            suit = "Hearts";
        }
        else if (setSuit == 3) {
            suit = "Diamonds";
        }
        else if (setSuit == 4) {
            suit = "Invalid";
        }
        
        if (setValue == 0) {
            value = "2";
            numValue = setValue;
        }
        else if (setValue == 1) {
            value = "3";
            numValue = setValue;
        }
        else if (setValue == 2) {
            value = "4";
            numValue = setValue;
        }
        else if (setValue == 3) {
            value = "5";
            numValue = setValue;
        }
        else if (setValue == 4) {
            value = "6";
            numValue = setValue;
        }
        else if (setValue == 5) {
            value = "7";
            numValue = setValue;
        }
        else if (setValue == 6) {
            value = "8";
            numValue = setValue;
        }
        else if (setValue == 7) {
            value = "9";
            numValue = setValue;
        }
        else if (setValue == 8) {
            value = "10";
            numValue = setValue;
        }
        else if (setValue == 9) {
            value = "Jack";
            numValue = setValue;
        }
        else if (setValue == 10) {
            value = "Queen";
            numValue = setValue;
        }
        else if (setValue == 11) {
            value = "King";
            numValue = setValue;
        }
        else if (setValue == 12) {
            value = "Ace";
            numValue = setValue;
        }
        else if (setValue == 13) {
            value = "Invalid";
            numValue = setValue;
        }
        
        name = "the " + value + " of " + suit;
        owner = "";
        owned = false;
    }
    
    public boolean setOwner(String newOwner) {
        owned = true;
        owner = newOwner;
        return owned;
    }
    
    public String getCard() {
        return name;
    }
    
    public String getSuit() {
        return suit;
    }
    
    public String getValue() {
        return value;
    }
    
    public String getOwner() {
        return owner;
    }
    
    public int getNumValue() {
        return numValue;
    }
    
    public boolean getOwned() {
        return owned;
    }  
    
    public void replace(String nm) {
        if(name.equals(nm)) {
            owner = "";
            owned = false;
        }
    }
}
