package BusinessLogic;

import Model.Product;
import DataAccess.ProductDAO;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The ProductBLL class provides business logic operations for the Product model.
 */
public class ProductBLL {

    private ProductDAO productDAO = new ProductDAO();

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product
     * @return the product with the specified ID
     * @throws NoSuchElementException if the product is not found
     */
    public Product findProductById(int id) {
        Product product = productDAO.findById(id);
        if (product == null) {
            JOptionPane.showMessageDialog(null, "Error: Product not found!");
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return product;
    }

    /**
     * Retrieves the field names of the Product model.
     *
     * @return an array of field names
     */
    public String[] getFieldNames() {
        String[] toReturn = productDAO.takeFieldNames();
        return toReturn;
    }

    /**
     * Retrieves a list of all products.
     *
     * @return a two-dimensional array representing the list of products
     */
    public String[][] getListOfProducts() {
        String[][] toReturn = productDAO.listOfObject(productDAO.findAll());
        return toReturn;
    }

    /**
     * Inserts a new product.
     *
     * @param product the product to insert
     * @return the inserted product
     */
    public Product insertProduct(Product product) {
        return productDAO.insert(product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param productID the ID of the product to delete
     */
    public void deleteProduct(int productID) {
        productDAO.delete(productID);
    }

    /**
     * Retrieves a list of all products.
     *
     * @return a list of all products
     */
    public List<Product> findAllProducts() {
        return productDAO.findAll();
    }

    /**
     * Updates a product.
     *
     * @param product the product to update
     * @return the updated product
     */
    public Product updateProduct(Product product) {
        return productDAO.update(product, product.getId());
    }
}
