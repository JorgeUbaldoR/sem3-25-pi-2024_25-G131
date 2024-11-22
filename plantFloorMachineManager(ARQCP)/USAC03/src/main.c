#include <stdio.h>
#include "../include/asm.h"


int main () {
		
	int value;
	char str[] = "	89  ";
	int res = get_number(str,&value);
	printf("%d: %d\n", res, value);
	
	char str2[] = "8--9";
	
	res = get_number(str2,&value);
	printf("%d: %d\n", res, value);
	
	return 0;
}
