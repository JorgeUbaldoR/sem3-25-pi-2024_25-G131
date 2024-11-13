#include <stdio.h>
#include "../include/asm.h"

int main () {
    int buffer[] = {1,2,3,4,5,6,7,8,9};
    int length = sizeof(buffer) / sizeof(int);
    int* tail = buffer + 4;
    int* head = buffer + 1 ;
    int value;  

    printf("--- Before method ---\n");
    printf("Buffer: [ ");
    for(int* i = head; i <= tail; i++) {
        printf("%d ", *i);
    }
    printf("]\n");
    printf("Head: %d\n", *head);
    printf("Tail: %d\n", *tail);

    int ret = dequeue_value(buffer, length, &tail, head, &value); 

    printf("\n--- After method ---\n");
    printf("Buffer: [ ");
    for(int* i = head; i < tail; i++) {
        printf("%d ", *i);
    }
    printf("]\n");
    printf("Value removed: %d\n", value);
    printf("New Head: %d\n", *head);
    printf("Tail: %d\n", *tail);
    printf("Output: %d\n", ret);
    
    return 0;
}
