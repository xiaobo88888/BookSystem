package Module;

public class Book {
    private int id;
    private String title;
    private String author;
    private String introduce;
    private double price;
    private int stock;

    public Book(){

    }

    public Book(String title, String author, String introduce, double price, int stock) {
        this.title = title;
        this.author = author;
        this.introduce = introduce;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", introduce='" + introduce + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
