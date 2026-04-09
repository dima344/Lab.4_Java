package ua.edu.sumdu.j2se.pr4;

/**
 * Клас-обгортка для зберігання телефону та його кількості на складі.
 */
public class StoreItem {
    private Phone phone;
    private int quantity;

    public StoreItem(Phone phone, int quantity) {
        this.phone = phone;
        this.quantity = quantity;
    }

    public Phone getPhone() { return phone; }
    public void setPhone(Phone phone) { this.phone = phone; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return phone.toString() + " | 📦 Кількість: " + quantity + " шт.";
    }
}