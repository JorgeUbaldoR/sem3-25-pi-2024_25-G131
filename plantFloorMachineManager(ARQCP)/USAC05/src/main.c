#include <stdio.h>
#include "../include/asm.h"

int main() {
    int buffer[] = {0,1,2,3,4,5,6,7,8,9};
    int length = sizeof(buffer) / sizeof(int);
    int head = 0;            // Head index
    int tail = 9;   		// Tail index
    int value = 5;          // Value to insert

    printf("---Before method---\n");
    printf("Buffer: [ ");
    for (int i = 0; i < length; i++) {
        printf("%d ", buffer[i]);
    }
    printf("]\n");
    printf("Head: %d (index)\n", head);
    printf("Tail: %d (index)\n", tail);
    printf("Length: %d\n", length);

    int ret = enqueue_value(buffer, length, &tail, &head, value);

    printf("\n---After method---\n");
    printf("Buffer: [ ");
    for (int i = 0; i < length; i++) {
        printf("%d ", buffer[i]);
    }
    printf("]\n");
    printf("Head: %d (index)\n", head);
    printf("Tail: %d (index)\n", tail);
    printf("Length: %d\n", length);
    printf("Output (Buffer Full Status): %d\n", ret);

    return 0;
}
