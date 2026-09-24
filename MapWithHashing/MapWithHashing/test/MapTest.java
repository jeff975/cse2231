import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Brisy Villalobos and Joshua Anderson
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i])
                    : "" + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i])
                    : "" + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // Completed- add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size
     @Test
    public void testConstructorDefault() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.constructorTest();
        Map<String, String> ref = this.constructorRef();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ref.size(), m.size());
        assertEquals(ref, m);
    }

    @Test
    public void testConstructorWithSize() {
        /*
         * Set up variables
         */
        Map<String, String> m = new Map4<>(1007);
        Map<String, String> ref = this.constructorRef();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ref.size(), m.size());
        assertEquals(ref, m);

    }

    @Test
    public void testConstructorWithDifferentSize() {
        /*
         * Set up variables
         */
        Map<String, String> m1 = new Map4<>(2);
        Map<String, String> m2 = new Map4<>(10);
        Map<String, String> m3 = new Map4<>(100);
        Map<String, String> ref = this.constructorRef();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ref, m1);
        assertEquals(ref, m2);
        assertEquals(ref, m3);

    }


    @Test
    public final void testAddFromEmpty() {
        Map<String, String> m = this.constructorTest();
        Map<String, String> mExpected = this.createFromArgsRef("test", "value");

        m.add("test", "value");

        // Assert object equality
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAdd() {
        Map<String, String> m = this.createFromArgsTest("test", "value");
        Map<String, String> mExpected = this.createFromArgsRef("test", "value", "test1",
                "value1");

        m.add("test1", "value1");

        // Assert object equality
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddTwo() {
        Map<String, String> m = this.createFromArgsTest("test", "value");
        Map<String, String> mExpected = this.createFromArgsRef("test", "value", "test1",
                "value1", "test2", "value2");

        m.add("test1", "value1");
        m.add("test2", "value2");

        // Assert object equality
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveToEmpty() {
        Map<String, String> m = this.createFromArgsTest("test", "value", "test1",
                "value1");
        Map<String, String> mExpected = this.createFromArgsRef("test", "value", "test1",
                "value1");
    }

}
