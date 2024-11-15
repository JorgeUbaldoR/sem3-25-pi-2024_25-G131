#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "../include/asm.h"


void loginUI(char * username, char * password);
int login(char *username, char *password);
void showTeam();
int us_ui();
void usac_01();
void usac_02();
void usac_03();
void usac_04();
void usac_05();
void usac_06();
void usac_07();
void usac_09();
void usac_10();




int main() {
    int option;
    char username[50];
    char password[50];

    while (1) {
        printf("╔══════════════════════════╗");
        printf("\n║        Main Menu         ║\n");
        printf("╚══════════════════════════╝\n");
        printf("║   1. Login               ║\n");
        printf("║   2. Show Team           ║\n");
        printf("║   0. Exit                ║\n");
        printf("╚══════════════════════════╝\n");

        printf("Select a option: ");
        scanf("%d", &option);

        switch (option) {
            case 1:
                loginUI(username,password);
                break;
            case 2:
                showTeam();
                break;
            case 0:
                printf("Saindo...\n");
                return 0;
            default:
                printf("Invalid choice. Try again...\n");
        }
    }

}

int us_ui(){
    int option;
    while (1) {
        printf("\n════════════════════════════\n");
        printf("            US_UI\n\n");
        printf(" 1.  USAC01\n");
        printf(" 2.  USAC02\n");
        printf(" 3.  USAC03\n");
        printf(" 4.  USAC04\n");
        printf(" 5.  USAC05\n");
        printf(" 6.  USAC06\n");
        printf(" 7.  USAC07\n");
        printf(" 8.  USAC08\n");
        printf(" 9.  USAC09\n");
        printf(" 10. USAC10\n");
        printf(" 0. Exit  \n");
        printf("Select a option: ");

         if (scanf("%d", &option) != 1) {
             printf("Invalid input. Please enter a number.\n");
              while (getchar() != '\n');
              continue;
         }

        switch (option) {
            case 1: usac_01();
                break;

            case 2: usac_02();
                break;

            case 3: usac_03();
                break;

            case 4: usac_04();
                break;

            case 5: usac_05();
                break;

            case 6: usac_06();
                break;

            case 7: usac_07();
                break;

            case 8:
                break;

            case 9: usac_09();
                break;

            case 10: usac_10();
                break;

            case 0:
                printf("\nExit...\n\n");
                return 0;
            default:
                printf("Invalid choice. Try again...\n");
        }
    }
}

void showTeam(){
    printf("\n════════════════════════════\n");
    printf("      Development Team\n\n");
    printf("Emanuel Almeida   - 1230839@isep.ipp.pt\n");
    printf("Jorge Ubaldo      - 1231274@isep.ipp.pt\n");
    printf("Francisco Santos  - 1230564@isep.ipp.pt\n");
    printf("PyongYang Xu      - 1230444@isep.ipp.pt\n\n");
}



void loginUI(char * username, char * password){
    printf("\n════════════════════════════\n");
    printf("          Login UI\n\n");
    printf(" Username: ");
    scanf("%s", username);
    printf(" Password: ");
    scanf("%s", password);

    if (login(username, password)) {
        printf("\nLogin with success! Welcome-Back, %s.\n\n", username);
        us_ui();
    } else {
        printf("\nINVALID Username/Password.\n\n");
    }

}

int login(char *username, char *password) {

    char stored_username[] = "man";
    char stored_password[] = "man";

    if (strcmp(username, stored_username) == 0 && strcmp(password, stored_password) == 0) {
        return 1;
    }
    return 0;
}


