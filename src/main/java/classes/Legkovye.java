package classes;

public class Legkovye {
    private String head;
    private String year;
    private String model;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Legkovye(String head, String year, String model) {
        this.head = head;
        this.year = year;
        this.model = model;
    }
}
