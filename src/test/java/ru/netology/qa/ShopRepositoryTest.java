package ru.netology.qa;

import  org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShopRepositoryTest {

    @Test
    public void shouldRemoveExistingElement() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Молоко", 80);
        Product product2 = new Product(2, "Хлеб", 40);
        Product product3 = new Product(3, "Сыр", 300);

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        repo.removeById(2); // удаляем хлеб

        Product[] expected = {product1, product3};
        Product[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenRemovingNonExistent() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Книга", 500);
        Product product2 = new Product(2, "Ручка", 50);

        repo.add(product1);
        repo.add(product2);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> repo.removeById(999)
        );

        assertEquals("Element with id: 999 not found", exception.getMessage());
    }
}