#include <stdio.h>
#include "../include/asm.h"

int main () {
    int buffer[] = {0,1,2,3,4,5,6,7,8,9};
    int length = sizeof(buffer) / sizeof(int);
    int head = 5;
    int tail = 6;
    int value;  
	

    printf("--- Before method ---\n");

    //Head before tail
    if (head < tail) {
    printf("Buffer: [ ");
    for(int i = head; i <= tail; i++) {
        printf("%d ", buffer[i]);
    }
    printf("]\n");
    printf("Head: %d\n", buffer[head]);
    printf("Tail: %d\n", buffer[tail]);

    } 

    //Head equals tail
    if (head == tail) {
    printf("Buffer: [ ]\n");
    printf("Head: %d\n", buffer[head]);
    printf("Tail: %d\n", buffer[tail]);

    } 

    //Head after tail
    if (head > tail) {
    printf("Buffer: [ ");
    for(int i = head; i < length; i++) {
        printf("%d ", buffer[i]);
    }
    for(int i = 0; i <= tail; i++) {
        printf("%d ", buffer[i]);
    }
    printf("]\n");
    printf("Head: %d\n", buffer[head]);
    printf("Tail: %d\n", buffer[tail]);

    } 

    int ret = dequeue_value(buffer, length, &tail, &head, &value); 

    //Head before tail
    printf("\n--- After method ---\n");
    if (head != tail && head < tail) {
    printf("Buffer: [ ");
    for(int i = head; i <= tail; i++) {
        printf("%d ", buffer[i]);
    }
    printf("]\n");
    printf("Value removed: %d\n", value);
    printf("Head: %d\n", buffer[head]);
    printf("Tail: %d\n", buffer[tail]);
} 
    if (head == tail) {
	printf("Buffer: [ ]\n");
	}

    if (head > tail) {
    printf("Buffer: [ ");
    for(int i = head; i < length; i++) {
        printf("%d ", buffer[i]);
    }
    for(int i = 0; i <= tail; i++) {
        printf("%d ", buffer[i]);
    }
    printf("]\n");
    printf("Head: %d\n", buffer[head]);
    printf("Tail: %d\n", buffer[tail]);

    } 

    printf("Output: %d\n", ret);
    
    return 0;
}
