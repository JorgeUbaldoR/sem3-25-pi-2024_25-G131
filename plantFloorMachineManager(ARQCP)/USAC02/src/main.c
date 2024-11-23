#include <stdio.h>
#include "../include/asm.h"

void print(int value,int res, char* bits){
    printf("\nValue: %d\n",value);
    if(res == 1){
        printf ("%d : [%d,%d,%d,%d,%d]\n",res,bits[4],bits[3],bits[2],bits[1],bits[0]); 
    }else{
        printf ("%d : [ ]\n",res); 
    }
}


int main(){
    int value = 26; 
    char bits [5];
    
    int res = get_number_binary (value,bits);
    print(value,res,bits);

    char bits2[5];
    value = 0;
    res = get_number_binary (value,bits2);
    print(value,res,bits2);

    char bits3[5];
    value = 1;
    res = get_number_binary (value,bits3);
    print(value,res,bits3);
    
    char bits4[5];
    value = 15;
    res = get_number_binary (value,bits4);
    print(value,res,bits4);

    char bits5[5];
    value = 31;
    res = get_number_binary (value,bits5);
    print(value,res,bits5);

    char bits6[5];
    value = 32;
    res = get_number_binary (value,bits6);
    print(value,res,bits6);

    char bits7[5];  
    value = -1;
    res = get_number_binary (value,bits7);
    print(value,res,bits7);
    
}