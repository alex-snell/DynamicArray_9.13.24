


/**
 * A simple class to demonstrate dynamic behavior with arrays. Objects of this
 * class store strings in an array that grows to match the demand for storage.
 * 
 * The class is based on an underlying string array. Objects can be initialized
 * to any size; otherwise they'll be initialized to the default size. For
 * example,
 * 
 * DynamicArray da1 = new DynamicArray(10);
 * 
 * will have initially room for 10 strings, while
 * 
 * DynamicArray da2 = new DynamicArray();
 * 
 * will have initially room for 4 strings.
 */
public class DynamicArray {

    /** Default size for underlying array */
    private static final int DEFAULT_SIZE = 4;

    /** The underlying array for this class */
    private String[] foundation;

    
    /**
     * Full constructor. Initializes the underlying array to the specified size. The
     * size must be a positive, non zero value. Otherwise the constructor uses the
     * default size value.
     */
    public DynamicArray(int size) {
        // If size <= 0 use default -- this is a good time to demo ternary operator
        size = (size > 0) ? size : DEFAULT_SIZE;
        this.foundation = new String[size];
    } // full constructor

    /** Array-based constructor -- used for testing */
    public DynamicArray(String[] data) {
        this.foundation = data;
    } // array-based constructor

    /**
     * Default constructor
     */
    public DynamicArray() {
        this(DEFAULT_SIZE);
    } // default constructor
/*
 * contains NEEDS: String target
 * - safety check if target // this.foundation = null
 * else for every String in the index's of foundation if the string equals target, 
 * containTarget is true
 */
    public boolean contains(String target){
        boolean containTarget = false;
        /* Check for target null, with values in foundation.
         * -have a safety check because the value will always be false if target is null & foundation is not
         * inverse for first else if statement
         * -have a safety check because if foundation is null the length cannot be evaluated and therefore needs a check
         * -foundation being null & target not being null will always evaluate to false
         * 
         * although not tested for if foundation is null && target is null, containTarget will evaluate to true
         */
        if(target == null && this.foundation != null){
            containTarget = false;
        }else if (this.foundation == null && target != null) {
           containTarget = false;
        }else{
            for (String foundation1 : this.foundation) {
                if ((foundation1).equals(target)) {
                    containTarget = true;
                }
            }
        }  
       return containTarget;
    }
/* get NEEDS index
 * declare String val
 * test that index does not succeed length or is negative
 * else - val = the value in the foundation at [index] given
 * return the val String
 */
    public String get(int index){
        String val;
        if(index < 0 || index >= this.foundation.length){
            val = null;
        }else{
            val = this.foundation[index];
        }
        return val;
    }
    /* remove NEEDS [index]
     * declare string val;  
     * safety check index length like above
     * else - save val in string and make it null
     * RETURN val
     */
    public String remove(int index){
        String val;
        if(index >= this.foundation.length || index < 0){
            val = null;
        }else{
            val = this.foundation[index];
            this.foundation[index] = null;
        }
        return val;
    }
    /*same as above but does not return or save the value
     * delete NEEDS [index]
     * safety check index length like above
     * else - make this.foundation[index] null
     */
    public void delete (int index){
        if(index >= this.foundation.length || index < 0){
            //nothing?
        }else{
            this.foundation[index] = null;
        }
    }
    /* insert NEEDS(String str)
     * intilize boolean and int
     * safety check if foundation is null - resize
     * else - while boolean false iterate through array
     * if empty space in array found
     *  - boolean = true
     *     int = index
     * IF (no empty spaces)
     * resize and add str to end
     * ELSE
     * add str to empty space
     */
    public void insert (String str){
        boolean hasEmpty = false;
        int whereEmpty = -1;
        //safety check to amke sure code runs, a null foundation will not have a length
        if(this.foundation == null){
            resize();
        }else{
            int i =0;
            //test if boolean is false while iterating through an array
            while(hasEmpty == false && i < foundation.length){
                if(this.foundation[i] == null || this.foundation[i].equals("")){
                    whereEmpty = i;
                    hasEmpty = true;
                }
                i++;
            }
        }
        //test if whereEmpty was never  found by if its value remained -1, a value an index would never be
        if(whereEmpty == -1){
            resize();
            this.foundation[this.foundation.length-1] = str;
        }else{
            this.foundation[whereEmpty] = str;
        }
        
    }
    /* resize()
     * create new array with + 1 length
     * ITERATE through array to put old values into larger array
     * 
     * THEN
     * old array = new array.
     * so thay this.foundation is re-established with a new size
     */
    public void resize(){
        String array[] = new String[this.foundation.length+1];
        for(int i = 0; i < this.foundation.length; i++){
            array[i] = this.foundation[i];
        }
        this.foundation = array; //put array values and size into the constructor

    }

    
    /** Driver/test code */
    public static void main(String[] args) {
        final String PASS = "Pass";
        final String FAIL = "Fail";
        final String NON_EXISTING = "COBOL";
        // Test data
        String[] testData = {"Java", "Python", "C", "C++", "Fortran"};
        DynamicArray test = new DynamicArray(testData);
        DynamicArray tset = new DynamicArray(null);
       
        // Naive testing - I am ashamed to do this but I need 
        // to keep things simple for now.
        String testContainsNullTarget = (!test.contains(null)) ? PASS : FAIL;
        String testContainsEmptyData =  (!tset.contains("Java")) ? PASS : FAIL;
        String testContainsExisting = (test.contains(testData[1])) ? PASS : FAIL;
        String testContainsNonExisting = (!test.contains(NON_EXISTING)) ? PASS : FAIL;
        String testGetNegative = (test.get(-1) == null) ? PASS : FAIL;
        String testGet = (test.get(0).equals(testData[0])) ? PASS : FAIL;
        String testGetOutOfBounds = (test.get(testData.length+1)==null) ? PASS : FAIL;
        String testRemove = (testData[1].equals(test.remove(1))) ? PASS : FAIL;
        String testRemoveNull = (test.remove(1) == null) ? PASS : FAIL;
        String testRemoveOutOfBounds = (test.remove(testData.length+1) == null) ? PASS :FAIL;
        test.insert("test");
        test.insert("test #2");
        System.out.printf("\nTest for contains(null): ............... %s", testContainsNullTarget);
        System.out.printf("\nTest for contains on null foundation: .. %s", testContainsEmptyData);
        System.out.printf("\nTest for contains (existing): .......... %s", testContainsExisting);
        System.out.printf("\nTest for contains (non existing): ...... %s", testContainsNonExisting);
        System.out.printf("\nTest for get(-1): ...................... %s", testGetNegative);
        System.out.printf("\nTest for get(0): ....................... %s", testGet);
        System.out.printf("\nTest for get(out of bounds): ........... %s\n\n", testGetOutOfBounds);
        System.out.printf("\nTest for remove(1): .................... %s", testRemove);
        System.out.printf("\nTest for remove(null): ................. %s", testRemoveNull);
        System.out.printf("\nTest for remove(out of bounds): ........ %s\n\n", testRemoveOutOfBounds);
    } // method main


} // class DynamicArray