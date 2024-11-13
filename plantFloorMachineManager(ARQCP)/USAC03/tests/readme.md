### **Test Setup and Teardown**

- **`setUp`**: This function is defined but empty because no specific initialization is required before each test case
  in this context.
- **`tearDown`**: Similarly, this function is empty as no specific cleanup is necessary after each test case.

### **Test Cases**

Each test case evaluates the `get_number` function under different conditions to ensure it behaves correctly across a
variety of input scenarios:

1. **`test_trimmed_str_only_numbers`**:
    - **Description**: 
    - **Expected Outcome**: 
    - **Explanation**: 



Each test case evaluates the get_number function under different conditions to ensure it behaves correctly across a variety of input scenarios:
1.	**`test_trimmed_str_only_numbers`**:
 -  **Description**: Tests if the function correctly parses a trimmed string containing only numeric characters.
 - **Expected Outcome**: Returns 1, with value set to 89.
 - **Explanation**: The input string “89” has no extra whitespace or special characters, so the function should interpret it as a valid number and set value to 89.

2.	**`test_not_trimmed_str_only_numbers`**:
 - **Description**: Tests if the function can parse a number surrounded by whitespace.
 - **Expected Outcome**: Returns 1, with value set to 89.
 - **Explanation**: The function should ignore the whitespace around the number and parse it as 89.

3.	**`test_trimmed_separated_str_only_numbers`**:
- *Description**: Tests if the function can handle separated numeric characters in a trimmed string.
- **Expected Outcome**: Returns 1, with value set to 8992.
- **Explanation**: The function should combine separated numeric characters “8 9 9 2” into a single integer, resulting in 8992.

4.	**`test_not_trimmed_separated_str_only_numbers`**:
-	**Description**: Tests if the function correctly parses separated numeric characters with surrounding whitespace.
-	**Expected Outcome**: Returns 1, with value set to 8992.
-	**Explanation**: The function should ignore surrounding whitespace and concatenate the numeric characters to form 8992.

5.	**`test_invalid_str_with_numbers`**:
-   **Description**: Tests if the function rejects a string with invalid characters between numbers.
-	**Expected Outcome**: Returns 0.
-	**Explanation**: The input “8–9” contains invalid characters (--), so the function should not recognize it as a valid integer.

6.	**`test_invalid_str_without_numbers`**:
-	**Description**: Tests if the function rejects a string that lacks numeric characters entirely.
-	**Expected Outcome**: Returns 0.
-	**Explanation**: The input “####–##” has no numeric characters, so it should be deemed invalid.

7.	**`test_empty_str`**:
- **Description**: Tests if the function can handle an empty string.
- **Expected Outcome**: Returns 0.
- **Explanation**: An empty string cannot be parsed as a number, so the function should return 0.

8.	**`test_null_str`**:
-	**Description**: Tests if the function can handle a null or zero-length string.
-	**Expected Outcome**: Returns 0.
-   **Explanation**: A null or zero-length string is considered invalid, so the function should return 0.

9.	**`test_neg_value_str`**:
-	**Description**: Tests if the function can handle a negative number.
-	**Expected Outcome**: Returns 0.
-	**Explanation**: The function does not support negative values, so it should return 0 for an input like “-89”.

10.	**`test_single_value_str:`**
-	**Description**: Tests if the function correctly parses a single-digit number surrounded by whitespace.
-	**Expected Outcome**: Returns 1, with value set to 8.
-	**Explanation**: The function should ignore the surrounding whitespace and interpret “8” as the integer 8.

11.	**`test_decimal_value_str:`**
-	**Description**: Tests if the function rejects strings containing decimal values.
-	**Expected Outcome**: Returns 0.
-	**Explanation**: Decimal points are not valid in the expected input format, so the function should return 0 for “8.9”.

12.	**`test_mixed_valid_and_invalid_values_str:`**
-	**Description**: Tests if the function rejects strings that mix numeric and invalid characters.
-	**Expected Outcome**: Returns 0.
-	**Explanation**: A string like “8a9” includes letters and thus should be considered invalid, resulting in a return value of 0.


### **Main Function**

- **Description**: The main function initializes the Unity test framework, runs all defined test cases, and concludes
  the testing session with the results.
- **Flow**: The test results will indicate whether the `get_number` function passes all these conditions, providing
  feedback on its correctness.

### **Key Assumptions**

- The get_number function returns 1 if the input string is successfully parsed into a number and stored in the provided value variable, and returns 0 if parsing fails due to invalid characters, format issues, or empty/null input.
- Leading and trailing whitespace is ignored, allowing the function to interpret a valid number regardless of surrounding spaces.
- Non-numeric characters (e.g., letters, symbols, punctuation) within or surrounding numeric characters in the input string invalidate parsing, and the function should return 0.
- The function only accepts positive integers without decimal points or negative signs. Negative values and decimal formats are considered invalid, and parsing will fail if encountered.
- When parsing strings with separated numbers (like “8 9 9 2”), the function is expected to concatenate the numbers into a single integer, e.g., “8992”.