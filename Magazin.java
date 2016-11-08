// TODO: sa scurtez codul cu <link below>
// http://stackoverflow.com/questions/7428039/java-constructor-method-with-optional-parameters

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.io.*;
import java.util.DoubleSummaryStatistics;
import java.util.Scanner;

/*
-----------------------------------------------------------------------------------
field        |     0     |     1     |      2      |     3    |     4     |   5   |
-------------|-----------|-----------|-------------|----------|-----------|-------|
produs       |    cod    |  denumire |  cantitate  |   pret   |   owner   |  \0   |
-------------|-----------|-----------|-------------|----------|-----------|-------|
utilizator   |  username |    nume   |   prenume   |   email  |   parola  |  tip  |
-----------------------------------------------------------------------------------

 */


public class Magazin {
    private String dateUtilizatori = "dateUtilizatori.txt";
    private String dateProdus = "dateProdus.txt";
    private ArrayList<Utilizator> allUtil = new ArrayList<Utilizator>();
    private ArrayList<Produs> allProd = new ArrayList<Produs>();
    private String prodNameList="";
    private String codList="";
    private String usernameList="";
    private String emailList="";
    private TextColor culoare = new TextColor();
    private Utilizator loggedUser = new Utilizator();

    Magazin() {

    }

    private void importBazautil() {
        String line;

        // verificam daca avem baza de utilizatri facuta
        // daca nu, o facem
        try {
            FileReader fileReader = new FileReader(dateUtilizatori);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] each = line.split("\\s+");

                // get usernameList and emailList
                usernameList += each[0] + " ";
                emailList += each[3] + " ";

                // Add Utilizator to local shop
                Utilizator localUtil = new Utilizator();
                localUtil.setUsername(each[0]);
                localUtil.setNume(each[1]);
                localUtil.setPrenume(each[2]);
                localUtil.setEmail(each[3]);
                localUtil.setParola(each[4]);
                localUtil.setTip(Boolean.parseBoolean(each[5]));
                allUtil.add(localUtil);
            }

