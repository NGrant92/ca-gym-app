package controllers;

import models.*;
import static utils.ScannerInput.*;
import static utils.Analytics.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Niall on 24/04/2017.
 */
public class GymApi {

    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers;


    public String listBySpecificBMICategory(String category) {
        String listBMI = "";
        category = category.toUpperCase();
        for(Member member : members){
            if(member.determineBMICategory().contains(category)) {
                listBMI = listBMI + member.toString() + "\n";
            }
        }
        if(members.size() == 0) {
            return "There are no members in the gym";
        }
        else if(members.size() > 0 && listBMI.equals("")) {
            return "There are no members in the gym in this BMI category";
        }
        else {
            return listBMI;
        }
    }
}
