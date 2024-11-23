### **Test Setup and Teardown**

- **`setUp`**: This function is defined but empty because no specific initialization is required before each test case
  in this context.
- **`tearDown`**: Similarly, this function is empty as no specific cleanup is necessary after each test case.

### **Test Cases**

Each test case evaluates the `get_number` function under different conditions to ensure it behaves correctly across a
variety of input scenarios:

Each test case evaluates the get_number function under different conditions to ensure it behaves correctly across a
variety of input scenarios:

1. **`test_Null`**:

- **Description**: Tests the case of an empty string (no characters).
- **Expected Outcome**: Should return 0 and the number 0 because there is no valid number to parse.
- **Explanation**: An empty string cannot be converted to a number, so the function should return 0 and set the number to 0.

2. **`test_One`**:

- **Description**: Tests the conversion of a string containing only the number “1”.
- **Expected Outcome**: Should return 1 and the number 1.
- **Explanation**: The string “1” is a valid number and should be parsed correctly.

3. **`test_Zero`**:

- *Description**: Tests a string containing only “0”, with extra spaces around it.
- **Expected Outcome**: Should return 1 and the number 0.
- **Explanation**: The function should ignore leading and trailing spaces and correctly parse the number “0”.

4. **`test_Three`**:

- **Description**: Tests the conversion of a string containing “333” with extra spaces around it.
- **Expected Outcome**: Tests the conversion of a string containing “333” with extra spaces around it.
- **Explanation**: The string “333” should be correctly parsed, even with spaces.

5. **`test_Four`**:

- **Description**: Tests the conversion of a string containing the number “4444”, with extra spaces.
- **Expected Outcome**: Should return 1 and the number 4444.
- **Explanation**: The string “4444” should be parsed correctly.

6. **`test_Five`**:

- **Description**: Tests the conversion of a string containing “55555”, with extra spaces around it.
- **Expected Outcome**: Should return 1 and the number 55555.
- **Explanation**: The string “55555” should be parsed correctly, with spaces ignored.

7. **`test_MinusOne`**:

- **Description**: Tests a string with the negative number “-1”.
- **Expected Outcome**: Should return 0, as negative numbers are not allowed.
- **Explanation**: The function should reject negative numbers and return 0 and -1 as the number.

8. **`test_SixtyFour`**:

- **Description**: Tests a string with mixed characters and the number “64”.
- **Expected Outcome**: Should return 0 because the string contains non-numeric characters.
- **Explanation**: The string contains invalid characters (letters and symbols), so the function should reject it and return 0.

9. **`test_Forty`**:

- **Description**:  Tests a string with “40” followed by non-numeric characters.
- **Expected Outcome**: Should return 0 because of the presence of non-numeric characters after the number.
- **Explanation**: The function should return 0 when a string contains numbers followed by invalid characters.

### **Main Function**

- **Description**: The main function initializes the Unity test framework, runs all defined test cases, and concludes
  the testing session with the results.
- **Flow**: The test results will indicate whether the `get_number` function passes all these conditions, providing
  feedback on its correctness.

### **Key Assumptions**

- The get_number function is expected to return 1 when a valid number is successfully parsed from the input string and return 0 if parsing fails (due to invalid characters, format issues, or empty input).
- Leading and trailing whitespace is ignored in the string input.
- The function only accepts positive integers without decimal points or negative signs. Negative values and decimals are considered invalid, so parsing should fail for such inputs.