package ru.netology.qa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShopRepositoryTest {

    @Test
    public void testRemoveExistingProduct() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Product A", 100);
        Product product2 = new Product(2, "Product B", 200);
        Product product3 = new Product(3, "Product C", 300);

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        repo.remove(2);

        Product[] expected = {product1, product3};
        assertArrayEquals(expected, repo.findAll());
    }

    @Test
    public void testRemoveNonExistingProductThrowsException() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Product A", 100);

        repo.add(product1);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> repo.remove(666));
        assertEquals("Element with id: 666 not found", exception.getMessage());

        Product[] expected = {product1};
        assertArrayEquals(expected, repo.findAll());
    }

    @Test
    public void testAddNewProduct() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Product A", 100);

        repo.add(product1);

        Product[] expected = {product1};
        assertArrayEquals(expected, repo.findAll());
    }

    @Test
    public void testAddDuplicateIdThrowsException() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Product A", 100);

        repo.add(product1);

        Product product2 = new Product(1, "Product B", 200);

        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class, () -> repo.add(product2));
        assertEquals("Element with id: 1 already exists", exception.getMessage());

        Product[] expected = {product1};
        assertArrayEquals(expected, repo.findAll());
    }
}