### **Test Setup and Teardown**

- **`setUp`**: This function is defined but empty because no specific initialization is required before each test case
  in this context.
- **`tearDown`**: Similarly, this function is empty as no specific cleanup is necessary after each test case.

### **Test Cases**

Each test case evaluates the `sort_array` function under different conditions to ensure it behaves correctly across a
variety of input scenarios:

1. **`test_NullVector`**:
    - **Description**: Tests the case where the input array is empty (size = 0).
    - **Expected Outcome**: The function should return 0 indicating failure for an empty array.
    - **Explanation**:  Sorting an empty array should result in no change, and the function should return 0 indicating failure or no operation.

2. **`test_One`**:
    - **Description**: Tests the case where the array contains only one element.
    - **Expected Outcome**: The function should return 1, indicating success, and the array should remain unchanged.
    - **Explanation**: An array with one element is trivially sorted, and the function should return success (1) with no change in the array.

3. **`test_Zero`**:
    - **Description**: Tests the case where the array contains multiple elements, and the order is ascending.
    - **Expected Outcome**: The function should return 1, indicating success, and the array should be sorted in ascending order.
    - **Explanation**:  Sorting the array [10, 0, 1] in ascending order should result in [0, 1, 10].

4. **`test_Three`**:
    - **Description**: Tests the case with negative values, sorting in ascending order.
    - **Expected Outcome**: The function should return 1, indicating success, and the array should be sorted in ascending order.
    - **Explanation**:  Sorting the array [-1, -3, -2] in ascending order should result in [-3, -2, -1].

5. **`test_Five`**:
    - **Description**: Tests the case where the array contains duplicates, sorting in ascending order.
    - **Expected Outcome**: The function should return 1, indicating success, and the array should be sorted in ascending order.
    - **Explanation**:  Sorting the array [1, 1, 1, 1, 2] in ascending order should result in [1, 1, 1, 1, 2].

6. **`test_NullVectorD`**:
    - **Description**: Similar to test_NullVector but tests the case where the sorting is in descending order.
    - **Expected Outcome**: The function should return 0 for failure with an empty array.
    - **Explanation**: An empty array should return 0, indicating no sorting was done.

7. **`test_OneD`**:
    - **Description**:  Similar to test_One, but tests descending order sorting.
    - **Expected Outcome**:  The function should return 1, indicating success, and the array should remain unchanged.
    - **Explanation**: An array with one element in descending order should still return 1 as success, with no changes in the array.

8. **`test_ZeroD`**:

    - **Description**:  Tests the sorting of multiple elements in descending order.
    - **Expected Outcome**: The function should return 1, indicating success, and the array should be sorted in descending order.
    - **Explanation**: Sorting the array [10, 0, 1] in descending order should result in [10, 1, 0].
      

9. **`test_ThreeD`**:

    - **Description**:  Tests the sorting of an array with negative values in descending order.
    - **Expected Outcome**: The function should return 1, indicating success, and the array should be sorted in descending order.
    - **Explanation**: Sorting the array [-1, -3, -2] in descending order should result in [-1, -2, -3].

10. **`test_FiveD`**:

    - **Description**:  Tests the sorting of an array with duplicates in descending order.
    - **Expected Outcome**: The function should return 1, indicating success, and the array should be sorted in descending order.
    - **Explanation**: Sorting the array [1, 1, 1, 1, 2] in descending order should result in [2, 1, 1, 1, 1].

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