package test;

import model.GroceryItem;
import model.GroceryList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroceryListTest {
    private GroceryList testGroceryList;
    private GroceryItem item1;
    private GroceryItem item2;
    private GroceryItem item3;

    @BeforeEach
    public void runBefore() {
        testGroceryList = new GroceryList();
        item1 = new GroceryItem("Carrots", 200);
        item2 = new GroceryItem("Peppers", 350);
        item3 = new GroceryItem("Bananas", 100);
    }

    @Test
    public void testConstructor() {
        assertNull(testGroceryList.getFirstItem());
        testGroceryList.addItem(item1);
        assertEquals(item1, testGroceryList.getFirstItem());
        assertEquals("Carrots", item1.getName());
        assertEquals(200, item1.getPrice());
        assertTrue(testGroceryList.containsItem(item1));
        assertFalse(testGroceryList.containsItem(item2));
    }

    @Test
    public void testAddSingleItem() {
        assertNull(testGroceryList.getFirstItem());
        testGroceryList.addItem(item1);
        assertEquals(item1, testGroceryList.getFirstItem());
    }

    @Test
    public void testAddMultipleItems() {
        testGroceryList.addItem(item1);
        testGroceryList.addItem(item2);
        assertTrue(testGroceryList.containsItem(item1));
        assertTrue(testGroceryList.containsItem(item2));

    }

    @Test
    public void testRemoveSingleItem() {
        GroceryList groceryList1 = new GroceryList();
        groceryList1.addItem(item1);
        groceryList1.addItem(item2);
        assertEquals(item1, groceryList1.getFirstItem());
        groceryList1.removeItem(item1.getName());
        assertEquals(item2, groceryList1.getFirstItem());
    }

    @Test
    public void testRemoveItemsAtAnyIndex() {
        GroceryList groceryList1 = new GroceryList();
        groceryList1.addItem(item1);
        groceryList1.addItem(item2);
        groceryList1.addItem(item3);
        groceryList1.removeItem(item2.getName());
        assertTrue(groceryList1.containsItem(item1));
        assertFalse(groceryList1.containsItem(item2));
        assertTrue(groceryList1.containsItem(item3));
    }

    @Test
    public void testRemoveMultipleItems() {
        GroceryList groceryList1 = new GroceryList();
        groceryList1.addItem(item1);
        groceryList1.addItem(item2);
        groceryList1.removeItem(item1.getName());
        groceryList1.removeItem(item2.getName());
        assertFalse(groceryList1.containsItem(item1));
        assertFalse(groceryList1.containsItem(item2));
    }

    @Test
    public void testContainsItem() {
        testGroceryList.addItem(item2);
        assertTrue(testGroceryList.containsItem(item2));
    }

    @Test
    public void testDoesNotContainItem() {
        assertFalse(testGroceryList.containsItem(item1));
        testGroceryList.addItem(item2);
        assertFalse(testGroceryList.containsItem(item3));
    }

    @Test
    public void testContainsMultipleItem() {
        testGroceryList.addItem(item1);
        testGroceryList.addItem(item2);
        assertTrue(testGroceryList.containsItem(item1));
        assertTrue(testGroceryList.containsItem(item2));
        assertFalse(testGroceryList.containsItem(item3));
    }

    @Test
    public void testGetTotalWithEmptyList() {
        GroceryList groceryList1 = new GroceryList();
        assertEquals(0, groceryList1.getTotal());
    }

    @Test
    public void testGetAllItems() {
        GroceryList groceryList1 = new GroceryList();
        groceryList1.addItem(item1);
        groceryList1.addItem(item2);
        groceryList1.addItem(item3);
        List<GroceryItem> list1 = new ArrayList<>();
        list1.add(item1);
        list1.add(item2);
        list1.add(item3);
        assertEquals(list1, groceryList1.getAllItems());
    }

    @Test
    public void testGetTotal() {
        GroceryList groceryList1 = new GroceryList();
        groceryList1.addItem(item1);
        groceryList1.addItem(item2);
        groceryList1.addItem(item3);
        GroceryList groceryList2 = new GroceryList();
        groceryList2.addItem(item2);
        assertEquals(650, groceryList1.getTotal());
        assertEquals(350, groceryList2.getTotal());
    }

}
