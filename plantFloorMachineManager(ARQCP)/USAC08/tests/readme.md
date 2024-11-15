### **Test Setup and Teardown**

- **`setUp`**: This function is defined but empty because no specific initialization is required before each test case
  in this context.
- **`tearDown`**: Similarly, this function is empty as no specific cleanup is necessary after each test case.

### **Test Cases**

Each test case evaluates the `sort_array` function under different conditions to ensure it behaves correctly across a
variety of input scenarios:

1. **`test_ascending_order_positive_vec`**:
    - **Description**: Tests the sorting of an array with positive values in ascending order.
    - **Expected Outcome**: The array should be sorted as [2, 4, 6, 9, 25, 65], and the return value should be 1.
    - **Explanation**: This verifies that the function correctly sorts a typical positive integer array when order = 1 (
      ascending).

2. **`test_descending_order_positive_vec`**:
    - **Description**: Tests the sorting of an array with positive values in descending order.
    - **Expected Outcome**: The array should be sorted as [65, 25, 9, 6, 4, 2], and the return value should be 1.
    - **Explanation**: Confirms that the function sorts a positive integer array in descending order with order = 0 (
      descending).

3. **`test_ascending_order_negative_vec`**:
    - **Description**: Tests sorting an array with only negative values in ascending order.
    - **Expected Outcome**: The array should be sorted as [-65, -25, -9, -6, -4, -2], with a return value of 1.
    - **Explanation**: Ensures the function handles negative numbers properly in ascending order.

4. **`test_descending_order_negative_vec`**:
    - **Description**: Tests sorting an array with only negative values in descending order.
    - **Expected Outcome**: The sorted array should be [-2, -4, -6, -9, -25, -65], with a return value of 1.
    - **Explanation**: Confirms that the function correctly sorts an array with negative numbers in descending order.

5. **`test_descending_order_negative_and_positive_vec`**:
    - **Description**: Tests sorting an array with a mix of positive and negative values in descending order.
    - **Expected Outcome**: The array should be sorted as [25, 9, -2, -4, -6, -65], with a return value of 1.
    - **Explanation**: Validates sorting in descending order with mixed positive and negative values.

6. **`test_ascending_order_negative_and_positive_vec`**:
    - **Description**: Tests sorting an array with mixed negative and positive values in ascending order.
    - **Expected Outcome**: The array should be sorted as [-65, -6, -4, -2, 9, 25], with a return value of 1.
    - **Explanation**: Confirms proper sorting in ascending order for an array with mixed sign values.

7. **`test_ascending_order_empty_array`**:
   - **Description**: Tests sorting an empty array in ascending order.
   - **Expected Outcome**: The return value should be 0.
   - **Explanation**: Ensures that the function recognizes an empty array and returns 0, indicating no sorting is needed.

8. **`test_descending_order_empty_array`**:

   - **Description**: Tests sorting an empty array in descending order.
   - **Expected Outcome**: The return value should be 0.
   - **Explanation**: Ensures that the function recognizes an empty array and returns 0, indicating no sorting is needed.

9. **`test_single_element_array_ascending`**:

   - **Description**: Tests sorting an array with a single element in ascending order.
   - **Expected Outcome**: The array remains [42], and the return value is 1.
   - **Explanation**: Verifies that an array with one element is handled correctly and that the function returns 1.

10. **`test_single_element_array_descending`**:

    - **Description**: Tests sorting an array with a single element in descending order.
    - **Expected Outcome**: The array remains [42], and the return value is 1.
    - **Explanation**: Confirms that a single-element array is handled appropriately in descending order.

11. **`test_duplicate_elements_array_ascending`**:

    - **Description**: Tests sorting an array with duplicate elements in ascending order.
    - **Expected Outcome**: The array should be [3, 3, 3, 3, 3], with a return value of 1.
    - **Explanation**: Ensures the function correctly sorts an array with identical elements without modifying their order.

12. **`test_duplicate_elements_array_descending`**:

    - **Description**: Tests sorting an array with duplicate elements in descending order.
    - **Expected Outcome**: The array should be [3, 3, 3, 3, 3], with a return value of 1.
    - **Explanation**: Checks that the function handles duplicate elements correctly in descending order.

13. **`test_already_sorted_array_ascending`**:

    - **Description**: Tests sorting an array already sorted in ascending order.
    - **Expected Outcome**: The array remains [1, 2, 3, 4, 5], with a return value of 1.
    - **Explanation**: Confirms that the function recognizes an already sorted array in ascending order and doesn’t alter it
      unnecessarily.

14. **`test_already_sorted_array_descending`**:

    - **Description**: Tests sorting an array already sorted in descending order.
    - **Expected Outcome**: The array remains [5, 4, 3, 2, 1], with a return value of 1.
    - **Explanation**: Similar to the ascending test, it ensures that the function identifies a sorted array in descending
      order.

15. **`test_reverse_sorted_array_ascending`**:

    - **Description**: Tests sorting an array that is reverse-ordered (in descending order) to ascending order.
    - **Expected Outcome**: The sorted array should be [1, 2, 3, 4, 5], with a return value of 1.
    - **Explanation**: Confirms that the function can correctly sort a descending-ordered array into ascending order.

16. **`test_reverse_sorted_array_descending:`**:

    - **Description**: Tests sorting an array that is reverse-ordered (in ascending order) to descending order.
    - **Expected Outcome**: The sorted array should be [5, 4, 3, 2, 1], with a return value of 1.
    - **Explanation**: Ensures that the function can reverse a sorted ascending array into descending order.

### **Main Function**

- **Description**: The main function initializes the Unity test framework, runs all defined test cases, and concludes
  the testing session with the results.
- **Flow**: The test results will indicate whether the `sort_array` function passes all these conditions, providing
  feedback on its correctness.

### **Key Assumptions**

- The `sort_array` function is assumed to return `1` for success and `0` for failure.
- The function correctly handles various edge cases, such as empty arrays, negative values, arrays with all equal
  elements, etc.
- The function correctly sorts the array by ascending or descending order.