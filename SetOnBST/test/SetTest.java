import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, remove, contains

    @Test
    public void sizeThree() {
        /*
         * Set up variables
         */
        Set<String> t1 = this.createFromArgsTest("b", "a", "c");
        Set<String> t2 = this.createFromArgsRef("b", "a", "c");
        /*
         * Call method under test
         */
        int size = t1.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(3, size);
        assertEquals(t2, t1);
    }

    @Test
    public void sizeZero() {
        /*
         * Set up variables
         */
        Set<String> t1 = this.createFromArgsTest();
        Set<String> t2 = this.createFromArgsRef();
        /*
         * Call method under test
         */
        int size = t1.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, size);
        assertEquals(t2, t1);
    }

    @Test
    public void removeAny() {
        /*
         * Set up variables
         */
        Set<String> t1 = this.createFromArgsTest("b");
        Set<String> t2 = this.createFromArgsRef("b");
        /*
         * Call method under test
         */
        String remove = t1.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("b", remove);
        assertEquals(t2, t1);
    }

    @Test
    public void addtwo() {
        /*
         * Set up variables
         */
        Set<String> t1 = this.createFromArgsTest("b", "a", "c");
        Set<String> t2 = this.createFromArgsRef("b", "a", "c");
        /*
         * Call method under test
         */
        t1.add("v");
        t1.add("g");
        t1.add("h");
        t2.add("v");
        t2.add("g");
        t2.add("h");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(t2, t1);
    }

    @Test
    public void addEmpty() {
        /*
         * Set up variables
         */
        Set<String> t1 = this.createFromArgsTest();
        Set<String> t2 = this.createFromArgsRef("a");
        /*
         * Call method under test
         */
        t1.add("a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(t2, t1);
    }
}
