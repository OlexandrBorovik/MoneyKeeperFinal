package com.example.moneykeeper;

import java.util.ArrayList;

public class StaticLists {

    public static ArrayList<Account> accounts;
    public static ArrayList<Category> categories;

    public static ArrayList<Account> getAccounts() {
        return accounts;
    }

    public static void setAccounts(ArrayList<Account> accounts) {
        StaticLists.accounts = accounts;
    }

    public static ArrayList<Category> getCategories() {
        return categories;
    }

    public static void setCategories(ArrayList<Category> categories) {
        StaticLists.categories = categories;
    }
}
