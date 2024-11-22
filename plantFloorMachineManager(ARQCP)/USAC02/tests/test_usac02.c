#include "../../unity_folder/unity.h"
#include "../include/asm.h"  

void setUp(void) {
    // Configuração antes de cada teste, se necessário
}

void tearDown(void) {
    // Limpeza após cada teste, se necessário
}

// Teste com valor máximo permitido
void test_get_number_binary_max_value(void) {
    char bits[5];
    int value = 31;
    int res = get_number_binary(value, bits);
    char expected[] = {1,1,1,1,1};

    TEST_ASSERT_EQUAL(1, res);
    TEST_ASSERT_EQUAL_CHAR_ARRAY(expected, bits, 5);
    
}

// Teste com valor mínimo permitido
void test_get_number_binary_min_value(void) {
    char bits[5];
    int value = 0;
    int res = get_number_binary(value, bits);
    char expected[] ={0,0,0,0,0};

    TEST_ASSERT_EQUAL(1, res);
    TEST_ASSERT_EQUAL_CHAR_ARRAY(expected, bits, 5);
}

// Teste com valor intermediário
void test_get_number_binary_mid_value(void) {
    char bits[5];
    int value = 15;
    int res = get_number_binary(value, bits);
    char expected[] ={1,1,1,1,0};

    TEST_ASSERT_EQUAL(1, res);
    TEST_ASSERT_EQUAL_CHAR_ARRAY(expected, bits, 5);
}

// Teste com valor arbitrário válido
void test_get_number_binary_arbitrary_value(void) {
    char bits[5];
    int value = 26;
    int res = get_number_binary(value, bits);
    char expected[] = {0,1,0,1,1};

    TEST_ASSERT_EQUAL(1, res);
    TEST_ASSERT_EQUAL_CHAR_ARRAY(expected, bits, 5);
}

// Teste com valor fora do limite superior
void test_get_number_binary_out_of_range_high(void) {
    char bits[5];
    int value = 32;
    int res = get_number_binary(value, bits);

    TEST_ASSERT_EQUAL(0, res);
}

// Teste com valor fora do limite inferior
void test_get_number_binary_out_of_range_low(void) {
    char bits[5];
    int value = -1;
    int res = get_number_binary(value, bits);

    TEST_ASSERT_EQUAL(0, res);
}

// Teste com valor de um bit ativo
void test_get_number_binary_one_bit_active(void) {
    char bits[5];
    int value = 1;
    int res = get_number_binary(value, bits);
    char expected[] ={1,0,0,0,0};

    TEST_ASSERT_EQUAL(1, res);
    TEST_ASSERT_EQUAL_CHAR_ARRAY(expected, bits, 5);
}

// Teste com número com dois bits ativos
void test_get_number_binary_two_bits_active(void) {
    char bits[5];
    int value = 3;  
    int res = get_number_binary(value, bits);
        char expected[] ={1,1,0,0,0};


    TEST_ASSERT_EQUAL(1, res);
    TEST_ASSERT_EQUAL_CHAR_ARRAY(expected, bits, 5);
}

// Teste para verificar consistência com entrada de limite
void test_get_number_binary_edge_values(void) {
    char bits[5];
    int value = 30;  
    int res = get_number_binary(value, bits);
    char expected[] ={0,1,1,1,1};

    TEST_ASSERT_EQUAL(1, res);
    TEST_ASSERT_EQUAL_CHAR_ARRAY(expected, bits, 5);
}

// Teste para valores consecutivos (consistência de saída)
void test_get_number_binary_consecutive_values(void) {
    char bits[5];
    int res;

    for (int value = 0; value <= 31; ++value) {
        res = get_number_binary(value, bits);
        TEST_ASSERT_EQUAL(1, res);
    }

    res = get_number_binary(32, bits);
    TEST_ASSERT_EQUAL(0, res);

    res = get_number_binary(-1, bits);
    TEST_ASSERT_EQUAL(0, res);
}


// Teste com ponteiro nulo para array de bits
void test_get_number_binary_null_pointer(void) {
    int value = 15; // Valor válido
    int res = get_number_binary(value, NULL);

    TEST_ASSERT_EQUAL(0, res); // Função deve falhar ao receber ponteiro nulo
}

// Teste com valor muito grande (overflow de inteiro)
void test_get_number_binary_large_value(void) {
    char bits[5];
    int value = 1000000; // Valor extremamente grande
    int res = get_number_binary(value, bits);

    TEST_ASSERT_EQUAL(0, res); // Função deve retornar erro
}

// Teste com valor próximo ao limite inferior de um inteiro (INT_MIN)
void test_get_number_binary_int_min(void) {
    char bits[5];
    int value = INT_MIN; // Limite inferior de int
    int res = get_number_binary(value, bits);

    TEST_ASSERT_EQUAL(0, res); // Função deve retornar erro
}

