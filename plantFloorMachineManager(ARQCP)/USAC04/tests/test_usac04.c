#include "../../unity_folder/unity.h"
#include "../include/asm.h"

void setUp(void) {
}

void tearDown(void) {
}

void test_null(void){
	int value = 0;
	char str[] = "";
	char cmd[20];
	
	int expected_output = 0;
	char expected_str[] = "";
	
	int output = format_command(str, value, cmd);
	
	TEST_ASSERT_EQUAL_INT(expected_output, output);
	TEST_ASSERT_EQUAL_STRING(expected_str, cmd);
}

void test_one(void){
	int value = 23;
	char str[] = " op ";
	char cmd[20];
	
	int expected_output = 1;
	char expected_str[] = "OP,1,0,1,1,1";
	
	int output = format_command(str, value, cmd);
	
	TEST_ASSERT_EQUAL_INT(expected_output, output);
	TEST_ASSERT_EQUAL_STRING(expected_str, cmd);
}

void test_Zero(void){
	int value = 8;
	char str[] = " on ";
	char cmd[20];
	
	int expected_output = 1;
	char expected_str[] = "ON,0,1,0,0,0";
	
	int output = format_command(str, value, cmd);
	
	TEST_ASSERT_EQUAL_INT(expected_output, output);
	TEST_ASSERT_EQUAL_STRING(expected_str, cmd);
}

void test_Three(void){
	int value = 17;
	char str[] = " ofF ";
	char cmd[20];
	
	int expected_output = 1;
	char expected_str[] = "OFF,1,0,0,0,1";
	
	int output = format_command(str, value, cmd);
	
	TEST_ASSERT_EQUAL_INT(expected_output, output);
	TEST_ASSERT_EQUAL_STRING(expected_str, cmd);
}

void test_Four(void){
	int value = 7;
	char str[] = "Off";
	char cmd[20];
	
	int expected_output = 1;
	char expected_str[] = "OFF,0,0,1,1,1";
	
	int output = format_command(str, value, cmd);
	
	TEST_ASSERT_EQUAL_INT(expected_output, output);
	TEST_ASSERT_EQUAL_STRING(expected_str, cmd);
}

void test_Five(void){
	int value = 15;
	char str[] = "oN      ";
	char cmd[20];
	
	int expected_output = 1;
	char expected_str[] = "ON,0,1,1,1,1";
	
	int output = format_command(str, value, cmd);
	
	TEST_ASSERT_EQUAL_INT(expected_output, output);
	TEST_ASSERT_EQUAL_STRING(expected_str, cmd);
}

void test_MinusOne(void){
	int value = -1;
	char str[] = " cmD ";
	char cmd[20];
	
	int expected_output = 0;
	char expected_str[] = "";
	
	int output = format_command(str, value, cmd);
	
	TEST_ASSERT_EQUAL_INT(expected_output, output);
	TEST_ASSERT_EQUAL_STRING(expected_str, cmd);
}

void test_SixtyFour(void){
	int value = 64;
	char str[] = " shdhsdh %444 sdjshd";
	char cmd[20];
	
	int expected_output = 0;
	char expected_str[] = "";
	
	int output = format_command(str, value, cmd);
	
	TEST_ASSERT_EQUAL_INT(expected_output, output);
	TEST_ASSERT_EQUAL_STRING(expected_str, cmd);
}

void test_Forty(void){
	int value = 40;
	char str[] = "40  adsads";
	char cmd[20];
	
	int expected_output = 0;
	char expected_str[] = "";
	
	int output = format_command(str, value, cmd);
	
	TEST_ASSERT_EQUAL_INT(expected_output, output);
	TEST_ASSERT_EQUAL_STRING(expected_str, cmd);
}

void test_Offy(void){
	int value = 0;
	char str[] = "Offy   ";
	char cmd[20];
	
	int expected_output = 0;
	char expected_str[] = "";
	
	int output = format_command(str, value, cmd);
	
	TEST_ASSERT_EQUAL_INT(expected_output, output);
	TEST_ASSERT_EQUAL_STRING(expected_str, cmd);
}

void test_Offy2(void){
	int value = 0;
	char str[] = "Off y  2   ";
	char cmd[20];
	
	int expected_output = 0;
	char expected_str[] = "";
	
	int output = format_command(str, value, cmd);
	
	TEST_ASSERT_EQUAL_INT(expected_output, output);
	TEST_ASSERT_EQUAL_STRING(expected_str, cmd);
}

void test_CMD(void){
	int value = 0;
	char str[] = "CmD   ";
	char cmd[20];
	
	int expected_output = 0;
	char expected_str[] = "";
	
	int output = format_command(str, value, cmd);
	
	TEST_ASSERT_EQUAL_INT(expected_output, output);
	TEST_ASSERT_EQUAL_STRING(expected_str, cmd);
}

int main(void) {
    UNITY_BEGIN();
    RUN_TEST(test_null);
    RUN_TEST(test_one);
    RUN_TEST(test_Zero);
    RUN_TEST(test_Three);
    RUN_TEST(test_Four);
    RUN_TEST(test_Five);
    RUN_TEST(test_MinusOne);
    RUN_TEST(test_SixtyFour);
    RUN_TEST(test_Forty);
    RUN_TEST(test_Offy);
    RUN_TEST(test_Offy2);
    RUN_TEST(test_CMD);
    return UNITY_END();
}
