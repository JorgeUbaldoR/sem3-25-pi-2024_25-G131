#include <stdio.h>
#include "asm.h"


int main(){
    char str[] = "TEMP&unit:celsius&value:20#HUM&unit:percentage&value:80";
    char str2[] = "HUM&unit:percentage&value:80#TEMP&unit:celsius&value:20";
    char token [] = "temp";
    char unit[20];
    int value ;

    char res = extract_data (str,token,unit,&value) ;
    printf ("%d:%s,%d\n",res,unit,value ); // 1: celsius ,20

    char token2 [] = "hum";
    res = extract_data (str2,token2,unit,&value) ;
    printf ("%d:%s,%d\n",res,unit,value ); // 1: celsius ,20

    char token3 [] = "AAA" ;
    res = extract_data (str,token3,unit,&value) ;
    printf ( "%d:%s ,%d \n" , res , unit , value ); // 0: ,0
}