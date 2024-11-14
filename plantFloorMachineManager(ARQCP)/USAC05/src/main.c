#include <stdio.h>
#include "../include/asm.h"

int main() {
    int buffer[] = {2, 32, 5, 23, 4, 6, 19, 29, 43, 27, 7, 43, 55, 32, 3, 12, 2, 5, 7, 9, 12, 16, 18, 21};
    int length = sizeof(buffer) / sizeof(int);
    int head = 0;            // Head index
    int tail = 23;   		// Tail index
    int value = 99;          // Value to insert

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