void usac_01() {

    char str[] = "TEMP&unit:celsius&value:20#HUM&unit:percentage&value:80";
    char token [] = "temp";
    char unit[20];
    int value ;
    printf("\nString: %s\n", str);
    printf("Token: %s\n", token);
    int res = extract_data (str,token,unit,&value) ;
    printf ("%d:%s ,%d\n",res,unit,value ); // 1: celsius ,20


    char str2[] = "HUM&unit:percentage&value:80#TEMP&unit:celsius&value:20";
    char unit2[20];
    char token2 [] = "hum";
    printf("\nString: %s\n", str2);
    printf("Token: %s\n", token2);
    res = extract_data (str2,token2,unit2,&value) ;
    printf ("%d:%s ,%d\n",res,unit2,value); // 1: celsius ,20


    char unit3[20] = {};
    char token3 [] = "AAA" ;
    printf("Token: %s\n", token3);
    res = extract_data (str,token3,unit3,&value) ;
    printf ("%d:%s ,%d\n",res,unit3,value); // 0: ,0

}

void usac_02() {

    int value = 26;
    char bits [5];

    printf("\nDecimal value: %d\n", value);
    int res = get_number_binary (value,bits);
    printf ("%d : [%d,%d,%d,%d,%d]\n",res,bits[0],bits[1],bits[2],bits[3],bits[4]); // 1: 1 ,1 ,0 ,1 ,0

    value = 0;
    printf("Decimal value: %d\n", value);
    res = get_number_binary (value,bits);
    printf ("%d : [%d,%d,%d,%d,%d]\n",res,bits[0],bits[1],bits[2],bits[3],bits[4]); // 1: 1 ,1 ,0 ,1 ,0

    value = 1;
    printf("Decimal value: %d\n" ,value);
    res = get_number_binary (value,bits);
    printf ("%d : [%d,%d,%d,%d,%d]\n",res,bits[0],bits[1],bits[2],bits[3],bits[4]); // 1: 1 ,1 ,0 ,1 ,0

    value = 15;
    printf("Decimal value: %d\n", value);
    res = get_number_binary (value,bits);
    printf ("%d : [%d,%d,%d,%d,%d]\n",res,bits[0],bits[1],bits[2],bits[3],bits[4]); // 1: 1 ,1 ,0 ,1 ,0

    value = 31;
    printf("Decimal value: %d\n", value);
    res = get_number_binary (value,bits);
    printf ("%d : [%d,%d,%d,%d,%d]\n",res,bits[0],bits[1],bits[2],bits[3],bits[4]); // 1: 1 ,1 ,0 ,1 ,0

    value = 32;
    printf("Decimal value: %d\n", value);
    res = get_number_binary (value,bits);
    printf ("%d : [%d,%d,%d,%d,%d]\n",res,bits[0],bits[1],bits[2],bits[3],bits[4]); // 1: 1 ,1 ,0 ,1 ,0

    value = -1;
    printf("Decimal value: %d\n", value);
    res = get_number_binary (value,bits);
    printf ("%d : [%d,%d,%d,%d,%d]\n",res,bits[0],bits[1],bits[2],bits[3],bits[4]); // 1: 1 ,1 ,0 ,1 ,0

}


void usac_03() {

    int value;
    char str[] = "  89  ";

    printf("\n\nValid string before method: %s\n", str);

    int res = get_number(str, &value);
    printf("Return value: %d\n", res);
    printf("Number: %d\n", value);

    char str1[] = "	8--9 ";
    printf("\nInvalid string before method: %s\n", str1);

    res = get_number(str1, &value);
    printf("Return value: %d\n", res);

}


void usac_04() {

	int value = 26;
	char str[] = " oN ";
	char cmd[20];
	int res = format_command(str, value, cmd) ;
	printf("%d: %s\n", res, cmd); // 1: ON ,1 ,1 ,0 ,1 ,0

	char str2[] = " aaa ";
	int result = format_command(str2, value, cmd) ;
	printf("%d: %s\n", result, cmd); // 0:

}

void usac_05() {

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

}

