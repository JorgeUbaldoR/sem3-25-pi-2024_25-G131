#include <stdio.h>
#include "asm.h"


int main () {
		
		int buffer[] = {2,32,5,23,4,6,19,29,43,27,7,43,55,32,3,12,2,5,7,9,12,16,18,21};
		int lenght = sizeof(buffer) / sizeof(int);
		int* tail = buffer + (lenght - 1);
		int* head = buffer;
		int value[lenght - 1]; 
		
		printf("---Before method---\n");
		printf("Buffer: [ ");
		for(int* i = buffer; i < buffer + lenght; i++) {
			printf("%d ", *i);
		}
		printf(" ]\n");
		printf("Head: %d\n", *head);
		printf("Tail: %d\n", *tail);
		printf("Lenght: %d\n", lenght);
		
		int ret = dequeue_value(buffer, lenght, tail, head, value);
		
	
		printf("\n---After method---\n");
		head = value;
		printf("Buffer: [ ");
		for(int* i = buffer; i < buffer + lenght; i++) {
			printf("%d ", *i);
		}
		printf(" ]\n");
		printf("Value: [ ");
		for(int* i = value; i < value + lenght - 1; i++) {
			printf("%d ", *i);
		}
		printf(" ]\n");
		printf("Head: %d\n", *head);
		printf("Tail: %d\n", *tail);
		printf("Output: %d\n" ,ret);
		
	
	
	return 0;
}
