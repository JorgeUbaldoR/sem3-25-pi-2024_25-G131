#ifndef ASM_H
#define ASM_H
int extract_data(char* str, char* token, char*unit, int* value);
int get_number_binary(int n, char* bits);
int get_number (char* a, int* b);
int dequeue_value (int* a, int b, int** c, int* d, int* e);
int get_n_element (int* a, int b, int* c, int* d);
int sort_array (int* a, int b, char c);
int median(int* vec, int length, int* me);
#endif