// Teste com valor próximo ao limite superior de um inteiro (INT_MAX)
void test_get_number_binary_int_max(void) {
    char bits[5];
    int value = INT_MAX; // Limite superior de int
    int res = get_number_binary(value, bits);

    TEST_ASSERT_EQUAL(0, res); // Função deve retornar erro
}


// Teste com array de tamanho maior que 5
void test_get_number_binary_large_array(void) {
    char bits[10]; // Array maior que o necessário
    int value = 15; // Valor válido
    int res = get_number_binary(value, bits);
    char expected[] = {1,1,1,1,0};

    TEST_ASSERT_EQUAL(1, res); // Função deve funcionar corretamente
    TEST_ASSERT_EQUAL_CHAR_ARRAY(expected, bits, 5);
}



// Teste fornecidos para o Sprint 2
void test_null(void) {
    char bits[5]; 
    int value = -1; 
    int res = get_number_binary(value, bits);

    TEST_ASSERT_EQUAL(0, res); 
}

void test_One(void) {
    char bits[5]; 
    int value = 1; 
    int res = get_number_binary(value, bits);
    char expected[] = {1,0,0,0,0};

    TEST_ASSERT_EQUAL(1, res); 
    TEST_ASSERT_EQUAL_CHAR_ARRAY(expected, bits, 5);
}

 
void test_two(void) {
    char bits[5]; 
    int value = 0; 
    int res = get_number_binary(value, bits);
    char expected[] = {0,0,0,0,0};

    TEST_ASSERT_EQUAL(1, res); 
    TEST_ASSERT_EQUAL_CHAR_ARRAY(expected, bits, 5);
} 
  
void test_three(void) {
    char bits[5]; 
    int value = 3; 
    int res = get_number_binary(value, bits);
    char expected[] = {1,1,0,0,0};

    TEST_ASSERT_EQUAL(1, res); 
    TEST_ASSERT_EQUAL_CHAR_ARRAY(expected, bits, 5);
} 

void test_four(void) {
    char bits[5]; 
    int value = 4; 
    int res = get_number_binary(value, bits);
    char expected[] = {0,0,1,0,0};

    TEST_ASSERT_EQUAL(1, res); 
    TEST_ASSERT_EQUAL_CHAR_ARRAY(expected, bits, 5);
} 


void test_Five(void) {
    char bits[5]; 
    int value = 5; 
    int res = get_number_binary(value, bits);
    char expected[] = {1,0,1,0,0};

    TEST_ASSERT_EQUAL(1, res); 
    TEST_ASSERT_EQUAL_CHAR_ARRAY(expected, bits, 5);
} 


void test_MinusOne(void) {
    char bits[5]; 
    int value = -1; 
    int res = get_number_binary(value, bits);

    TEST_ASSERT_EQUAL(0, res); 
} 

void test_SixtyFour(void) {
    char bits[5]; 
    int value = 64; 
    int res = get_number_binary(value, bits);

    TEST_ASSERT_EQUAL(0, res); 
} 

void test_Forty(void) {
    char bits[5]; 
    int value = 40; 
    int res = get_number_binary(value, bits);

    TEST_ASSERT_EQUAL(0, res); 
} 



int main(void) {
    UNITY_BEGIN();

    RUN_TEST(test_get_number_binary_max_value);
    RUN_TEST(test_get_number_binary_min_value);
    RUN_TEST(test_get_number_binary_mid_value);
    RUN_TEST(test_get_number_binary_arbitrary_value);
    RUN_TEST(test_get_number_binary_out_of_range_high);
    RUN_TEST(test_get_number_binary_out_of_range_low);
    RUN_TEST(test_get_number_binary_one_bit_active);
    RUN_TEST(test_get_number_binary_two_bits_active);
    RUN_TEST(test_get_number_binary_edge_values);
    RUN_TEST(test_get_number_binary_consecutive_values);
    RUN_TEST(test_get_number_binary_large_value);
    RUN_TEST(test_get_number_binary_int_min);
    RUN_TEST(test_get_number_binary_int_max);
    RUN_TEST(test_get_number_binary_large_array);
    RUN_TEST(test_get_number_binary_null_pointer);

    RUN_TEST(test_null);
    RUN_TEST(test_One);
    RUN_TEST(test_two);
    RUN_TEST(test_three);
    RUN_TEST(test_four);
    RUN_TEST(test_Five);
    RUN_TEST(test_MinusOne);
    RUN_TEST(test_SixtyFour);
    RUN_TEST(test_Forty);

    return UNITY_END();
}
