package com.example.emanagerapp.utils;

import com.example.emanagerapp.R;
import com.example.emanagerapp.models.Category;

import java.util.ArrayList;

public class Constants {
    public static String INCOME="INCOME";
    public static String EXPENSE="EXPENSE";

    public static ArrayList<Category> categories;
    public  static void setCategories(){
        categories=new ArrayList<>();
        categories.add(new Category("salary", R.drawable.salary,R.color.category1));
        categories.add(new Category("business",R.drawable.briefcase,R.color.category2));
        categories.add(new Category("InvestMent",R.drawable.investment,R.color.category3));
        categories.add(new Category("Rent",R.drawable.house,R.color.category4));
        categories.add(new Category("Loan",R.drawable.loan,R.color.category5));
        categories.add(new Category("Other",R.drawable.wallet,R.color.category6));
    }

    public static Category getCategoryDetails(String categoryName)
    {
        for (Category cat:categories) {
            if (cat.getCategoryName().equalsIgnoreCase(categoryName)){
                return cat;
            }
        }
        return null;
    }

    public static int getAccountsColor(String accountName)
    {

        switch (accountName)
        {
            case "Bank":
                return R.color.bank_color;

            case "Cash":
                return R.color.cash_color;
            case "Card":
                return R.color.card_color;
            default:

               return R.color.default_color;

        }

    }
}
