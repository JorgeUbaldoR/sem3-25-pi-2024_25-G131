#include <stdio.h>
#include "../include/asm.h"

int main() {
    int buffer[] = {1, 0, 0};
    int length = sizeof(buffer) / sizeof(int); // Tamanho do buffer circular
    int array[length]; // Array onde os elementos mais antigos serão movidos
    
    int tail = 0;
    int head = 1;  
    
    int n = 1; // Quantidade de elementos a serem movidos

    printf("=== Before Method ===\n");

	if (head == tail) {
    printf("Buffer: [ ]\n");
    printf("Head: %d\n", head);
    printf("Tail: %d\n", tail);
	
	} else if (head < tail) {
    printf("Buffer: [ ");
    for (int i = head; i <= tail; i++) {
        printf("%d ", buffer[i]);
    }
    printf("]\n");
    printf("Head -> %d\n", buffer[head]);
    printf("Tail -> %d\n", buffer[tail]);

	} else { // head > tail
    printf("Buffer: [ ");
    
    for (int i = head; i < length; i++) {
        printf("%d ", buffer[i]);
    }
    
    for (int i = 0; i <= tail; i++) {
        printf("%d ", buffer[i]);
    }
    printf("]\n");
    printf("Head -> %d\n", buffer[head]);
    printf("Tail -> %d\n", buffer[tail]);
}

	printf("=====================\n");
	
	printf("\n");

	// Chamada da função
	int ret = move_n_to_array(buffer, length, &tail, &head, n, array);
	
	printf("=== OPERAÇÃO: MOVER ELEMENTOS ===\n");
printf("Quantidade de elementos a mover: %d\n", n);

printf("\n");

if (ret) {
    printf("> Elementos movidos para o array: [");
    for (int i = 0; i < n; i++) {
        printf("%d", array[i]);
        if (i < n - 1) printf(", ");
    }
    printf("]\n");
} else {
    printf("Could not move %d elements (not enough elements).\n", n);
}

printf("=====================\n");

printf("\n");

	printf("=== After Method ===\n");

	if (head == tail) {
    printf("Buffer: [ ]\n");
	printf("Head: %d\n", head);
    printf("Tail: %d\n", tail);
	
	} else if (head < tail) {
    printf("Buffer: [ ");
    
    for (int i = head; i <= tail; i++) {
        printf("%d ", buffer[i]);
    }
    
    printf("]\n");
    printf("Head -> %d\n", buffer[head]);
    printf("Tail -> %d\n", buffer[tail]);
	
	} else { // head > tail
    printf("Buffer: [ ");
    for (int i = head; i < length; i++) {
        printf("%d ", buffer[i]);
    }
    for (int i = 0; i <= tail; i++) {
        printf("%d ", buffer[i]);
    }
    
    printf("]\n");
    printf("Head -> %d\n", buffer[head]);
    printf("Tail -> %d\n", buffer[tail]);
}

	printf("=====================\n");
printf("Operation Result: %s\n", ret ? "Success" : "Failure");

if (!ret) {
    printf("Could not move %d elements (not enough elements).\n", n);
}

printf("=====================\n");

    return 0;
}
