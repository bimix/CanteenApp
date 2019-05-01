package com.example.kibernetika.zibatcanteenapp2;

import java.io.Serializable;

/**
 * Created by kibernetika on 19.04.2017.
 */

public class Dish implements Serializable {
    private String Description,PictureUrl,Title;
    private int Alcohol, Carbohydrates, Energy, Id, Fat, Price, Protein, Weight;
    private float ratingStar;



    public Dish(String Description, String PictureUrl, String Title, int alcohol, int carbohydrates, int Alcohol, int Carbohydrates, int Fat, int Id){
        this.Alcohol = Alcohol;
        this.Carbohydrates=Carbohydrates;
        this.Description = Description;
        this.Energy = Energy;
        this.Id = Id;
        this.Title = Title;
        this.PictureUrl = PictureUrl;
        this.Price = Price;
        this.Protein= Protein;
        this.Weight = Weight;
        this.Fat =Fat;


    }




    public void setDescription (String Description) {this.Description = Description;}
    public void setPictureUrl (String PictureUrl) {this.PictureUrl = PictureUrl;}
    public void setTitle (String Title) {this.Title = Title;}
    public void setAlcohol (int Alcohol) {this.Alcohol = Alcohol;}
    public void setCarbohydrates (int Carbohydrates) {this.Carbohydrates = Carbohydrates;}
    public void setEnergy (int Energy) {this.Energy = Energy;}
    public void setId (int Id) {this.Id = Id;}
    public void setFat (int Fat) {this.Fat = Fat;}
    public void setPrice (int Price) {this.Price = Price;}
    public void setProtein (int Protein) {this.Protein = Protein;}
    public void setWeight (int Weight) {this.Weight = Weight;}


    public String getDescription(){return Description;}
    public String getPictureUrl(){return PictureUrl;}
    public String getTitle(){return Title;}
    public int getAlcohol(){return Alcohol;}
    public int getCarbohydrates(){return Carbohydrates;}
    public int getEnergy(){return Energy;}
    public int getId(){return Id;}
    public int getFat(){return Fat;}
    public int getPrice(){return Price;}
    public int getProtein(){return Protein;}
    public int getWeight(){return Weight;}




    public String toString () {return Title + "" + Description + "" ;}



}