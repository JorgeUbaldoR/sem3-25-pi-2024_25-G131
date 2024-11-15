#include <stdio.h>
#include "../include/asm.h"


int main () {
		
		int buffer[] = {2,32,5,23,4,6,19,29,43,27,7,43,55,32,3,12,2,5,7,9,12,16,18};   
		int lenght = sizeof(buffer) / sizeof(int);
		
		int head = 12;
		int tail = 10;
		
		
		int n = get_n_element(buffer, lenght, &tail, &head);
		printf("Number of elements: %d\n", n);
		
	
	return 0;
}
