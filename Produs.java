import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

class Produs {
    private int codUnic;
    private String denumire;
    private int cantitate;
    private double pret;
    private String owner;

    public void setCodUnic(int codUnic){
        this.codUnic = codUnic;
    }

    public int getCodUnic() {
        return codUnic;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void show(){
        TextColor culoare = new TextColor();
        System.out.println("--------");
        System.out.println("COD UNIC: #"+culoare.setBLUE(Integer.toString(this.getCodUnic())));
        System.out.println("Nume: "+ culoare.setCYAN(this.getDenumire()));
        System.out.println("Cantitate: "+ culoare.setCYAN(Integer.toString(this.getCantitate())));
        System.out.println("Pret: "+ culoare.setCYAN(Double.toString(this.getPret())));
        System.out.println("Vandut de: "+culoare.setCYAN(this.getOwner()));
        System.out.println("--------");
    }

    void create(String owner, String denumireList, String codList) {

        //scanner
        Scanner scanner = new Scanner(System.in);
        TextColor culoare = new TextColor();

        //Cod random
        while (true) {
            this.codUnic = ThreadLocalRandom.current().nextInt(100, 999 + 1);
            if (codList.contains(Integer.toString(codUnic))) {
                continue;
            }
            break;
        }


        //Produs
        while (true) {
            System.out.print("Denumire produs (fara spatiu, introdu _ in loc): ");
            this.denumire = scanner.nextLine();
            if (denumireList.contains(denumire)) {
                System.out.println(culoare.setRED("Produsul deja exista."));
                continue;
            }
            if(denumire.contains(" ")){
                System.out.println(culoare.setRED("Introdu denumirea produsului fara spatiu. Foloseste _ in schimb."));
                continue;
            }
            break;
        }


        //Cantitate
        while (true) {
            System.out.print("Cantitate (int): ");
            try {
                this.cantitate = scanner.nextInt();
            } catch (Exception ex) {
                System.out.println(culoare.setRED("Cantitate introdusa incorect."));
                scanner.next();
                continue;
            }
            break;
        }


        //Cantitate
        while (true) {
            System.out.print("Pret (double: x.y): ");
            try {
                this.pret = scanner.nextDouble();
            } catch (Exception ex) {
                System.out.println(culoare.setRED("Pret introdus incorect."));
                scanner.next();
                continue;
            }
            break;
        }

        this.setOwner(owner);
        System.out.println(culoare.setBLUE("Felicitari! Produsul a fost introdus cu succes [cod unic "+Integer.toString(codUnic)+"] de catre " + this.owner));
    }

}