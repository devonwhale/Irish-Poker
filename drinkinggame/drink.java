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

public class drink {
    private int drinkAmount;
    private int drinkDefault;
    private String name;
    private double alcohol;
    
    public drink(String type) {
        if(type.equals("lag")) {
            drinkDefault = 250;
            name = "Lager";
            alcohol = 0.048;
        }
        else if(type.equals("dis")) {
            drinkDefault = 150;
            name = "Disaronno and Coke";
            alcohol = 0.093;
        }
        else if(type.equals("wine")) {
            drinkDefault = 100;
            name = "Wine";
            alcohol = 0.130;
        }
        else if(type.equals("vodka")) {
            drinkDefault = 140;
            name = "Vodka and coke";
            alcohol = 0.080;
        }
        else if(type.equals("mal")) {
            drinkDefault = 85;
            name = "Malibu and coke";
            alcohol = 0.210;
        }   
        else if(type.equals("whisk")) {
            drinkDefault = 85;
            name = "Whiskey";
            alcohol = 0.400;
        }
    }
    
    public int getAmount() {
        return drinkAmount;
    }
    
    public void setAmount(int nAmount) {
        drinkAmount = nAmount;
    }
    
    public void subtractAmount(int amount) {
        drinkAmount = drinkAmount - amount;
    }
    
    public void refill() {
        drinkAmount = drinkDefault;
    }
    
}