void usac_06() {

    int buffer[] = {0,1,2,3,4,5,6,7,8,9};
int length = sizeof(buffer) / sizeof(int);
int head;
int tail;
int value;

printf("Full buffer: [ ");
for (int* i = buffer; i < buffer + length; i++) {
    printf("%d ", *i);
}
printf(" ]\n");
printf("Length: %d\n", length);

do {
    printf("Choose head: ");
    if (scanf("%d", &head) != 1 || head < 0 || head >= length) {
        printf("Invalid input. Please enter a valid number between 0 and %d.\n", length - 1);
        while (getchar() != '\n');
        continue;
    }
    break;
} while (1);

do {
    printf("Choose tail: ");
    if (scanf("%d", &tail) != 1 || tail < 0 || tail >= length) {
        printf("Invalid input. Please enter a valid number between 0 and %d.\n", length - 1);
        while (getchar() != '\n');
        continue;
    }
    break;
} while (1);


    printf("\n--- Before method ---\n");

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

}

void usac_07() {

    int buffer[] = {0,1,2,3,4,5,6,7,8,9};
	int length = sizeof(buffer) / sizeof(int);
    int head;
	int tail;

    printf("Full buffer: [ ");
    for(int* i = buffer; i < buffer + length; i++) {
        printf("%d ", *i);
    }
    printf(" ]\n");
    printf("Length: %d\n", length);

    do {
        printf("Choose head: ");
        if(scanf("%d", &head) != 1 || head < 0 || head >= length) {
            printf("Invalid input. Please enter a valid number between 0 and %d.\n", length - 1);
            while (getchar() != '\n');
            continue;
        }
        break;
    } while (1);

    do {
        printf("Choose tail: ");
        if(scanf("%d", &tail) != 1 || tail < 0 || tail >= length) {
            printf("Invalid input. Please enter a valid number between 0 and %d.\n", length - 1);
            while (getchar() != '\n');
            continue;
        }
        break;
    } while (1);

	int n = get_n_element(buffer, length, &tail, &head);
	printf("Number of elements: %d\n", n);
}


void usac_09() {

    printf("\n\n╔══════════════════════════╗");
    printf("\n║          Sort            ║  \n");
    printf("╚══════════════════════════╝\n");
    int buffer[] = {2,32,5,23,4,6,19,29,43,27,7,43,55,32,3,12,2,5,7,9,12,16,18};
    int lenght = sizeof(buffer) / sizeof(int);
    char c;

    printf("Buffer : [ ");
        for(int* i = buffer; i < buffer + lenght; i++) {
        printf("%d ", *i);
        }
    printf(" ]\n");

    do {
        printf("Select '0' for descending order or '1' for ascending: ");
        scanf("%hhd", &c);
    } while ( c!= 0 && c!= 1);

    int res = sort_array(buffer, lenght, c);

    printf("Sorted Buffer (descending): [ ");
        for(int* i = buffer; i < buffer + lenght; i++) {
                printf("%d ", *i);
        }
    printf(" ]\n");
    printf("Return value: %d\n", res);
}


void usac_10() {

    int vec1[] = {6,3,2,5,1,4,7,8}; // 1 2 3 4 5 6
    int length1 = sizeof(vec1)/sizeof(int);
    int me1 = 1;

    int res = median(vec1,length1,&me1);
    
    printf("\nVec = [");
    for (int i = 0; i < length1; i++){
        printf("%d,", *(vec1+i));
    }
    printf("]\n%d: %d\n", res,me1);

    int vec2[] = {1,2,3,4,5};
    int length2 = sizeof(vec2)/sizeof(int);
    int me2 = 1;

    res = median(vec2,length2,&me2);
    printf("\nVec = [");
    for (int i = 0; i < length2; i++){
        printf("%d,", *(vec2+i));
    }
    printf("]\n%d: %d\n", res,me2);

    int vec3[] = {};
    int length3 = sizeof(vec3)/sizeof(int);
    int me3 = 1;

    res = median(vec3,length3,&me3);
    printf("\nVec = [");
    for (int i = 0; i < length3; i++){
        printf("%d, ", *(vec3+i));
    }
    printf("]\n%d: %d\n", res,me3);

}


