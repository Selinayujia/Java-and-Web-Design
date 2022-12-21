/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * hw1 To create a cookie receipe class and ingredient class
 * os: mac unix
 * @author selina
 */

public class hw1{
    public static void main(String[] args){
        CookieReceipe cookieReceipe = new CookieReceipe(10, 10);
        String step1 = "Preheat oven to 375 degrees F. Line a baking pan with parchment paper and set aside.";
        String step2 = "Mix flour and sugar";
        String step3 = "Beat in eggs ";
        String step4 = "Add 12 oz package of chocolate chips and mix well.";
        String step5 = "Bake in preheated oven for approximately 8-10 minutes. ";
        
        cookieReceipe.addSteps(step1);
        cookieReceipe.addSteps(step2);
        cookieReceipe.addSteps(step3);
        cookieReceipe.addSteps(step4);
        cookieReceipe.addSteps(step5);
        
        Ingrediant ing1 = new Ingrediant(0.5, "cup of sugar");
        Ingrediant ing2 = new Ingrediant(1, "tsp of baking soda");
        Ingrediant ing3 = new Ingrediant(2, "eggs");
        Ingrediant ing4 = new Ingrediant(3, "cups of flour");
        Ingrediant ing5 = new Ingrediant(2, "cups of chocolate chips");
        
        cookieReceipe.addIngredient(ing1);
        cookieReceipe.addIngredient(ing2);
        cookieReceipe.addIngredient(ing3);
        cookieReceipe.addIngredient(ing4);
        cookieReceipe.addIngredient(ing5);
        
        
        System.out.println(cookieReceipe);
        
    }
    
}

class Ingrediant {
    private double messurement;
    private String item;
    Ingrediant(double messure, String item_name){
        messurement = messure;
        item = item_name;
    }
    
    @Override
    public String toString(){
        // System.out.println(""+messurement+" "+item+"\n");
        return ""+messurement+" "+item+"\n";
        
    }
}
class CookieReceipe {
    private String[] steps;
    private int stepCounter = 0;
    private Ingrediant[] ingrediants;
    private int ingCounter = 0;
    
    CookieReceipe(int step_num, int ingrediant_num){
        steps = new String[step_num];
        ingrediants = new Ingrediant[ingrediant_num];
    }
    
    
    void addIngredient(Ingrediant ingrediant){
        if (ingCounter >= ingrediants.length){
            System.out.println("You have reached the maximum ingrediant number!");
        }
        else{
            ingrediants[ingCounter] = ingrediant;
            ingCounter += 1;
        }
    }
    
    public void addSteps(String step){
        if (stepCounter >= steps.length){
            System.out.println("You have reached the maximum step number!");
        }
        else{
            steps[stepCounter] = step;
            stepCounter += 1;
        }
    }
    
    @Override // override the obj class
    public String toString(){
        String res = "";
        for(Ingrediant ing : ingrediants){
            if(ing != null){
                res = res + ing.toString();
            }
        }
        res += "\n\n";
        
        for(String step : steps){
            if(step != null){
                res = res + step + "\n";
            }
            
        }
        
        return res;
    }
    
    
}
