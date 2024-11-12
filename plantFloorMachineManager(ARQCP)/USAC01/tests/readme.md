### Documentation for `extract_data` Function Unit Tests

This document describes each unit test developed for the `extract_data` function, covering expected behavior across diverse input cases. The function parses a string formatted with tokens, units, and values, and extracts the unit and value corresponding to a specified token.

---

#### 1. `test_extract_data_with_valid_temp_token`
   - **Objective**: Validate extraction for the `TEMP` token, with lowercase input.
   - **Input**:
     - `str`: `"TEMP&unit:celsius&value:20#HUM&unit:percentage&value:80"`
     - `token`: `"temp"`
   - **Expected Output**:
     - Return value: `1`
     - Extracted unit: `"celsius"`
     - Extracted value: `20`

#### 2. `test_extract_data_with_valid_hum_token`
   - **Objective**: Validate extraction for the `HUM` token.
   - **Input**:
     - `str`: `"TEMP&unit:celsius&value:20#HUM&unit:percentage&value:80"`
     - `token`: `"HUM"`
   - **Expected Output**:
     - Return value: `1`
     - Extracted unit: `"percentage"`
     - Extracted value: `80`

#### 3. `test_extract_data_with_invalid_token`
   - **Objective**: Test extraction with a non-existent token (`"PRES"`).
   - **Input**:
     - `str`: `"TEMP&unit:celsius&value:20#HUM&unit:percentage&value:80"`
     - `token`: `"PRES"`
   - **Expected Output**:
     - Return value: `0` (indicating failure)
     - Unit: `""` (zeroed out)
     - Value: `0`

#### 4. `test_extract_data_with_no_value`
   - **Objective**: Validate function behavior when the value is missing.
   - **Input**:
     - `str`: `"TEMP&unit:celsius&value:#HUM&unit:percentage&value:80"`
     - `token`: `"TEMP"`
   - **Expected Output**:
     - Return value: `0` (indicating failure due to missing value)
     - Unit: `"celsius"`
     - Value: `0`

#### 5. `test_extract_data_with_invalid_format`
   - **Objective**: Ensure function handles invalid format gracefully.
   - **Input**:
     - `str`: `"TEMP unit celsius value 20"`
     - `token`: `"TEMP"`
   - **Expected Output**:
     - Return value: `0` (indicating failure due to invalid format)
     - Unit: `""`
     - Value: `0`

#### 6. `test_extract_data_with_mixed_case_token`
   - **Objective**: Confirm function performs case-insensitive token matching.
   - **Input**:
     - `str`: `"TEMP&unit:celsius&value:20#HUM&unit:percentage&value:80"`
     - `token`: `"tEmP"`
   - **Expected Output**:
     - Return value: `1`
     - Unit: `"celsius"`
     - Value: `20`

#### 7. `test_extract_data_with_extra_characters_in_string`
   - **Objective**: Test handling of extra characters after a valid value.
   - **Input**:
     - `str`: `"TEMP&unit:celsius&value:20extra#HUM&unit:percentage&value:80"`
     - `token`: `"TEMP"`
   - **Expected Output**:
     - Return value: `1`
     - Unit: `"celsius"`
     - Value: `20` (extra characters ignored)

#### 8. `test_extract_data_with_large_value`
   - **Objective**: Verify that the function can handle a large numeric value.
   - **Input**:
     - `str`: `"TEMP&unit:celsius&value:99999#HUM&unit:percentage&value:80"`
     - `token`: `"TEMP"`
   - **Expected Output**:
     - Return value: `1`
     - Unit: `"celsius"`
     - Value: `99999`

#### 9. `test_extract_data_with_no_unit`
   - **Objective**: Confirm behavior when a token is missing a unit.
   - **Input**:
     - `str`: `"TEMP&value:25#HUM&unit:percentage&value:80"`
     - `token`: `"TEMP"`
   - **Expected Output**:
     - Return value: `0` (indicating failure due to missing unit)
     - Unit: `""`
     - Value: `0`

#### 10. `test_extract_data_with_special_characters_in_unit`
   - **Objective**: Test handling of special characters in the unit.
   - **Input**:
     - `str`: `"TEMP&unit:%cel!sius&value:20#HUM&unit:percentage&value:80"`
     - `token`: `"TEMP"`
   - **Expected Output**:
     - Return value: `1`
     - Unit: `"%cel!sius"`
     - Value: `20`

#### 11. `test_extract_data_with_partial_match_in_string`
   - **Objective**: Ensure exact token matching without partial matches.
   - **Input**:
     - `str`: `"TEMPERATURE&unit:celsius&value:30#HUM&unit:percentage&value:80"`
     - `token`: `"TEMP"`
   - **Expected Output**:
     - Return value: `0` (indicating no exact match)
     - Unit: `""`
     - Value: `0`

#### 12. `test_extract_data_with_multiple_tokens_of_same_type`
   - **Objective**: Confirm that the function extracts the first instance of a repeated token.
   - **Input**:
     - `str`: `"TEMP&unit:celsius&value:20#TEMP&unit:kelvin&value:273"`
     - `token`: `"TEMP"`
   - **Expected Output**:
     - Return value: `1`
     - Unit: `"celsius"`
     - Value: `20` (first instance extracted)

#### 13. `test_extract_data_with_empty_string`
   - **Objective**: Test function’s response to an empty input string.
   - **Input**:
     - `str`: `""`
     - `token`: `"TEMP"`
   - **Expected Output**:
     - Return value: `0` (indicating failure due to empty input)
     - Unit: `""`
     - Value: `0`

---
