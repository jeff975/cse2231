import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true, "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    // TODO - add test cases for add, removeFirst,
    // isInInsertionMode

    //done - size, order, changeToExtractionMode

    @Test
    public final void changeToExtractionModeMultiple() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green", "green1",
                "green1", "green1");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false, "green",
                "green1", "green1", "green1");

        m.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    @Test
    public final void changeToExtractionModeOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false, "green");

        m.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    @Test
    public final void changeToExtractionModeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        m.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    @Test
    public final void orderEmptyTrue() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        assertEquals(mExpected.order(), m.order());
        assertEquals(mExpected, m);
    }

    @Test
    public final void orderOneTrue() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true, "green");

        assertEquals(mExpected.order(), m.order());
        assertEquals(mExpected, m);
    }

    @Test
    public final void orderMultipleTrue() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green", "green1",
                "green1", "green1");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true, "green",
                "green1", "green1", "green1");

        assertEquals(mExpected.order(), m.order());
        assertEquals(mExpected, m);
    }

    @Test
    public final void orderEmptyFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        assertEquals(mExpected.order(), m.order());
        assertEquals(mExpected, m);
    }

    @Test
    public final void orderOneFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false, "green");

        assertEquals(mExpected.order(), m.order());
        assertEquals(mExpected, m);
    }

    @Test
    public final void orderMultipleFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "green",
                "green1", "green1", "green1");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false, "green",
                "green1", "green1", "green1");

        assertEquals(mExpected.order(), m.order());
        assertEquals(mExpected, m);
    }

    @Test
    public final void sizeEmptyTrue() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        int t = m.size();

        assertEquals(t, 0);
        assertEquals(mExpected, m);
    }

    @Test
    public final void sizeOneTrue() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true, "green");

        int t = m.size();

        assertEquals(t, 1);
        assertEquals(mExpected, m);
    }

    @Test
    public final void sizeMultipleTrue() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green", "green1",
                "green1", "green1");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true, "green",
                "green1", "green1", "green1");

        int t = m.size();

        assertEquals(t, 4);
        assertEquals(mExpected, m);
    }

    @Test
    public final void sizeEmptyFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        int t = m.size();

        assertEquals(t, 0);
        assertEquals(mExpected, m);
    }

    @Test
    public final void sizeOneFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false, "green");

        int t = m.size();

        assertEquals(t, 1);
        assertEquals(mExpected, m);
    }

    @Test
    public final void sizeMultipleFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "green",
                "green1", "green1", "green1");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false, "green",
                "green1", "green1", "green1");

        int t = m.size();

        assertEquals(t, 4);
        assertEquals(mExpected, m);
    }

}