            bufferedReader.close();
            fileReader.close();

        } catch (IOException ex) {
            ex.fillInStackTrace();
        }
    }
    private void importBazaProd() {
        String line;

        // verificam daca avem baza de produse facuta
        // daca nu, o facem
        try {
            FileReader fileReader = new FileReader(dateProdus);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] each = line.split("\\s+");

                //get prodNameList and codList
                prodNameList += each[1] + " ";
                codList += each[0] + " ";

                //add Produs to locals shop
                Produs localProd = new Produs();
                localProd.setCodUnic(Integer.parseInt(each[0]));
                localProd.setDenumire(each[1]);
                localProd.setCantitate(Integer.parseInt(each[2]));
                localProd.setPret(Double.parseDouble(each[3]));
                localProd.setOwner(each[4]);
                allProd.add(localProd);
            }

            bufferedReader.close();
            fileReader.close();

        } catch (IOException ex) {
            ex.fillInStackTrace();
        }
    }

    private void creeazaCont(){
        Utilizator localUtil = new Utilizator();
        localUtil.create(usernameList,emailList);
        allUtil.add(localUtil);
        usernameList+= localUtil.getUsername();
        emailList+= localUtil.getEmail();

        String line;
        try{
            FileWriter fileWriter = new FileWriter(dateUtilizatori,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(localUtil.getUsername()+" ");
            bufferedWriter.write(localUtil.getNume()+ " ");
            bufferedWriter.write(localUtil.getPrenume() + " ");
            bufferedWriter.write(localUtil.getEmail() + " ");
            bufferedWriter.write(localUtil.getParola() + " ");
            bufferedWriter.write(Boolean.toString(localUtil.getTip()) + " ");
            bufferedWriter.newLine();

            bufferedWriter.close();
            fileWriter.close();

        }catch (Exception ex){
            ex.fillInStackTrace();
        }
    }

    private void loginScreen(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String username= scanner.nextLine();
        System.out.print("Password: ");
        String password= scanner.nextLine();
        if(checkLogin(username,password)){
            String line;
            try{
                FileReader fileReader = new FileReader(dateUtilizatori);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while((line = bufferedReader.readLine())!= null){
                    String[] each = line.split("\\s+");
                    if(each[0].equals(username)){
                        loggedUser.setUsername(each[0]);
                        loggedUser.setNume(each[1]);
                        loggedUser.setPrenume(each[2]);
                        loggedUser.setEmail(each[3]);
                        loggedUser.setParola(each[4]);
                        loggedUser.setTip(Boolean.parseBoolean(each[5]));
                        break;
                    }
                }
                fileReader.close();
                bufferedReader.close();
            }catch(Exception e1){
                e1.fillInStackTrace();
            }
            if(loggedUser.getTip()== false){
                System.out.println(culoare.setGREEN("Succes! Te-ai autentificat ca si cumparator"));
                buyerLoggedIn();
            }
            if(loggedUser.getTip()== true){
                System.out.println(culoare.setCYAN("Succes! Te-ai autentificat ca vanzator!"));
                sellerLoggedIn();
            }

            return;
        }
        System.out.println(culoare.setRED("Username si parola gresite."));
    }
    private boolean checkLogin(String username, String password){
        String line;
        try{
            FileReader fileReader = new FileReader(dateUtilizatori);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine())!=null){
                String[] each = line.split("\\s+");
                if(each[0].equals(username) && each[4].equals(password))
                    return true;
            }

            bufferedReader.close();
            fileReader.close();
        }catch (Exception e1){
            System.out.println(culoare.setRED("Niciun utilizator creat. Vei primi eroare ca username-ul si parola sunt" +
                    "gresite"));
        }
        return false;
    }

    private void updateDateUtilizatori(){
        try{
            FileWriter fileWriter = new FileWriter(dateUtilizatori);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Utilizator localUtil : allUtil){
                if(localUtil.getUsername().equals(loggedUser.getUsername()))
                {
                    bufferedWriter.write(loggedUser.getUsername()+" ");
                    bufferedWriter.write(loggedUser.getNume()+ " ");
                    bufferedWriter.write(loggedUser.getPrenume() + " ");
                    bufferedWriter.write(loggedUser.getEmail() + " ");
                    bufferedWriter.write(loggedUser.getParola() + " ");
                    bufferedWriter.write(Boolean.toString(loggedUser.getTip()) + " ");
                    bufferedWriter.newLine();
                    continue;
                }
                bufferedWriter.write(localUtil.getUsername()+" ");
                bufferedWriter.write(localUtil.getNume()+ " ");
                bufferedWriter.write(localUtil.getPrenume() + " ");
                bufferedWriter.write(localUtil.getEmail() + " ");
                bufferedWriter.write(localUtil.getParola() + " ");
                bufferedWriter.write(Boolean.toString(localUtil.getTip()) + " ");
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();

        }catch (Exception ex){
            ex.fillInStackTrace();
        }
    }
    private void changePass(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Parola veche: ");
        String parolaVeche = scanner.nextLine();
        if(!parolaVeche.equals(loggedUser.getParola())){
            System.out.print(culoare.setRED("Parola nu este corecta.\n"));
            return;
        }
        System.out.print("Parola noua: ");
        String parolaNoua = scanner.nextLine();
        System.out.print("Rescrie parola: ");
        if(!scanner.nextLine().equals(parolaNoua)){
            System.out.print(culoare.setRED("Cele doua parole nu coincid.\n"));
            return;
        }

        loggedUser.setParola(parolaNoua);
        updateDateUtilizatori();

        System.out.println(culoare.setGREEN("Parola actualizata cu succes!"));
    }

    private void cumparaProdus(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Codul produsului (integral): ");
        int codAles = scanner.nextInt();
        scanner.nextLine();
        for (int i =0 ; i<allProd.size(); i++){
            if(allProd.get(i).getCodUnic() == codAles){
                if(allProd.get(i).getCantitate()>0){
                    allProd.get(i).show();
                    System.out.println(culoare.setPURPLE("Esti sigur ca vrei sa cumperi produsul?"));
                    System.out.println("y/<any other input>");
                    System.out.print("> ");
                    String option = scanner.nextLine();
                    if(option.equals("y") || option.equals("Y")){
                        System.out.print("Cantitate: ");
                        try{
                            int cantitate = scanner.nextInt();
                            if(cantitate > allProd.get(i).getCantitate()) {
                                System.out.print(culoare.setRED("Pe stoc avem maxim " + allProd.get(i).getCantitate() + ", tu ai incercat" +
                                        "sa cumperi " + cantitate));
                                break;
                            }
                            allProd.get(i).setCantitate(allProd.get(i).getCantitate()-cantitate);
                            updateDateProduse();
                            System.out.println(culoare.setGREEN("Produs cumparat cu succes!"));
                        }catch (Exception e1){
                            System.out.println(culoare.setRED("Cantitate introdusa gresit."));
                        }
                    }
                    continue;
                }
                System.out.println(culoare.setYELLOW("Ne pare rau, dar produsul nu mai este in stoc."));
                break;
            }
        }
    }

    private void updateDateProduse() {
            try{
                FileWriter fileWriter = new FileWriter(dateProdus);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                for(Produs localProd : allProd){
                    bufferedWriter.write(localProd.getCodUnic()+" ");
                    bufferedWriter.write(localProd.getDenumire()+ " ");
                    bufferedWriter.write(Integer.toString(localProd.getCantitate()) + " ");
                    bufferedWriter.write(Double.toString(localProd.getPret()) + " ");
                    bufferedWriter.write(localProd.getOwner() + " ");
                    bufferedWriter.newLine();
                }

                bufferedWriter.close();
                fileWriter.close();

            }catch (Exception ex){
                ex.fillInStackTrace();
            }
    }

    private void buyerLoggedIn(){
        while (true){
            System.out.println("1. Vizualizare produse disponibile");
            System.out.print("2. Cauta produs\n");
            System.out.print("3. Cumpara produs\n");
            System.out.print("4. Schimba parola\n> ");
            Scanner scanner = new Scanner(System.in);
            try{
                int opt2 = scanner.nextInt();
                switch (opt2){
                    case 1:
                        vizProduseDisponibile();
                        break;
                    case 2:
                        cautaProdus();
                        break;
                    case 3:
                        cumparaProdus();
                        break;
                    case 4:
                        changePass();
                        break;
                }

            }catch (Exception e1){
                System.out.print(culoare.setRED("Alegere gresita. \n"));
            }
        }
    }

    private void cautaProdus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Dupa nume");
        System.out.println("2. Dupa cod");
        try{
            int opt3= scanner.nextInt();
            scanner.nextLine();
            if(opt3 == 1 ){
                System.out.print("Nume/ parte din nume produs: ");
                String numeCautat= scanner.nextLine();
                for(Produs localProd : allProd){
                    String localName = localProd.getDenumire();
                    localName=localName.toLowerCase();
                    numeCautat=numeCautat.toLowerCase();
                    if(localName.contains(numeCautat))
                        localProd.show();
                }
                return;
            }
            if(opt3 == 2){
                System.out.println("Cod unic/ parte din el: ");
                int codCautat = scanner.nextInt();
                for (Produs localProd: allProd){
                    if(Integer.toString(localProd.getCodUnic()).contains(Integer.toString(codCautat))){
                        localProd.show();
                        return;
                    }
                }
            }
            System.out.println(culoare.setRED("Wrong choice."));
        }catch (Exception e1){
            System.out.println(culoare.setRED("Wrong choice."));
        }
    }

    private void vizProduseDisponibile() {
        for (Produs localProd: allProd){
            localProd.show();
        }
    }

    private void vizProduseProprii(){
        System.out.println(culoare.setPURPLE("Produsele tale: "));
        for (Produs localProd : allProd){
            if(localProd.getOwner().equals(loggedUser.getUsername())){
                localProd.show();
            }
        }
    }

    private void sellerLoggedIn(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("1. Adauga produse");
            System.out.print("2. Vizualzieaza produsele proprii\n");
            System.out.print("3. Schimba parola\n> ");
            try{
                int option = scanner.nextInt();
                switch (option){
                    case 1:
                        adaugaProduse();
                        break;
                    case 2:
                        vizProduseProprii();
                        break;
                    case 3:
                        changePass();
                }
            }catch (Exception e1){
                System.out.println(culoare.setRED("Alegere gresita."));
            }
        }
    }

    private void adaugaProduse(){
        Produs localProd = new Produs();
        localProd.create(loggedUser.getUsername(),prodNameList,codList);
        allProd.add(localProd);
        prodNameList+= localProd.getDenumire();
        codList+= localProd.getCodUnic();

        String line;
        try{
            FileWriter fileWriter = new FileWriter(dateProdus,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(localProd.getCodUnic()+" ");
            bufferedWriter.write(localProd.getDenumire()+" ");
            bufferedWriter.write(localProd.getCantitate()+" ");
            bufferedWriter.write(localProd.getPret()+" ");
            bufferedWriter.write(localProd.getOwner()+" ");
            bufferedWriter.newLine();

            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e1){
            try{
                FileWriter fileWriter = new FileWriter(dateUtilizatori);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("");
                bufferedWriter.close();
            }catch (Exception e2){
                e2.fillInStackTrace();
            }
            e1.fillInStackTrace();
        }

    }

    void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        importBazautil();
        importBazaProd();
        while (true){
            System.out.print("1. Login\n2. Creeaza cont\n");
            try{
                int opt = scanner. nextInt();
                switch (opt){
                    case 1:
                        loginScreen();
                        continue;

                    case 2:
                        creeazaCont();
                        continue;

                    default:
                        System.out.print(culoare.setRED("Alegere gresita. Incearca din nou."));
                        continue;
                }

            }catch (Exception ex12){
                scanner.nextLine();
                System.out.println(culoare.setRED("Alegere gresita. Incearca din nou."));
                continue;
            }
        }

    }


}
