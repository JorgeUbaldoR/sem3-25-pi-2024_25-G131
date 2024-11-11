#include <stdio.h>
#include "asm.h"


int main(){
    int vec1[] = {6,3,2,5,1,4,7,8}; // 1 2 3 4 5 6
    int length1 = sizeof(vec1)/sizeof(int);
    int me1 = 89;

    printf("%p\n",(void*)&me1);
    int res = median(vec1,length1,&me1);
    printf("%d: %d\n", res,me1);

    int vec2[] = {1,2,3,4,5};
    int length2 = sizeof(vec2)/sizeof(int);
    int me2 = 0;

    res = median(vec2,length2,&me2);
    printf("%d: %d\n", res,me2);
}