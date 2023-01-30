public class Item {
  private String name;
  private double price;

  public Item(String name, double price) {
    this.name = name;
    this.price = price;
  }

  public double getPrice() {
    return price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return name.equals(item.getName()) && price == item.getPrice();
  }
}
