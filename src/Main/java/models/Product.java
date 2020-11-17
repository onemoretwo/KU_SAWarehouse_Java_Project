package Main.java.models;

public class Product {
    private String id;
    private int category_id;
    private String name;
    private int quantity;
    private String unit;
    private double price;
    private int saftyStock;
    private String status;
    private String imgName;

    public Product(String id, int category_id, String name, int quantity, String unit, double price, int saftyStock, String status, String imgName) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.saftyStock = saftyStock;
        this.status = status;
        this.imgName = imgName;
    }

    public String getId() {
        return id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public double getPrice() {
        return price;
    }

    public int getSaftyStock() {
        return saftyStock;
    }

    public int getStatus() {
        if (status.equals("สินค้าหมด")) return 1;
        else if (status.equals("เหลือน้อย")) return 2;
        else if (status.equals("มีสินค้าอยู่")) return 3;
        return 4;
    }

    public String getImgName() {
        return imgName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", category_id=" + category_id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", saftyStock=" + saftyStock +
                ", status='" + status + '\'' +
                ", imgName='" + imgName + '\'' +
                '}';
    }
}