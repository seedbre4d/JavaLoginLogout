import java.util.Scanner;

class Utilizator {

    private String username = "<NOT_SET_username>";
    private String nume = "<NOT_SET_nume>";
    private String prenume = "<NOT_SET_prenume>";
    private String email = "<NOT_SET_email>";
    private String parola = "<NOT_SET_parola>";
    private Boolean tip = false;  // false - cumparator ;; true - vanzator


    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getTip() {
        return tip;
    }

    public void setTip(Boolean tip) {
        this.tip = tip;
    }

    void create(String usernameList, String emailList) {

        //scanner
        Scanner scanner = new Scanner(System.in);
        //culoare
        TextColor culoare = new TextColor();

        // Nume
        while (true) {
            System.out.print("Nume: ");
            this.nume = scanner.nextLine();

            boolean hasNonOnlyALpha = nume.matches("^.*[^a-zA-Z].*$");
            if (hasNonOnlyALpha) {
                System.out.println(culoare.setRED("Trebuie sa contina doar caractere din alfabetul englezesc."));
                continue;
            }
            break;
        }


        //Prenume
        while (true) {
            System.out.print("Prenume: ");
            this.prenume = scanner.nextLine();

            boolean hasNonOnlyALpha = prenume.matches("^.*[^a-zA-Z].*$");
            if (hasNonOnlyALpha) {
                System.out.println(culoare.setRED("Trebuie sa contina doar caractere din alfabetul englezesc."));
                continue;
            }
            break;
        }


        //Username
        while (true) {
            System.out.print("Username: ");
            this.username = scanner.nextLine();
            boolean hasNonAlpha = username.matches("^.*[^a-zA-Z0-9].*$");
            if (hasNonAlpha) {
                System.out.println(culoare.setRED("Doar caractere alfanumerice acceptate (fara spatiu)."));
                continue;
            }
            if (usernameList.contains(this.username)) {
                System.out.println(culoare.setRED("Usename-ul deja exista."));
                continue;
            }
            break;
        }

        //Parola
        while (true) {
            System.out.print("Alege o parola pentru utilizatorul ales: ");
            this.parola = scanner.nextLine();

            if (parola.length() > 30 || parola.length() < 5) {
                System.out.println(culoare.setRED("Parola trebuie sa aiba minim 5 si maxim 30 de caractere."));
                continue;
            }

            System.out.print("Rescrie parola: ");
            String parolaRescrisa = scanner.nextLine();

            if (!parolaRescrisa.equals(parola)) {
                System.out.println(culoare.setRED("Cele doua parole nu se potrivesc."));
                continue;
            }
            break;
        }

        System.out.println(emailList);
        //Email
        while (true) {
            System.out.print("Scrie email-ul tau: ");
            this.email = scanner.nextLine();

            if (emailList.contains(this.email)) {
                System.out.println(culoare.setRED("Email-ul deja exista."));
                continue;
            }

            if (!this.email.contains("@")) {
                System.out.println(culoare.setRED("Email-ul nu are un format valid."));
                continue;
            }
            break;

        }


        //Tip
        while (true) {
            System.out.print("Esti cumparator sau vanzator?\n0. Vanzator\n1. Cumparator\n> ");
            int helper;
            try {
                helper= scanner.nextInt();
                if (helper == 0) {
                    this.tip = false;
                    break;
                }
                if (helper == 1) {
                    this.tip = true;
                    break;
                }
                    System.out.println(culoare.setRED("Alegere gresita."));
                } catch (Exception ex1){
                  System.out.println(culoare.setRED("Alegere gresita"));
                  scanner.next();
                }
        }
        System.out.println(culoare.setBLUE("Felicitari! Contul a fost creat cu succes!"));
    }

}
