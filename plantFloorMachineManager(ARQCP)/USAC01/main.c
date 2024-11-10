#include <stdio.h>
#include "asm.h"


int main(){
    char str [] = "TEMP&unit:celsius&value:20#HUM&unit:percentage&value:80 ";
    char token [] = "HUm";
    char unit [20];
    int value ;

    int res = extract_data (str,token,unit,&value) ;
    printf ("%d:%s,%d\n",res,unit,value ); // 1: celsius ,20

    //char token2 [] = "AAA" ;
    //res = extract_data ( str , token2 , unit , & value ) ;
    //printf ( "%d:%s ,%d \n" , res , unit , value ); // 0: ,0
}