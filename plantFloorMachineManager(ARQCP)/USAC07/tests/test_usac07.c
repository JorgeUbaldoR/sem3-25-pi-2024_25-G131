#include "../../unity_folder/unity.h"
#include "../include/asm.h"  

void setUp(void) {
}

void tearDown(void) {
}


void test_full_buffer(void) {
    int arr[] = {4,65,9,2,25,6};
    int lenght = 6;
    int* tail = arr + lenght;
    int * head = arr;

    int result = get_n_element(arr,lenght,tail,head);
    int expected_output = 6;
    
    TEST_ASSERT_EQUAL_INT(expected_output, result);

}

void test_not_full_buffer(void) {
    int arr[] = {4,65,9,2,25,6,0,0,0,0};
    int lenght = 10;
    int* tail = arr + 6;
    int * head = arr;

    int result = get_n_element(arr,lenght,tail,head);
    int expected_output = 6;
    
    TEST_ASSERT_EQUAL_INT(expected_output, result);

}


int main(void) {
    UNITY_BEGIN();
    RUN_TEST(test_full_buffer);
    RUN_TEST(test_not_full_buffer);
    
    return UNITY_END();
}
