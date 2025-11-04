package com.example.dummy.menu;

import java.util.Scanner;

public interface Menu {

    public void print();

    public Scanner getScanner();

    public int askInt(String label, int defaultValue);

    public String askString(String label);

    public boolean askBoolean(String label);
}
